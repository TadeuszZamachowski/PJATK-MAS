import java.time.LocalDate;
import java.util.EnumSet;

public class Kurier extends Osoba{

    private String sposóbDowozu;
    private LocalDate dataPrawaJazdy;

    public Kurier(String imie, String nazwisko, Adres adres, String numerTelefonu, String email, int id, EnumSet<Typ> typ,
                  String sposóbDowozu, LocalDate dataPrawaJazdy) {
        super(imie, nazwisko, adres, numerTelefonu, email, id, typ);
        this.sposóbDowozu = sposóbDowozu;
        this.dataPrawaJazdy = dataPrawaJazdy;
    }
}
