import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Adres adres1 = new Adres("Polna", "Polno", "13", "11-111");
        Osoba osoba = new Osoba("ja", "ja", adres1, "123", "email", 1, EnumSet.of(Typ.Klient));
        Osoba osoba1 = new Osoba("Kuba", "Kubowski", adres1, "123", "email", 1, EnumSet.of(Typ.Klient));
        Osoba.save();
        Adres adres2 = new Adres("Dolna", "Wysypki", "8", "13-589");
        Adres adres3 = new Adres("Wolna", "Wolnowo", "1", "11-111");
        Adres adres4 = new Adres("Kolna", "Kolnowo", "12", "12-543");


        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();
        Menu menu4 = new Menu();

        Danie danie1 = Danie.stworzDanie(menu1,"Burger",new BigDecimal("12.50"),200);
        Danie danie2 = Danie.stworzDanie(menu1,"Pizza",new BigDecimal("25.99"),200);
        Danie danie3 = Danie.stworzDanie(menu1,"Spaghetti",new BigDecimal("18.99"),200);

        Danie danie4 = Danie.stworzDanie(menu2,"Pierogi ruskie",new BigDecimal("12.50"),12);
        Danie danie5 = Danie.stworzDanie(menu2,"Pierogi z mięsem",new BigDecimal("15.50"),12);
        Danie danie6 = Danie.stworzDanie(menu2,"Pierogi ze śmietaną",new BigDecimal("10.50"),12);

        Danie danie7 = Danie.stworzDanie(menu3,"Pad thai z kurczakiem",new BigDecimal("22.60"),12);
        Danie danie8 = Danie.stworzDanie(menu3,"Pad thai z tofu",new BigDecimal("20.30"),12);
        Danie danie9 = Danie.stworzDanie(menu3,"Pad thai z wołowiną",new BigDecimal("25.50"),12);

        Danie danie10 = Danie.stworzDanie(menu4,"Espresso",new BigDecimal("6.66"),12);
        Danie danie11 = Danie.stworzDanie(menu4,"Cappuccino",new BigDecimal("12.50"),12);
        Danie danie12 = Danie.stworzDanie(menu4,"Americana",new BigDecimal("12"),12);
        Danie.save();


        Restauracja res1 = new Restauracja("Fast Food", adres1, "123 456 789", "000",
                "fastFood@email.com", 69, 20, menu1 , BigDecimal.valueOf(12));
        Restauracja res2 = new Restauracja("Pierogarnia", adres2, "098 765 432", "111",
                "pierogarnia@email.com", 50, 20, menu2,BigDecimal.valueOf(24));
        Restauracja res3 = new Restauracja("Viet cuisine", adres3, "092 923 012", "222",
                "vietcuisine@email.com", 100, 20, menu3,BigDecimal.valueOf(36));
        Restauracja res4 = new Restauracja("Kawiarnia", adres4, "518 123 123", "333",
                "kawiarnia@email.com", 60, 20, menu4,BigDecimal.valueOf(48));

        menu1.przypiszRestauracje(res1);
        menu2.przypiszRestauracje(res2);
        menu3.przypiszRestauracje(res3);
        menu4.przypiszRestauracje(res4);

        Restauracja.save();

        Zamówienie.load();
        List<Zamówienie> zamówienies = Zamówienie.getExtent();
        System.out.println(zamówienies.size());
        for (Zamówienie z : zamówienies) {
            System.out.println(z);
        }

        Restauracja.load();

        JFrame jFrame = new LoginFrame("Zaloguj");
        jFrame.setVisible(true);
    }

}
