import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class Kund {
    protected String personnr;
    protected String förNamn;
    protected String efterNamn;
    protected LocalDate senasteBetalning;
    protected LocalDate ettÅrEfterSenasteBetalning;

    @Override
    public String toString() {
        return "Personummer: " + personnr + '\n' +
                "Förnamn: " + förNamn + '\n' +
                "Efternamn: " + efterNamn + '\n' +
                "Senaste betalat: " + senasteBetalning + '\n' +
                ärKundAktuell();
    }

    public Kund(String personnr, String förNamn, String efterNamn, LocalDate senasteBetalning) {
        this.personnr = personnr;
        this.förNamn = förNamn;
        this.efterNamn = efterNamn;
        this.senasteBetalning = senasteBetalning;
    }

    public String ärKundAktuell() {
        ettÅrEfterSenasteBetalning = senasteBetalning.plus(1, YEARS);
        if (ettÅrEfterSenasteBetalning.isAfter(LocalDate.now()))
            return "Kunden har betalat årsavgiften!";
        else
            return "Kunden har inte betalat årsavgiften!";
    }
}
