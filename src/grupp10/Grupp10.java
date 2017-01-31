package grupp10;

import grupp10.utility.DatabaseHandler;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main class of project
 * @author Robert Östlin
 */
public class Grupp10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // example
        ArrayList<HashMap<String, String>> teachers = DatabaseHandler.fetchRows("SELECT Id, Fornamn, Efternamn, Email FROM Larare");
        
        for (HashMap<String, String> row : teachers) {
            System.out.println("Id: " + row.get("Id"));
            System.out.println("Förnamn: " + row.get("Fornamn"));
            System.out.println("Efternamn: " + row.get("Efternamn"));
            System.out.println("Email: " + row.get("Email"));
        }
        
        // returns "" if no value is found
        String fornamn = DatabaseHandler.fetchSingle("SELECT Fornamn FROM Larare WHERE Id = 1");
        
        // String fornamn = DatabaseHandler.fetchSingle("SELECT Fornamn FROM Larare WHERE Fornamn LIKE '%A%'");
        System.out.println("Förnamn: " + fornamn);
        
        new Login().setVisible(true);
    }
}