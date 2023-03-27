package bll;

import java.io.Serializable;

/**
 * MenuItem este o clasa abstracta
 * continte antetele metodelor care trebuie implementate de toate clasele ce extind acesta clasa
 */
public abstract class MenuItem implements Serializable {

    private static final long serialVersionUID = 123456789L;

    public abstract String getTitle();
    public abstract double getRating();
    public abstract int getCalories();
    public abstract int getProtein();
    public abstract int getFat();
    public abstract int getSodium();
    public abstract int getPrice();

}
