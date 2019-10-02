abstract class Djur implements IFoder {

    // Inkapsling
    private int vikt;
    private String namn;

    public Djur(String namn, int vikt) {
        this.namn = namn;
        this.vikt = vikt;
    }

    String getNamn() {
        return namn;
    }

    int getVikt() {
        return vikt;
    }
}
