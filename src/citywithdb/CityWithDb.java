package citywithdb;
import java.util.Scanner;
import java.sql.*;
public class CityWithDb {
    public static void main(String[] args)throws Exception{
            // connection with DB
            Connection conn = null;
            String url = "jdbc:derby://localhost:1527/";
            String dbName = "CityDB;create=true";
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String userName = "root"; 
            String password = "1111";
            
            // Variables
            ResultSet result;
            Statement stmt;
            int rows;
            String sql;
            String sqlStatement;
            int minPop;
            String city;
            double Population;
            // Create a Scanner object for keyboard input.
            Scanner keyboard = new Scanner(System.in);

        try{
            //Create a connection to the database.
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName);
            // Create a Statement object.
            stmt = conn.createStatement(); 
            // task 0
            CityWithDb.display("Task 0: Create a database called CityDB"); 
            // Create the DealWithCityDB table.
            System.out.println("Creating the DealWithCityDB table...");  
            sql = "CREATE TABLE DealWithCityDB (CityName CHAR(25) NOT NULL PRIMARY KEY, Population float)" ;
            // Execute the statement. 
            stmt.execute(sql);
            
            // Task 1
            CityWithDb.display("Task 1: Insert the following 5 rows \n"
                    + "\tto the database"); 
            sql="INSERT INTO DealWithCityDB VALUES('Riyadh', 5188000)" ; 
            stmt.executeUpdate(sql);
            sql="INSERT INTO DealWithCityDB VALUES('Makkah', 1535000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO DealWithCityDB VALUES('Medina', 1323000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO DealWithCityDB VALUES('Jeddah', 3431000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO DealWithCityDB VALUES('Dammam', 903300)";
            stmt.executeUpdate(sql);
            System.out.println("add 5 Item in row......"); 
            // Task 2
            CityWithDb.display("Task 2: Display all the data in database"); 
            sqlStatement = "SELECT * FROM DealWithCityDB"; 
            // Send the statement to the DBMS.
            result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.

            while (result.next()) {
                // Display a row from the result set.
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));
                 }

            // Task 3
            CityWithDb.display("Task 3: Display all the cities that have\n"
                    + "\tmore than 1.5 millions citizen."); 
            System.out.println(" more than 1.5 milion");
            sqlStatement = "SELECT * FROM DealWithCityDB WHERE Population >=1500000"; 
            // Send the statement to the DBMS.
             result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.
            while (result.next()) {
            // Display a row from the result set.
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));

            }
            
            // Task 4
            CityWithDb.display("Task 4: Ask the user to enter the minimum number\n "
                    + "\tof population and display all the cities that\n"
                    + "\thave more than this minimum number."); 
            System.out.print("Enter the minimum poplution: ");
            minPop = keyboard.nextInt();
            // Create a string with a SELECT statement.
            sqlStatement = "SELECT * FROM DealWithCityDB WHERE Population >= " +minPop;
            // Send the statement to the DBMS.
            result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.
            while (result.next()) {
            // Display a row from the result set.
            System.out.printf("%25s %5.2f\n",
            result.getString("CityName"), result.getDouble("Population"));
            }

            // Task 5
            CityWithDb.display("Task 5: Ask the user to update the population\n"
                    + "\tfor Riyadh city and display the updated value."); 
            // Get the data for the new DealWithCityDB.
            System.out.print("Enter the Riyadh city  to update the Population: ");
            city = keyboard.next();
            // Create a SELECT statement to get the specified
            sqlStatement ="SELECT * FROM DealWithCityDB WHERE CityName = '" + city + "'";
            // Create a Statement object.
            stmt = conn.createStatement();
            // Send the SELECT statement to the DBMS.
            result = stmt.executeQuery(sqlStatement);
            if (result.next()){
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));
            }
            else{
                System.out.println(" The city not found");
            }
            System.out.print("Enter the new Population: ");
            Population = keyboard.nextDouble();
            // Create an UPDATE statement to update the price
                 sqlStatement = "UPDATE DealWithCityDB SET Population = " +Population+ " WHERE CityName = '" +city+ "'" ;
            // Send the UPDATE statement to the DBMS. 
            rows = stmt.executeUpdate(sqlStatement);
            // Display the results.
            System.out.println(rows + " row(s) updated.");

            // Task 6
            CityWithDb.display("Task 6:Delete Dammam city."); 
          System.out.print("Enter the Dammam city  to delete: ");
           city = keyboard.next();
          sqlStatement ="SELECT * FROM DealWithCityDB WHERE CityName = '" + city + "'";
            // Create a Statement object.
            stmt = conn.createStatement();
            // Send the SELECT statement to the DBMS.
            result = stmt.executeQuery(sqlStatement);
            if (result.next()){
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));
            }
            else{
                System.out.println(" The city not found");
            }
         // Create an Delete statement to update the price
         sqlStatement = "DELETE FROM DealWithCityDB WHERE CityName = '" + city + "'";

         // Send the DELETE statement to the DBMS. 
         rows = stmt.executeUpdate(sqlStatement);
         // Display the results.
         System.out.println(rows + " row(s) deleted.");

         // Task 7
            CityWithDb.display("Task 7: Ask the user to insert a new city with\n"
                    + "\tpopulation and display the final database."); 
         System.out.print("Enter the city name: ");
         city = keyboard.next();
         System.out.print("Enter the Population: ");
         Population = keyboard.nextDouble();
         // Create a Statement object.
         stmt = conn.createStatement();
         // Create a string with a INSERT statement
         sqlStatement = "INSERT INTO DealWithCityDB VALUES('" +city + "', "+Population + ")";
         // Send the statement to the DBMS.
         rows = stmt.executeUpdate(sqlStatement);
          // Display the results.
         System.out.println(rows + " row(s) added to the table.");
         // display all database
         sqlStatement = "SELECT * FROM DealWithCityDB"; 
            // Send the statement to the DBMS.
             result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.

            while (result.next()) {
                // Display a row from the result set.
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));
                 }

            // Close the resources. 
            stmt.close();
            conn.close(); 
            System.out.println("Done");
            }catch(Exception ex) {
            System.out.println("ERROR: " + ex.getMessage()); 	
		} 


    }
    
  
    public static void display(String result) {
       System.out.println("\n********************************************\n"
                + result+" \n"
                + "********************************************");
    }
    
}
