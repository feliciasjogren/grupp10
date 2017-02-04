package grupp10.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles calls to MYSQL database.
 * Written as an utility class.
 * 
 * @author Robert Östlin
 */
public class DatabaseHandler {
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String DB = "grupp10";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "aaaa";
    
    /**
     * Private constructor which does not allow objects to be created.
     */
    private DatabaseHandler() {
        // private, no objects allowed
    }
    
    /**
     * Retrieves a single value from a table.
     * @author Robert Östlin
     * @param query Query starting with "select"
     * @return the string of the fetched value
     */
    public static String fetchSingle(String query) {
        printQuery(query);
        
        // query must start with select
        if (!query.toLowerCase().startsWith("select")) {
            throw new IllegalArgumentException("Query must start with select, query: '" + query + "'");
        }
        
        // query must include from statement
        String queryLowercase = query.toLowerCase();
        int fromPos = queryLowercase.indexOf("from");
        
        if (fromPos == -1) {
            throw new IllegalArgumentException("Query must include from statement, query: '" + query + "'");
        }
        
        // no commas allowed between select and from
        if (queryLowercase.substring(0, fromPos).contains(",")) {
            throw new IllegalArgumentException("Query must not include commas between select and from, query: '" + query + "'");
        }
        
        // get data from db
        ArrayList<HashMap<String, String>> res = fetchRows(query);
        boolean foundMultipleValues = false;
        
        // check that only one row is found
        if (res.size() == 1) {
            // check that only one column is found
            if (res.get(0).size() == 1) {
                // get value of column
                for (String v : res.get(0).keySet()) {
                    return res.get(0).get(v);
                }
                System.out.println();
            } else {
                foundMultipleValues = true;
            }
        } else if (res.isEmpty()) {
            return "";
        } else {
            foundMultipleValues = true;
        }
        
        // exit program if multiple values are found
        if (foundMultipleValues) {
            System.out.println("fetchSingle(): FATAL: More than one value found.");
            System.out.println("query: " + query);
            System.exit(0);
        }
        return null;
    }
    
    /**
     * Inserts a row into the database
     * @author Robert Östlin
     * @param query Query starting with "insert"
     * @return a boolean whether the insertion was successful.
     */
    public static boolean insert(String query) {
        if (executeStatement(query)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Inserts a row into the database and returns Id
     * @author Robert Östlin
     * @param query Query for insertion
     * @return Returns a String with Id
     */
    public static String insertGetId(String query) {
            Connection con;
            Statement stmt;
            int  id = -1;
            
            try {
                // connect to db
                con = DriverManager.getConnection(connectionJDBC(), USERNAME, PASSWORD);
                stmt = con.createStatement();
                
                // execute query
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                // get id
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    id = rs.getInt(1);
                }
                
                // close connection
                rs.close();
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return id + "";
    }
    
    /**
     * Deletes a row from the database
     * @author Robert Östlin
     * @param query Query starting with "delete"
     * @return a boolean whether the deletion was successful.
     */
    public static boolean delete(String query) {
        if (executeStatement(query)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates a value in the database
     * @author Robert Östlin
     * @param query Query starting with "update"
     * @return a boolean whether the update was successful.
     */    
    public static boolean update(String query) {
        if (executeStatement(query)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Executes an SQL statement
     * @author Robert Östlin
     * @param query SQL statement
     * @return whether the execution was successful.
     */
    private static boolean executeStatement(String query) {
        printQuery(query);
        
        Connection con;
        Statement stmt;
        boolean success = false;
        
        try {
            // connect to database
            con = DriverManager.getConnection(connectionJDBC(), USERNAME, PASSWORD);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }      
        return success;   
    }
    
    /**
     * Fetches rows from the database
     * @author Robert Östlin
     * @param query Query starting with "select"
     * @return ArrayList of HashMaps containing double Strings with fetched data.
     */
    public static ArrayList<HashMap<String, String>> fetchRows(String query) {
        printQuery(query);
        
        ResultSet rs;
        ArrayList<HashMap<String, String>> res = new ArrayList();
        try {
            // connect to database
            Connection con = DriverManager.getConnection(connectionJDBC(), USERNAME, PASSWORD);
            Statement stmt = con.createStatement();
            
            // execute query
            rs = stmt.executeQuery(query);
            
            // store selected column names
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // get number of columns
            int numberOfColumns = rsmd.getColumnCount();
            
            // copies rows and columns to arraylist
            while (rs.next()) {
                HashMap<String, String> hm = new HashMap();
                for (int i = 0; i < numberOfColumns; i++) {
                    hm.put(rsmd.getColumnLabel(i+1), rs.getString(rsmd.getColumnLabel(i+1)));
                }
                res.add(hm);               
            }
            con.close();  
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }
    
    /**
     * Concatenates JDBC URL
     * @author Robert Östlin
     * @return String of concatenated JBDC URL
     */
    private static String connectionJDBC() {
        return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false";  
    }
    
    private static void printQuery(String query) {
        // print out query
        System.out.println("query: " + query);
    }
}