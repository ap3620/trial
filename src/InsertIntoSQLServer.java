/*public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connect to SQL Server and execute an INSERT command.
 *
 */
public class InsertIntoSQLServer {
    //The SQL Server JDBC Driver is in
    //C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\auth\x64
    private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //The JDBC connection URL which allows for Windows authentication is defined below.
    private static final String jdbcURL = "jdbc:sqlserver://localhost:1433;databasename=AdventureWorks2014;integratedSecurity=true;";
    //To make Windows authenticaion work we have to set the path to sqljdbc_auth.dll at the command line

    /**
     * main method.
     *
     * @param  args  command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Program started");
        try
        {
            Class.forName(jdbcDriver).newInstance();
            System.out.println("JDBC driver loaded");
        }
        catch (Exception err)
        {
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        }

        Connection databaseConnection= null;
        try
        {
            //Connect to the database
            databaseConnection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to the database");
        }
        catch (SQLException err)
        {
            System.err.println("Error connecting to the database");
            err.printStackTrace(System.err);
            System.exit(0);
        }

        try
        {
            //declare the statement object
            Statement sqlStatement = databaseConnection.createStatement();

            //Build the command string
            String commandString="insert into [users_].[username] values";
            commandString+="(hello)";

            //print the command string to the screen
            System.out.println("\nCommand string:");
            System.out.println(commandString);

            //execute the command using the execute method
            sqlStatement.execute(commandString);

            System.out.println("Closing database connection");

            //close the database connection
            databaseConnection.close();
        }
        catch (SQLException err)
        {
            System.err.println("SQL Error");
            err.printStackTrace(System.err);

            System.exit(0);
        }
        System.out.println("Program finished");
    }
}
