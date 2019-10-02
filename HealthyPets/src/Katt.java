public class Katt extends Djur {

    // Inkapsling
    private String foder = foderTyp.KATTFODER.toString().toLowerCase() + ".";

    public Katt(String namn, int vikt) {
        super(namn, vikt);
    }

    @Override
    public int foder() {
        int måltid = Math.round(getVikt() / 150);
        return måltid;
    }

    public String getFoder() {
        return foder;
    }
}
