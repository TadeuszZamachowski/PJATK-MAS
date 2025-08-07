import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Status {Przyjęte, W_Realizacji, Anulowane, W_Dostawie, Zakończone}
public class Zamówienie implements Serializable {

    private int id;
    private LocalDate dataPrzyjecia;
    private Adres adres;
    private BigDecimal kwotaCalkowita;
    private Osoba osoba;
    private List<SkładZamówienia> składZamówienia = new ArrayList<>();
    private Status status;
    private Opinia opinia;
    private static List<Zamówienie> extent = new ArrayList<>();

    public Zamówienie(int id, Adres adres, BigDecimal kwotaCalkowita, Osoba osoba) {
        this.id = id;
        this.dataPrzyjecia = LocalDate.now();
        this.adres = adres;
        this.kwotaCalkowita = kwotaCalkowita;
        setOsoba(osoba);

        extent.add(this);
    }

    public void setStatus(Status s) throws Exception {
        if(status == Status.Anulowane || status == Status.Zakończone) {
            throw new Exception("Nie można zmienić statusu anulowanego/zakończonego zamówienia");
        }
        status = s;
    }

    public void setSkładZamówienia(SkładZamówienia sZ) {
        if (!składZamówienia.contains(sZ)) {
            składZamówienia.add(sZ);

            sZ.setZamówienie(this);
        }
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
        osoba.addZamówienie(this);
    }


    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setOpinia(Opinia opinia) {
        this.opinia = opinia;
    }

    public Opinia getOpinia(){
        return opinia;
    }

    public List<SkładZamówienia> getSkładZamówienia() {
        return this.składZamówienia;
    }

    public BigDecimal getKwotaCalkowita() {
        return kwotaCalkowita;
    }

    public int getId() {
        return id;
    }

    public Osoba getOsoba() {
        return this.osoba;
    }

    public Adres getAdres() {
        return adres;
    }
    public static List<Zamówienie> getExtent() {
        return extent;
    }

    public String toString() {
        return "id:" + id + "\n" + "Klient: " + osoba + "\n" + "Opinia: " + opinia + "\n" + dataPrzyjecia + "\n" + "Status: " + status;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Zamówienie>) stream.readObject();
    }

    public static void save() {
        final String file = "zamowienieExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Zamówienie.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "zamowienieExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Zamówienie.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
