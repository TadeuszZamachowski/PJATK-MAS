import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Adres implements Serializable, Comparable<Adres> {

    private final String ulica;
    private final String miejscowosc;
    private final String numer;
    private final String kodPocztowy;
    private static List<Adres> extent = new ArrayList<>();

    public Adres(String _ulica, String _miejscowosc, String _numer, String _kodPocztowy) {
        ulica = _ulica;
        miejscowosc = _miejscowosc;
        numer = _numer;
        kodPocztowy = _kodPocztowy;

        extent.add(this);
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getNumer() {
        return numer;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public String toString() {
        return ulica + " " + numer + "\n" + miejscowosc + '\n' + kodPocztowy;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Adres>) stream.readObject();
    }

    public static void save() {
        final String file = "adresExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Adres.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "adresExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Adres.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    @Override
    public int compareTo(Adres o) {
        if (o.ulica.equals(this.ulica) && o.miejscowosc.equals(this.miejscowosc)
        && o.numer.equals(this.numer) && o.kodPocztowy.equals(this.kodPocztowy)) {
            return 0;
        }
        return 1;
    }
}
