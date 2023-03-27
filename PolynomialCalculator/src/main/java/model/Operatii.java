package model;

public class Operatii {

    public static Polinom addOperation(Polinom p, Polinom q) {
        Polinom rezultat = new Polinom();

        for(Monom m : p.getPolinomul()) {
            int ok = 0;
            for(Monom n : q.getPolinomul()) {
                if(m.getExponent() == n.getExponent()) {
                    ok = 1;
                    if(m.getCoeficient() + n.getCoeficient() != 0) {
                        Monom x = new Monom(m.getCoeficient()+n.getCoeficient(), m.getExponent());
                        rezultat.getPolinomul().add(x); }
                }
            }
            if(ok == 0)
                rezultat.getPolinomul().add(m);
        }

        for(Monom n : q.getPolinomul()) {
            int ok = 0;
            for(Monom m : p.getPolinomul())
                if(n.getExponent() == m.getExponent())
                    ok = 1;
            if(ok == 0)
                rezultat.getPolinomul().add(n);
        }

        polinomSort(rezultat);
        return rezultat;
    }

    public static Polinom subbOperation (Polinom p, Polinom q) {
        Polinom rezultat = new Polinom();

        for(Monom m : p.getPolinomul()) {
            int ok = 0;
            for(Monom n : q.getPolinomul()) {
                if(m.getExponent() == n.getExponent()) {
                    ok = 1;
                    if(m.getCoeficient() - n.getCoeficient() != 0) {
                        Monom x = new Monom(m.getCoeficient() - n.getCoeficient(), m.getExponent());
                        rezultat.getPolinomul().add(x); }
                }
            }
            if(ok == 0)
                rezultat.getPolinomul().add(m);
        }

        for(Monom n : q.getPolinomul()) {
            int ok = 0;
            for(Monom m : p.getPolinomul())
                if(n.getExponent() == m.getExponent())
                    ok = 1;
            if(ok == 0){
                Monom x = new Monom( -n.getCoeficient(), n.getExponent());
                rezultat.getPolinomul().add(x);
            }
        }

        polinomSort(rezultat);
        return rezultat;
    }

    public static Polinom mulOperation(Polinom p, Polinom q) {
        Polinom rez = new Polinom();

        for(Monom m : p.getPolinomul()) {
            for(Monom n : q.getPolinomul()) {
                Monom k = new Monom(m.getCoeficient()*n.getCoeficient(), m.getExponent()+n.getExponent());
                rez.getPolinomul().add(k);
            }
        }
        polinomSort(rez);
        return simplificaPolinom(rez);
    }

    public static String divOperation(Polinom p, Polinom q){
        Polinom catul = new Polinom();
        Polinom p1 = new Polinom();
        duplicatePolinom(p1, p);
        Polinom intermediar = new Polinom();

        while(p1.getPolinomul().get(0).getExponent() >= q.getPolinomul().get(0).getExponent()){
            float a = p1.getPolinomul().get(0).getCoeficient() / q.getPolinomul().get(0).getCoeficient();
            float b = p1.getPolinomul().get(0).getExponent() - q.getPolinomul().get(0).getExponent();
            Monom m = new Monom(a, b);
            catul.getPolinomul().add(m);

            intermediar.getPolinomul().clear();
            intermediar.getPolinomul().add(m);

            Polinom x = new Polinom();
            Polinom y = new Polinom();
            duplicatePolinom(x, mulOperation(q, intermediar));
            duplicatePolinom(y,subbOperation(p1, mulOperation(q, intermediar)));

            p1 = new Polinom();
            duplicatePolinom(p1, y);
        }

        return "catul: " + catul.toString() + "\nrestul: " + p1.toString();
    }

    public static Polinom derivOperation(Polinom p) {
        Polinom rez = new Polinom();

        for(Monom m : p.getPolinomul()){
            Monom n = new Monom(m.getCoeficient() * m.getExponent(), m.getExponent() - 1);
            if(n.getExponent() >= 0)
            rez.getPolinomul().add(n);
        }
        return rez;
    }

    public static Polinom integOperation(Polinom p) {
        Polinom rez = new Polinom();

        for(Monom m : p.getPolinomul()) {
            Monom n = new Monom(m.getCoeficient() * 1 / (m.getExponent()+1), m.getExponent()+1);
            rez.getPolinomul().add(n);
        }
        return rez;
    }

    public static String integOperationV2(Polinom p) {
        String rez = "";
        for(Monom m : p.getPolinomul())
            if(m.getCoeficient() > 0)
                rez += "+" + (int)m.getCoeficient() + "/" + (int)(m.getExponent()+1) +"x^" + (int)(m.getExponent()+1);
            else
                rez += (int)m.getCoeficient() + "/" + (int)(m.getExponent()+1) +"x^" + (int)(m.getExponent()+1);
        return rez;
    }

    public static void polinomSort(Polinom p){
        for(int i = 0; i < p.getPolinomul().size(); i++)
            for(int j = i+1; j<p.getPolinomul().size(); j++)
                if(p.getPolinomul().get(i).getExponent() < p.getPolinomul().get(j).getExponent())
                    swap(p, p.getPolinomul().get(i), p.getPolinomul().get(j));
    }

    public static void swap(Polinom p, Monom m, Monom n){
        int index1 = p.getPolinomul().indexOf(m);
        int index2 = p.getPolinomul().indexOf(n);
        p.getPolinomul().set(index1, n);
        p.getPolinomul().set(index2, m);
    }

    public static Polinom simplificaPolinom(Polinom p){
        Polinom rezultat = new Polinom();
        int i=0;

        while(i < p.getPolinomul().size()-1){
            Monom m = new Monom(p.getPolinomul().get(i).getCoeficient(), p.getPolinomul().get(i).getExponent());
            i++;
            while(m.getExponent() == p.getPolinomul().get(i).getExponent()){
                m.setCoeficent(m.getCoeficient() + p.getPolinomul().get(i).getCoeficient());
                i++;
            }
            rezultat.getPolinomul().add(m);
        }
        rezultat.getPolinomul().add(p.getPolinomul().get(i));
        return rezultat;
    }

    public static void duplicatePolinom(Polinom p, Polinom q){
        for(Monom m : q.getPolinomul())
            p.getPolinomul().add(m);
    }

}
