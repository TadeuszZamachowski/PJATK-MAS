import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

enum Typ {Osoba, Pracownik, Klient};
public class Osoba implements Serializable {
    private String imie;
    private String nazwisko;
    private Adres adres;
    private String numerTelefonu;
    private String email;
    private int id;
    private LocalDate dataZatrudnienia;
    private float stawka;
    private List<Zamówienie> zamowienia = new ArrayList<>();
    private List<Opinia> opinie = new ArrayList<>();
    private EnumSet<Typ> typ = EnumSet.of(Typ.Osoba);
    private static List<Osoba> extent = new ArrayList<>();

    public Osoba(String imie, String nazwisko, Adres adres, String numerTelefonu, String email, int id,
                 EnumSet<Typ> typ) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.numerTelefonu = numerTelefonu;
        this.email = email;
        this.id = id;
        this.typ = typ;

        extent.add(this);
    }

    public Adres getAdres() {
        return adres;
    }

    public void setDataZatrudnienia(LocalDate date) throws Exception {
        if (typ.contains(Typ.Pracownik)) {
            dataZatrudnienia = date;
        } else {
            throw new Exception("To nie jest pracownik");
        }
    }

    public void setStawka(float kwota) throws Exception {
        if (typ.contains(Typ.Pracownik)) {
            stawka = kwota;
        } else {
            throw new Exception("To nie jest pracownik");
        }
    }

    public static Osoba znajdźOsobę(String _imie, String _nazwisko) {
        Osoba osoba = null;
        for (Osoba o : extent) {
            if (o.imie.equals(_imie) && o.nazwisko.equals(_nazwisko)) {
                osoba = o;
            }
        }
        return osoba;
    }

    public String toString() {
        return imie + " " + nazwisko;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void addZamówienie(Zamówienie zamówienie) {
        zamowienia.add(zamówienie);
    }

    public void addOpinie(Opinia opinia) {
        opinie.add(opinia);
    }

    public Opinia znajdźOpinieDoZamówienia(Zamówienie zamówienie) {

        for (Opinia o : opinie) {
            System.out.println(o);
            if (o.getZamówienie().getId() == zamówienie.getId()) {
                return o;
            }
        }
        return null;
    }

    public void deleteOpinia(Opinia o) {
        opinie.remove(o);
    }

    public void deleteZamówienie(Zamówienie zamówienie) {
        zamowienia.remove(zamówienie);
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Osoba>) stream.readObject();
    }

    public static void save() {
        final String file = "osobaExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Osoba.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "osobaExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Osoba.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
