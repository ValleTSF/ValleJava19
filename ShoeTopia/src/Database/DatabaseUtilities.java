package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseUtilities {

    Connection con;
    PreparedStatement preparedStatement;
    CallableStatement callableStatement;
    Statement statement;
    List<Customer> customerList = new ArrayList<>();
    List<Shoe> shoeList = new ArrayList<>();
    Map<Integer, Shoe> shoeMap = new HashMap<>();
    Map<Integer, Brand> brandMap = new HashMap<>();
    String customerFirstName;
    String customerLastName;
    String customerPassword;
    String customerID;
    String customerCityID;
    String shoeID;
    String shoeName;
    int shoeSize;
    String shoeColor;
    String shoeBrandID;
    int shoePrice;
    int shoeQuantity;

    public void startDatabaseConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/Database/DatabaseInformation.properties"));

            con = DriverManager.getConnection(properties.getProperty("connectionString"),
                    properties.getProperty("name"),
                    properties.getProperty("password"));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT ID, FirstName, LastName, CityID, Password FROM shoewebshop.customer");

        while (rs.next()) {
            customerID = rs.getString("ID");
            customerFirstName = rs.getString("FirstName");
            customerLastName = rs.getString("LastName");
            customerCityID = rs.getString("CityID");
            customerPassword = rs.getString("Password");

            customerList.add(new Customer(customerID,
                    customerFirstName,
                    customerLastName,
                    getCity(customerCityID),
                    customerPassword));

        }
        for (Customer customer : customerList) {
            System.out.println(customer.getId() + " " + customer.getFirstName() + " " +
                    customer.getLastName() + " " + customer.getCity().getName() + " " + customer.getPassword());
        }

        getAllShoes();
        getAllBrands();

    }

    public Boolean logIn(String accountID, String accountPassword) throws SQLException {
        String checkAccountID;
        String checkAccountPassword;

        preparedStatement = con.prepareStatement("SELECT ID, Password FROM shoewebshop.Customer\n" +
                "WHERE ID = ?\n" +
                "  AND Password = ?;");
        preparedStatement.setString(1, accountID);
        preparedStatement.setString(2, accountPassword);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            checkAccountID = rs.getString(1);
            checkAccountPassword = rs.getString(2);
            System.out.println(checkAccountID + checkAccountPassword);
            for (Customer customer : customerList) {
                if (customer.getId().equalsIgnoreCase(checkAccountID) &&
                        customer.getPassword().equalsIgnoreCase(checkAccountPassword))
                    return true;
            }
        }
        return false;
    }

    public City getCity(String cityID) throws SQLException {
        String cityName;
        PreparedStatement stmt = con.prepareStatement("SELECT name FROM shoewebshop.city WHERE id = ?");
        stmt.setString(1, cityID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            cityName = rs.getString("name");
            return new City(cityID, cityName);
        }
        return null;
    }

    public String[] viewShoes() throws SQLException {
        shoeList.clear();
        statement = con.createStatement();
        shoeMap.entrySet().stream().filter(shoe -> shoe.getValue().getQuantity() > 0).forEach(shoe -> shoeList.add(shoe.getValue()));

        String[] shoeListStringArray = new String[shoeList.size()];
        int i = 0;
        for (Shoe shoe : shoeList) {
            shoeListStringArray[i] = shoe.getName() +
                    ", " + shoe.getBrand().getName() +
                    ", " + shoe.getSize() +
                    ", " + shoe.getColor() +
                    ", " + shoe.getPrice() + " Kr";
            i++;
        }

        return shoeListStringArray;
    }

    public boolean addToCart(String thisCustomerID, String thisShoeID, int thisQuantity) throws SQLException {
        callableStatement = con.prepareCall("CALL shoewebshop.addToCart(?,?,?)");
        callableStatement.setInt(1, Integer.parseInt(thisCustomerID));
        callableStatement.setInt(2, Integer.parseInt(thisShoeID));
        callableStatement.setInt(3, thisQuantity);
        ResultSet rs = callableStatement.executeQuery();
        System.out.println("rs = " + rs);

        if (rs.toString().equalsIgnoreCase("Result set representing update count of 0"))
            return true;
        else
            return false;
    }

    public String getShoeID(String shoeName) {
        for (Shoe shoe : shoeList) {
            System.out.println(shoe.getName());
            if (shoeName.equalsIgnoreCase(shoe.getName())) {
                System.out.println(shoe.getId());
                return shoe.getId();
            }
        }
        return null;
    }

    public List<Shoe> viewCart(String thisCustomerID) throws SQLException {
        shoeList.clear();
        callableStatement = con.prepareCall("CALL shoewebshop.viewCart(?)");
        callableStatement.setInt(1, Integer.parseInt(thisCustomerID));
        ResultSet rs = callableStatement.executeQuery();

        return getShoeInformation(rs);
    }

    public List<Shoe> getShoeInformation(ResultSet rs) throws SQLException {
        while (rs.next()) {
            shoeID = rs.getString("ID");
            shoeName = rs.getString("Name");
            shoeSize = rs.getInt("Size");
            shoeColor = rs.getString("Color");
            shoeBrandID = rs.getString("BrandID");
            shoePrice = rs.getInt("Price");
            shoeQuantity = rs.getInt("Quantity");

            shoeList.add(new Shoe(shoeID,
                    shoeName,
                    shoeSize,
                    shoeColor,
                    shoePrice,
                    brandMap.get(Integer.parseInt(shoeBrandID)),
                    shoeQuantity));

        }
        return shoeList;
    }

    public Map<Integer,Shoe> getAllShoes() throws SQLException {
        int i = 1;
        brandMap = getAllBrands();

        statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM shoewebshop.shoe");

        while (rs.next()) {
            shoeID = rs.getString("ID");
            shoeName = rs.getString("Name");
            shoeSize = rs.getInt("Size");
            shoeColor = rs.getString("Color");
            shoeBrandID = rs.getString("BrandID");
            shoePrice = rs.getInt("Price");
            shoeQuantity = rs.getInt("Quantity");

            shoeMap.put(Integer.parseInt(shoeID), new Shoe(shoeID,
                    shoeName,
                    shoeSize,
                    shoeColor,
                    shoePrice,
                    brandMap.get(Integer.parseInt(shoeBrandID)),
                    shoeQuantity));
            System.out.println(shoeMap.get(i).getName());
            i++;
        }
        return shoeMap;
    }

    public Map<Integer, Brand> getAllBrands() throws SQLException {
        int i = 1;
        Map<Integer, Brand> brandMap = new HashMap<>();

        statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM shoewebshop.brand");

        while (rs.next()) {
            String brandID = rs.getString("ID");
            String brandName = rs.getString("Name");

            brandMap.put(Integer.parseInt(brandID), new Brand((brandID), brandName));
            System.out.println(brandMap.get(i).getName());
            i++;
        }
        return brandMap;
    }

}
