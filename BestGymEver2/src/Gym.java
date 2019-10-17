import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gym {

    private String[] split;
    private String personnr;
    private String förNamn;
    private String efterNamn;
    private LocalDate senasteBetalning;
    private List<Kund> kunder = new ArrayList<>();

    private Path path = Paths.get("C:\\Users\\Public\\Documents\\My Java Projects\\BestGymEver2\\customers.txt");

    public boolean frånFilToList() {

        try (Scanner sc = new Scanner(path)) {
            while (sc.hasNext()) {
                kunder.add(new Kund(sc.next().replace(",", ""),
                        sc.next(), sc.next(), LocalDate.parse(sc.next())));
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Filen kunde inte hittas!");
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ett fel uppstod!");
            return false;
        }
        return true;
    }

    public String findKund(String input) {

        if (input.matches("\\d+"))
            for (Kund kund : kunder) {
                if (kund.personnr.equals(input)) {
                    sparaBesökTillFil(kund);
                    return kund.toString();
                }
            }
        else {
            split = input.split(" ");
            for (Kund kund : kunder) {
                if (kund.förNamn.equalsIgnoreCase(split[0]) && kund.efterNamn.equalsIgnoreCase(split[1])) {
                    sparaBesökTillFil(kund);
                    return kund.toString();
                }
            }
        }
        return "Personen är inte medlem!";
    }

    public void sparaBesökTillFil(Kund kund) {

        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter
                ("C:\\Users\\Public\\Documents\\My Java Projects\\BestGymEver2\\" + kund.förNamn + kund.efterNamn + ".txt", true))) {
            bufWriter.write(kund.personnr + " " + kund.förNamn + " " + kund.efterNamn +
                    " har tränat " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")) + '\n');
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(null, "Filen finns redan!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ett fel uppstod!");
        }
    }

    public void addKund() {

        while (true) {
            try {
                personnr = JOptionPane.showInputDialog(null, "Ange personnummer").replace("-", "")
                        .replace("/", "");
                if (personnr.trim().length() > 12) {
                    JOptionPane.showMessageDialog(null, "Inkorrekt format på personnummer \n" +
                            "Ange (YYYY/MM/DD-****) eller (YY/MM/DD-****");
                    continue;
                } else if (personnr.trim().length() == 12)
                    personnr = personnr.substring(2);

                förNamn = JOptionPane.showInputDialog(null, "Ange förnamn");
                efterNamn = JOptionPane.showInputDialog(null, "Ange efternamn");
                senasteBetalning = LocalDate.now();
                kunder.add(new Kund(personnr.trim(), förNamn.trim(), efterNamn.trim(), senasteBetalning));

            } catch (NullPointerException e) {
                System.exit(0);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ett fel uppstod");
                continue;
            }
            try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter
                    ("C:\\Users\\Public\\Documents\\My Java Projects\\BestGymEver2\\customers.txt", true))) {
                bufWriter.write( '\n' + personnr + ", " + förNamn + " " + efterNamn + '\n' + LocalDate.now());
            } catch (FileAlreadyExistsException e) {
                JOptionPane.showMessageDialog(null, "Filen finns redan!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ett fel uppstod!");
            }
            if (frånFilToList()) {
                JOptionPane.showMessageDialog(null, "Välkommen till Best Gym Ever!");
                break;
            }
        }
    }
}