package bll;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * clasa ClientBLL are metode ce implementeaza operatile de baza pe baze de date construite in clasa AbstractDAO
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * constror clasa ClientBLL marcheaza validatorii si instantiaza obiectul de tip DAO
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        return client;
    }

    public List<Client> findAllClients() {
        List<Client> clientsList = clientDAO.findAll();
        if (clientsList == null) {
            throw new NoSuchElementException("Clients not found!");
        }
        return clientsList;
    }

    public Object[][] getClientsFields (List<Client> clientList) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Object[][] clients = clientDAO.getFields(clientList);
        return clients;
    }

    public String[] getColumnsName() {
        String [] columns = clientDAO.getColumns();
        return columns;
    }

    public Client insertClient(Client c) {
        return clientDAO.insert(c);
    }

    public Client updateClient(Client client){
        return clientDAO.update(client);
    }

    public Client deleteClient(Client client){
        return clientDAO.delete(client);
    }
}
