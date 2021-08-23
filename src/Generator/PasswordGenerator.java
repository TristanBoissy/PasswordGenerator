package Generator;
import java.util.Random;

public class PasswordGenerator {

    //Contains de newly generated password
    private String newPassword;
    Random rand = new Random();

    /***
     * ASCII table for the letters, numbers and symbols
     */
    private int LOWERCASE_MIN = 97;
    private int LOWERCASE_MAX = 122;

    private int UPPERCASE_MIN = 65;
    private int UPPERCASE_MAX = 90;

    private int SYMBOL_MIN = 33;
    private int SYMBOL_MAX = 47;

    private int NUMBER_MIN = 48;
    private int NUMBER_MAX = 57;




    public PasswordGenerator(){
        newPassword = generatePassword();
    }

    public String getPassword(){return this.newPassword;}

    private String generatePassword(){
        String password = new String();
        return password;
    }

    private char newLowercaseLetter(){




    }
}
