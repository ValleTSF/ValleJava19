import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String input;
        HusdjursHotell hotell = new HusdjursHotell();
        Djur valtDjur;

        while (true) {

            input = JOptionPane.showInputDialog("Vilket djur ska du mata?");

            if (input == null)
                System.exit(0);
            
            valtDjur = hotell.findDjur(input);

            // Ifall den inte hittar betyder det att djuret inte finns på hotellet eller att användaren skrivit fel.
            if (valtDjur == null)
                JOptionPane.showMessageDialog(null, "Djuret finns inte på hotellet!");

            // If satser som körs beroende på vad det valda djuret är för typ.
            else {
                if (valtDjur instanceof Hund) {
                    int mängdMat = valtDjur.foder();
                    JOptionPane.showMessageDialog(null, "Hunden " + valtDjur.getNamn() + " väger " + valtDjur.getVikt() +
                            "g och ska ha " + mängdMat + "g " + ((Hund) valtDjur).getFoder());
                    break;

                } else if (valtDjur instanceof Katt) {
                    int mängdMat = valtDjur.foder();
                    JOptionPane.showMessageDialog(null, "Katten " + valtDjur.getNamn() + " väger " + valtDjur.getVikt() +
                            "g och ska ha " + mängdMat + "g " + ((Katt) valtDjur).getFoder());
                    break;

                } else if (valtDjur instanceof Orm) {
                    int mängdMat = valtDjur.foder();
                    JOptionPane.showMessageDialog(null, "Ormen " + valtDjur.getNamn() + " väger " + valtDjur.getVikt() +
                            "g och ska ha " + mängdMat + "g " + ((Orm) valtDjur).getFoder());
                    break;
                }
            }
        }
    }
}


