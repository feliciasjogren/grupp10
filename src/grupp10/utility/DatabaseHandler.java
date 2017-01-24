package grupp10.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Robert Östlin (scrum master)
 */
public class DatabaseHandler {
    private DatabaseHandler() {
        // private, no objects allowed
    }
    
    // TODO:
    public static String fetchSingle(String query) {
        // code
        return "";
    }
    
    public static boolean insert(String query) {
        // code
        return true;
    }
    
    public static boolean delete(String query) {
        // code
        return true;
    }
    
    public static boolean update(String query) {
        // code
        return true;
    }
    
    public static ArrayList<HashMap<String, String>> fetchRows(String query) {
        ResultSet rs;
        ArrayList<HashMap<String, String>> res = new ArrayList();
        try {
            // connect to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupp10?useSSL=false","root","aaaa");
            Statement stmt = con.createStatement(); 
            
            // execute query
            rs = stmt.executeQuery(query); 
            
            // store selected column names
            ResultSetMetaData md = rs.getMetaData();
            
            // get number of columns
            int numberOfColumns = md.getColumnCount();
            
//String columnName[] = new String[numberOfColumns];

//            for (int i = 1; i <= numberOfColumns; i++)
//            {
//               columnName[i-1] = md.getColumnLabel(i);
//               System.out.println(columnName[i-1]);
//            }
            while (rs.next()) {
                HashMap<String, String> hm = new HashMap();
                for (int i = 0; i < numberOfColumns; i++)
                {
    //              columnName[i] = md.getColumnLabel(i+1);
    //              System.out.println(columnName[i]);
                    //res.add(new HashMap<String, String>(md.getColumnLabel(i+1), "value"));
                    //HashMap<String, String> hm = new HashMap();
                    // TODO:
                    // add null check
                    hm.put(md.getColumnLabel(i+1), rs.getString(md.getColumnLabel(i+1)));
                    //res.add(hm);
                }
                res.add(hm);
                
                
                System.out.println();
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                //System.out.println("id: " + rs.getInt("id"));
                //System.out.println("namn: " + rs.getString("namn"));
                //System.out.println("mailadress: " + rs.getString("mailadress"));
                
            }
            con.close();  
        } catch (Exception e) { // SQLExcpeption
            System.out.println(e);
        }
        return res;
    }
}
