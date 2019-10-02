public class Hund extends Djur {

    // Inkapsling
    private String foder = foderTyp.HUNDFODER.toString().toLowerCase() + ".";

    public Hund(String namn, int vikt) {
        super(namn, vikt);
    }

    @Override
    public int foder() {
        int måltid = Math.round(getVikt() / 100);
        return måltid;
    }

    public String getFoder() {
        return foder;
    }
}
