package grupp10.utility;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Robert Östlin (scrum master)
 */
public class Validation {
    
    private Validation() {
        // private, no objects allowed
    }
    
    static public boolean textfieldNotEmpty(JTextField tf, String message) {
        if (tf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            tf.requestFocus();
            return false;
        }
        return true;
    }
    
    static public boolean passwordfieldNotEmpty(JPasswordField pf, String message) {
        
        if (pf.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, message);
            pf.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param str
     * @return 
     */
    static public boolean isNumerical(String str) {
        try {
            int n = Integer.parseInt(str);
            return true;
        } catch(NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }
    }
    
    static public boolean hasOnlyLetters(String exp) {
        // check for letters a-z, A-Z and åäö/ÅÄÖ, no other characters are allowed
        return exp.matches("[a-zA-ZåäöÅÄÖ]+");
    } 
}
