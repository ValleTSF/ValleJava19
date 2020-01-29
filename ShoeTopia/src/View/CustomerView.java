package View;

import Database.DatabaseUtilities;
import Database.Shoe;


import javax.swing.*;
import java.sql.SQLException;


public class CustomerView {

    String accountName;
    String accountPassword;
    DatabaseUtilities du;
    int result;
    String cartString;

    public void instantiateLogInWindow() throws SQLException {

        du = new DatabaseUtilities();
        du.startDatabaseConnection();

        while (true) {
            JTextField accountField = new JTextField(7);
            JPasswordField passwordField = new JPasswordField(7);
            JPanel myPanel = new JPanel();

            myPanel.add(new JLabel("Account:"));
            myPanel.add(accountField);
            myPanel.add(new JLabel("Password:"));
            myPanel.add(passwordField);

            result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Welcome to ShoeTopia!", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {

                accountName = accountField.getText();
                accountPassword = String.copyValueOf(passwordField.getPassword());

                if (du.logIn(accountName, accountPassword)) {
                    JOptionPane.showMessageDialog(null, "Log in Successful!");
                    loggedInMenu();
                } else
                    JOptionPane.showMessageDialog(null, "Log in Unsuccessful");
            } else
                System.exit(0);
        }

    }

    public void loggedInMenu() throws SQLException {
        while (true) {
            String[] options = {"View Shoes", "View Cart", "Log Out", "Exit"};

            JFrame frame = new JFrame("Shopping");
            String choice = (String) JOptionPane.showInputDialog(frame,
                    "Main Menu",
                    "ShoeTopia",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == null)
                System.exit(0);

            switch (choice) {
                case "Log Out":
                    break;

                case "View Shoes":
                    String[] viewShoes_options = du.viewShoes();
                    System.out.println(viewShoes_options);


                    frame = new JFrame("Shopping");
                    choice = (String) JOptionPane.showInputDialog(frame,
                            "Main Menu",
                            "ShoeTopia",
                            JOptionPane.QUESTION_MESSAGE,
                            null, viewShoes_options
                            , viewShoes_options[0]);

                    if (choice == null)
                        continue;

                    result = JOptionPane.showConfirmDialog(null,
                            "Do you want to add " + choice + " to your Cart?");
                    switch (result) {
                        case 0:
                            if (du.addToCart(accountName, du.getShoeID(
                                    choice.substring(0, choice.indexOf(','))), 1))
                                JOptionPane.showMessageDialog(null, choice.substring(0, choice.indexOf(',')) + " was added to the cart.");
                            else
                                JOptionPane.showMessageDialog(null, "Out of stock!");
                            break;
                        default:
                            continue;
                    }
                    break;

                case "View Cart":
                    cartString = "";
                    for (Shoe shoe : du.viewCart(accountName)) {
                        cartString += shoe.getName() +
                                ", " + shoe.getBrand().getName() +
                                ", " + shoe.getSize() +
                                ", " + shoe.getColor() +
                                ", " + shoe.getPrice() + " Kr \n";
                    }
                    JOptionPane.showMessageDialog(null, cartString);
                    break;

                default:
                    System.exit(0);
            }

            System.out.printf("choice: ", choice);

        }

    }
}

