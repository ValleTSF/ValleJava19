public class Orm extends Djur {

    // Inkapsling
    private String foder = foderTyp.ORMPELLETS.toString().toLowerCase() + ".";

    public Orm(String namn, int vikt) {
        super(namn, vikt);
    }

    @Override
    public int foder() {
        int måltid = 20;
        return måltid;
    }

    public String getFoder() {
        return foder;
    }
}
