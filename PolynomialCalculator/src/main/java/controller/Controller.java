package controller;
import model.Operatii;
import model.Polinom;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private static View view;
    Polinom p;
    Polinom q;

    public Controller(View view) {
        this.view = view;
        view.addAdunareListener(new AdunareListener());
        view.addScadereListener(new ScadereListener());
        view.addInmultireListener(new InmultireListener());
        view.addImpartireListener(new ImpartireListener());
        view.addDerivareListener(new DerivareListener());
        view.addIntegrareListener(new IntegrareListener());
    }

    public static View getView() {
        return view;
    }

   /* public void init(Polinom p, Polinom q){
        p = new Polinom(getView().getPrimulPolinomField());
        Operatii.polinomSort(p);
        System.out.println(p);
        q = new Polinom(getView().getAlDoileaPolinomField());
        Operatii.polinomSort(q);
        System.out.println(q);
    }
    public void init2(Polinom p){
        p = new Polinom(view.getPrimulPolinomField());
        Operatii.polinomSort(p);
        System.out.println(p);
    }*/

    class AdunareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e1) {
            System.out.println("\nadunare");
            p = new Polinom(view.getPrimulPolinomField());
            q = new Polinom(view.getAlDoileaPolinomField());
            view.rezultatText.setText(Operatii.addOperation(p, q).toString());

            System.out.println(p);
            System.out.println(q);
            System.out.println(Operatii.addOperation(p, q).toString());
        }
    }

    class ScadereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e2) {
            System.out.println("\nscadere");
            p = new Polinom(view.getPrimulPolinomField());
            q = new Polinom(view.getAlDoileaPolinomField());
            view.rezultatText.setText(Operatii.subbOperation(p, q).toString());

            System.out.println(p.toString());
            System.out.println(q.toString());
            System.out.println(Operatii.subbOperation(p, q).toString());
        }
    }

    class InmultireListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e3) {
            System.out.println(("\ninmultire"));
            p = new Polinom(view.getPrimulPolinomField());
            q = new Polinom(view.getAlDoileaPolinomField());
            view.rezultatText.setText(Operatii.mulOperation(p, q).toString());

            System.out.println(p.toString());
            System.out.println(q.toString());
            System.out.println(Operatii.mulOperation(p, q).toString());
        }
    }

    class ImpartireListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e4) {
            System.out.println(("\nimpartire"));
            p = new Polinom(view.getPrimulPolinomField());
            q = new Polinom(view.getAlDoileaPolinomField());
            view.rezultatText.setText(Operatii.divOperation(p, q));

            System.out.println(p.toString());
            System.out.println(q.toString());
            System.out.println(Operatii.divOperation(p, q));
        }
    }

    class DerivareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e5) {
            System.out.println(("\nderivare"));
            //init2(p);
            p = new Polinom(view.getPrimulPolinomField());
            view.rezultatText.setText(Operatii.derivOperation(p).toString());

            System.out.println(p.toString());
            System.out.println(Operatii.derivOperation(p).toString());
            System.out.println(Operatii.integOperation(Operatii.derivOperation(p)).toString2());
        }
    }

    class IntegrareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e6) {
            System.out.println("\nintegrare");
            p = new Polinom(view.getPrimulPolinomField());
            view.rezultatText.setText(Operatii.integOperationV2(p));

            System.out.println(p.toString());
            System.out.println(Operatii.integOperation(p).toString2());
            System.out.println(Operatii.derivOperation(Operatii.integOperation(p)).toString());
        }
    }
}