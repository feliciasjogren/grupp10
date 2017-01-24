/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp10;

import grupp10.utility.DatabaseHandler;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rober
 */
public class Grupp10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // example
        ArrayList<HashMap<String, String>> teachers = DatabaseHandler.fetchRows("select Id, Forename, Surname, Email from teacher");
        
        for (HashMap<String, String> row : teachers) {
            System.out.println("Id: " + row.get("Id"));
            System.out.println("Forename: " + row.get("Forename"));
            System.out.println("Surname: " + row.get("Surname"));
            System.out.println("Email: " + row.get("Email"));
            System.out.println();
        }       
    }
}
