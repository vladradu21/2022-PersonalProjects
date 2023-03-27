package model;

import java.util.LinkedList;
import java.util.regex.*;

public class Polinom {

    private LinkedList<Monom> polinomul = new LinkedList<Monom>();

    public Polinom() {
        this.polinomul = polinomul;
    }

    public Polinom(String sir){
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(sir);
        while (matcher.find()) {
            Monom m = new Monom(getCoeficient(matcher.group(1)), getExponent(matcher.group(1)));
            polinomul.add(m);
        }
    }

    private static int getCoeficient(String sir){
        String[] aux = sir.split("x");
        int coef = Integer.parseInt(aux[0]);
        return coef;
    }

    private static int getExponent(String sir){
        String[] aux = sir.split("\\^");
        int exponent = Integer.parseInt(aux[1]);
        return exponent;
    }

    public LinkedList<Monom> getPolinomul() {
        return this.polinomul;
    }

    public String toString() {
        String rezultat ="";
        for (Monom m : polinomul) {
            if(rezultat.length() == 0) {
                if(m.getCoeficient() > 0)
                    rezultat += (int) m.getCoeficient()+"x^"+ (int) m.getExponent();
                else
                    if(m.getCoeficient() < 0)
                        rezultat += (int) m.getCoeficient()+"x^"+ (int) m.getExponent();
            }
            else
                if(m.getCoeficient() > 0)
                    rezultat += "+"+(int) m.getCoeficient()+"x^"+ (int) m.getExponent();
                    else
                        if(m.getCoeficient() < 0)
                            rezultat += (int) m.getCoeficient()+"x^"+ (int) m.getExponent();
        }
        return rezultat;
    }

    public String toString2() {
        String rezultat ="";
        for (Monom m : polinomul) {
            if(rezultat.length() == 0) {
                if(m.getCoeficient() > 0)
                    rezultat += m.getCoeficient()+"x^"+ m.getExponent();
                else
                if(m.getCoeficient() < 0)
                    rezultat += m.getCoeficient()+"x^"+ m.getExponent();
            }
            else
            if(m.getCoeficient() > 0)
                rezultat += "+"+ m.getCoeficient()+"x^"+ m.getExponent();
            else
            if(m.getCoeficient() < 0)
                rezultat += m.getCoeficient()+"x^"+ m.getExponent();
        }
        rezultat += "+C";
        return rezultat;
    }
}
