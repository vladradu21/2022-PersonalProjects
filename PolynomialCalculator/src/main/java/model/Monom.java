package model;

public class Monom {

    private float coeficent;
    private float exponent;

    public Monom(float coeficent,float exponent){
        this.coeficent = coeficent;
        this.exponent = exponent;
    }

    public float getCoeficient() {
        return coeficent;
    }

    public float getExponent() {
        return exponent;
    }

    public void setCoeficent(float coeficent){
        this.coeficent = coeficent;
    }

    public String toString(){
        return "coeficient: " + coeficent + " exponent: " + exponent;
    }
}
