import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Opinia implements Serializable{

    private LocalDate dataWystawienia;
    private String tekst;
    private int ocena;
    private Osoba osoba;
    private Zamówienie zamówienie;
    private static List<Opinia> extent = new ArrayList<>();
    public Opinia(String _tekst, int _ocena, Osoba _osoba, Zamówienie _zamówienie) {
        dataWystawienia = LocalDate.now();
        tekst = _tekst;
        ocena = _ocena;
        setOsoba(_osoba);
        zamówienie = _zamówienie;

        extent.add(this);
    }


    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
        osoba.addOpinie(this);
    }

    public Zamówienie getZamówienie() {
        return zamówienie;
    }

    public String toString() {
        return "Ocena: " + ocena +"\n" + "Tekst: " + tekst;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Opinia>) stream.readObject();
    }

    public static void save() {
        final String file = "opiniaExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Opinia.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "opiniaExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Opinia.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

}
