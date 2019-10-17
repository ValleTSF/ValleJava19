import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String hittadKund;
        Gym gym = new Gym();
        gym.frånFilToList();

        while (true) {
            String input = JOptionPane.showInputDialog(null, "Vänligen skriv in kundens namn eller personnummer!",
                    "", JOptionPane.QUESTION_MESSAGE);
            if (input == null)
                System.exit(0);

            else if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inkorrekt inmatning!");
                continue;
            }

            hittadKund = gym.findKund(input.trim());
            JOptionPane.showMessageDialog(null, hittadKund);

            if (hittadKund.equals("Personen är inte medlem!")) {
                int knapp = JOptionPane.showConfirmDialog(null, "Vill personen bli medlem?", "Best Gym Ever", JOptionPane.YES_NO_OPTION);
                if (knapp == 0) {
                    gym.addKund();
                    continue;
                } else if (knapp == 1)
                    continue;
                else if (knapp == 2) ;
                System.exit(0);
            }
        }
    }
}
