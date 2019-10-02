import java.util.ArrayList;
import java.util.List;

public class HusdjursHotell {

    // Polymorfism
    Djur sixten = new Hund("Sixten", 5000);
    Djur dogge = new Hund("Dogge", 10_000);
    Djur venus = new Katt("Venus", 5000);
    Djur ove = new Katt("Ove", 3000);
    Djur hypno = new Orm("Hypno", 1000);

    // Inkapslad lista
    private List<Djur> djurList = new ArrayList<>() {{
        add(sixten);
        add(dogge);
        add(venus);
        add(ove);
        add(hypno);
    }};

    // findDjur tar input och letar i våran inkapslade djurlista efter ett objekt som innehåller samma sträng.
    public Djur findDjur(String input) {
        for (Djur djur : djurList) {
            if (djur.getNamn().equalsIgnoreCase(input)) {
                return djur;
            }
        }
        return null;
    }
}
