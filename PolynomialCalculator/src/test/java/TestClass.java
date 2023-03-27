import model.Operatii;
import model.Polinom;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    @Test
    public void addTest() {
        Polinom p = new Polinom("4x^5-3x^4+1x^2-8x^1+1x^0");
        Polinom q = new Polinom("3x^4-1x^3+1x^2+2x^1-1x^0");
        String rez = new String("4x^5-1x^3+2x^2-6x^1");
        assertEquals(rez, Operatii.addOperation(p, q).toString());
    }

    @Test
    public void subTest() {
        Polinom p = new Polinom("4x^5-3x^4+1x^2-8x^1+1x^0");
        Polinom q = new Polinom("3x^4-1x^3+1x^2+2x^1-1x^0");
        String rez = new String("4x^5-6x^4+1x^3-10x^1+2x^0");
        assertEquals(rez, Operatii.subbOperation(p, q).toString());
    }

    @Test
    public void mulTest() {
        Polinom p = new Polinom("3x^2-1x^1+1x^0");
        Polinom q = new Polinom("1x^1-2x^0");
        String rez = new String("3x^3-7x^2+3x^1-2x^0");
        assertEquals(rez, Operatii.mulOperation(p, q).toString());
    }

    @Test
    public void divTest() {
        Polinom p = new Polinom("1x^3-2x^2+6x^1-5x^0");
        Polinom q = new Polinom("1x^2-1x^0");
        String rez = new String("catul: 1x^1-2x^0" + "\nrestul: 7x^1-7x^0");
        assertEquals(rez, Operatii.divOperation(p, q));
    }

    @Test
    public void derivTest() {
        Polinom p = new Polinom("1x^3-2x^2+6x^1-5x^0");
        String rez = "3x^2-4x^1+6x^0";
        assertEquals(rez, Operatii.derivOperation(p).toString());
    }

    @Test
    public void integTest(){
        Polinom p = new Polinom("1x^3-2x^2+6x^1-5x^0");
        String rez = "+1/4x^4-2/3x^3+6/2x^2-5/1x^1";
        assertEquals(rez, Operatii.integOperationV2(p));
    }
}
