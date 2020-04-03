package citywithdb;
import java.util.Scanner;
import java.sql.*;
public class CityWithDb {
    public static void main(String[] args)throws Exception{
            Connection conn = null;
            String url = "jdbc:derby://localhost:1527/";
            String dbName = "CityDB;create=true";
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String userName = "root"; 
            String password = "1111";
            // Create a Scanner object for keyboard input.
            Scanner keyboard = new Scanner(System.in);

        try{
            //Create a connection to the database.
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName);
            String sql;
            // Create a Statement object.
            Statement stmt = conn.createStatement(); 
            // Create the CityDB table. Task 0 
            System.out.println("Creating the CityDB table...");  
            sql = "CREATE TABLE CityDB (CityName CHAR(25) NOT NULL PRIMARY KEY, Population float)" ;
            // Execute the statement. 
            stmt.execute(sql);
// ********************************************************************************************************************************
            sql="INSERT INTO CityDB VALUES('Riyadh', 5188000)" ; // Task 1
            stmt.executeUpdate(sql);
            sql="INSERT INTO CityDB VALUES('Makkah', 1535000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO CityDB VALUES('Medina', 1323000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO CityDB VALUES('Jeddah', 3431000)" ;
            stmt.executeUpdate(sql);
            sql="INSERT INTO CityDB VALUES('Dammam', 903300)";
            stmt.executeUpdate(sql);
// ********************************************************************************************************************************           
            String sqlStatement = "SELECT * FROM CityDB"; // Task 2
            // Send the statement to the DBMS.
            ResultSet result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.

            while (result.next()) {
                // Display a row from the result set.
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));
                 }
            System.out.println(" more than 1.5 milion"); // Task 3
            sqlStatement = "SELECT * FROM CityDB WHERE Population >=1500000"; 
            // Send the statement to the DBMS.
             result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.
            while (result.next()) {
            // Display a row from the result set.
                System.out.printf("%25s %5.2f\n",
                result.getString("CityName"), result.getDouble("Population"));

            }
// ********************************************************************************************************************************   
            int minPop;
            System.out.print("Enter the minimum poplution: ");   // Task 4
            minPop = keyboard.nextInt();
            // Create a string with a SELECT statement.
            sqlStatement = "SELECT * FROM CityDB WHERE Population >= " +minPop;
            // Send the statement to the DBMS.
            result = stmt.executeQuery(sqlStatement);
            // Display the contents of the result set.
            while (result.next()) {
            // Display a row from the result set.
            System.out.printf("%25s %5.2f\n",
            result.getString("CityName"), result.getDouble("Population"));
            }
// ********************************************************************************************************************************            
            String city;
            double Population;
            // Get the data for the new cityDB.
            System.out.print("Enter the Riyadh city  to update the Population: "); // Task 5
            city = keyboard.next();
            // Create a SELECT statement to get the specified
            sqlStatement ="SELECT * FROM CityDB WHERE CityName = '" + city + "'";
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
                 sqlStatement = "UPDATE CityDB SET Population = " +Population+ " WHERE CityName = '" +city+ "'" ;
            // Send the UPDATE statement to the DBMS. 
            int rows = stmt.executeUpdate(sqlStatement);
            // Display the results.
            System.out.println(rows + " row(s) updated.");
// ********************************************************************************************************************************
          System.out.print("Enter the Dammam city  to delete: ");// Task 6
           city = keyboard.next();
          sqlStatement ="SELECT * FROM CityDB WHERE CityName = '" + city + "'";
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
         sqlStatement = "DELETE FROM CityDB WHERE CityName = '" + city + "'";

         // Send the DELETE statement to the DBMS. 
         rows = stmt.executeUpdate(sqlStatement);
         // Display the results.
         System.out.println(rows + " row(s) deleted.");
// ********************************************************************************************************************************
         System.out.println("inserting time :)"); // Task 7
         System.out.print("Enter the city name: ");
         city = keyboard.next();
         System.out.print("Enter the Population: ");
         Population = keyboard.nextDouble();
         // Create a Statement object.
         stmt = conn.createStatement();
         // Create a string with a INSERT statement
         sqlStatement = "INSERT INTO CityDB VALUES('" +city + "', "+Population + ")";
         // Send the statement to the DBMS.
          rows = stmt.executeUpdate(sqlStatement);
          // Display the results.
         System.out.println(rows + " row(s) added to the table.");
         // display all database
         sqlStatement = "SELECT * FROM CityDB"; 
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
    
}
