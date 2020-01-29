import View.CustomerView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        CustomerView cv = new CustomerView();
        cv.instantiateLogInWindow();
    }
}

