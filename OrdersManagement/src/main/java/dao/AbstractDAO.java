package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * este o clasa in care se scriu metode compatibile celor 3 obiecte
 * @param <T> se modifica in functie de clasa ce apeleaza
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * @param field fieldul dupa care se realizeaza selectia
	 * @return returneaza un string cu cod sql
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `");
		sb.append(type.getSimpleName());
		sb.append("` WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * @return reurneaza un string cu cod scris in sql pentru selectatrea tuturor inregistrarilor
	 */
	private String createSelectAllQuery(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `");
		sb.append(type.getSimpleName());
		sb.append("`");
		return sb.toString();
	}

	/**
	 * @param object primeste obiectul pe care dorim sa il inseram
	 * @return reurneaza un string cu cod scris in sql pentru inserarea unui element
	 */
	private String createInsertQuery(Object object){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO `");
		sb.append(type.getSimpleName());
		sb.append("` VALUES (");

		int sizeColumns = type.getDeclaredFields().length;
		int index = 0;

		for(Field field: type.getDeclaredFields()){
			field.setAccessible(true);
			sb.append("?");
			if(sizeColumns - 1 != index)
				sb.append(",");
			index++;
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * @param object primeste obiectul pe care dorim sa il editam
	 * @return reurneaza un string cu cod scris in sql pentru updatarea unui element
	 */
	private String createUpdateQuery(Object object){
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE `");
		sb.append(type.getSimpleName());
		sb.append("` SET ");

		int sizeColumns = type.getDeclaredFields().length;
		int index = 0;

		for(Field field: type.getDeclaredFields()){
			field.setAccessible(true);
			if(sizeColumns - 1 != index)
				sb.append(field.getName() + "=? ,");
			else
				sb.append(field.getName() + "=? ");
			index++;
		}
		sb.append(" WHERE id=?");

		return sb.toString();
	}

	/**
	 * @param object primeste obiectul pe care dorim sa il stergem
	 * @return reurneaza un string cu cod scris in sql pentru stergerea unui element
	 */
	private String createDeleteQuery(Object object){
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM `");
		sb.append(type.getSimpleName());
		sb.append("` WHERE id=?");

		return sb.toString();
	}

	/**
	 * @return returneaza lista de obiecte continute de tabela respectiva
	 */
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @param id reprezinta parametrul dupa care se face cautarea obiectelor
	 * @return returneaza lista de obiecte continuta de tabele in functie de conditia dorita
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} catch (IndexOutOfBoundsException e){
			return null;
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param t adauga obiectul t de tipul T in tabel
	 * @return obiectul creat
	 */
	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			for (Field field : type.getDeclaredFields() ) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(value.getClass().getSimpleName().equals("String") == true)
					statement.setString(index, value.toString());
				else {
					if(value.getClass().getSimpleName().equals("Double") == true) {
						statement.setDouble(index, Double.parseDouble(value.toString()));
					} else {
						statement.setInt(index, Integer.parseInt(value.toString()));
					}
				}
				index++;
			}
			statement.executeUpdate();
			return t;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insertClient " + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @param t modifica o inregistrare din tabel pe baza contintului obiectului t
	 * @return returneaza obiectul creat
	 */
	public T update(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createUpdateQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			int sizeColumns = type.getDeclaredFields().length;

			for (Field field : type.getDeclaredFields() ) {
				field.setAccessible(true);
				Object value = field.get(t);
				if(index == 1)
					statement.setInt(sizeColumns + 1, (Integer) field.get(t));

				if(value.getClass().getSimpleName().equals("String") == true)
					statement.setString(index, value.toString());
				else {
					if(value.getClass().getSimpleName().equals("Double") == true) {
						statement.setDouble(index, Double.parseDouble(value.toString()));
					} else {
						statement.setInt(index, Integer.parseInt(value.toString()));
					}
				}
				index++;
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:updateClient " + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}

	/**
	 * @param t sterge obiectul t din inregistrare
	 * @return
	 */
	public T delete(T t){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			Object value = null;
			for (Field field : type.getDeclaredFields() ) {
				field.setAccessible(true);
				if(index == 1)
					value = field.get(t);
				index++;
			}
			statement.setInt(1, (int) value);
			statement.executeUpdate();
			return t;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteClient " + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @return se returneaza un sir cu numele coloanelor tabelului
	 */
	public String[] getColumns () {
		int nrColumns = type.getDeclaredFields().length;
		String[] columnsName = new String[nrColumns];

		int i = -1;
		for(Field f : type.getDeclaredFields()) {
			i++;
			columnsName[i] = f.getName();
		}
		return columnsName;
	}

	/**
	 * @param list metoda primeste ca parametru o lista de obiecte
	 * @return se construieste o matrice care contine pe fiecare rand, separat pe coloane datele obiectului respectiv
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Object[][] getFields(List<T> list) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		int nrColumns = type.getDeclaredFields().length;
		Object [][] dataObj = new Object[list.size()][nrColumns];

		int i = 0, j = 0;
		for (T element : list) {
			j = 0;
			for(Field field : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Object value = propertyDescriptor.getReadMethod().invoke(element);
				dataObj[i][j] = value;
				j++;
			}
			i++;
		}
		return dataObj;
	}
}