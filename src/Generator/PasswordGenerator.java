package Generator;
import java.util.Random;

public class PasswordGenerator {

    //Contains de newly generated password
    private String newPassword;


    Random rand = new Random();

    /***
     * ASCII table for the letters, numbers and symbols
     */
    private final int LOWERCASE_MIN = 97;
    private final int LOWERCASE_MAX = 122;

    private final int UPPERCASE_MIN = 65;
    private final int UPPERCASE_MAX = 90;

    private final int SYMBOL_MIN = 33;
    private final int SYMBOL_MAX = 47;

    private final int NUMBER_MIN = 48;
    private final int NUMBER_MAX = 57;

    private final int LOWERCASE = 1;
    private final int UPPERCASE = 2;
    private final int SYMBOL = 3;
    private final int NUMBER = 4;

    private final int MIN_PASSWORD_CHARACTERS = 10;


    private int lowercaseVerification;
    private int uppercaseVerification;
    private int symbolVerification;
    private int numberVerification;




    public String getPassword(){return this.newPassword;}

    public PasswordGenerator(){

        randomizeCharacterVerification();

        for(int i = 0; i < rand.nextInt(MIN_PASSWORD_CHARACTERS/2)+MIN_PASSWORD_CHARACTERS; i++){
            if(i == 0){
                generateFirstCharacter();
            }
            generateNewCharacter(verifyCharacters(i));
        }

    }

    private void generateFirstCharacter(){
        switch (rand.nextInt(4)+1){

            case LOWERCASE:
                newPassword = String.valueOf(newLowercaseLetter());
                break;
            case UPPERCASE:
                newPassword = String.valueOf(newUppercaseLetter());
                break;
            case SYMBOL:
                newPassword = String.valueOf(newSymbol());
                break;
            case NUMBER:
                newPassword = String.valueOf(newNumber());
                break;
        }
    }

    private void generateNewCharacter(int verification){

        if(verification == -1){
            verification = rand.nextInt(4)+1;
        }

        switch (verification){
            case LOWERCASE:
                newPassword += newLowercaseLetter();
                break;
            case UPPERCASE:
                newPassword += newUppercaseLetter();
                break;
            case 3:
                newPassword += newSymbol();
                break;
            case 4:
                newPassword += newNumber();
                break;
        }
    }

    private int verifyCharacters(int i){

        if(i == lowercaseVerification){
            if(verifyLowercaseLetter()){
                return -1;
            }
            return LOWERCASE;
        }
        if(i == uppercaseVerification){
            if(verifyUppercaseLetter()){
                return -1;
            }
            return UPPERCASE;
        }
        if(i == symbolVerification){
            if(verifySymbol()){
                return -1;
            }
            return SYMBOL;
        }
        if(i == numberVerification){
            if(verifyNumber()){
                return -1;
            }
            return NUMBER;
        }

        return -1;
    }


    private boolean verifyLowercaseLetter(){
        return verifyTheString(LOWERCASE_MIN, LOWERCASE_MAX);
    }

    private boolean verifyUppercaseLetter(){
        return verifyTheString(UPPERCASE_MIN,UPPERCASE_MAX);

    }
    private boolean verifySymbol(){
        return verifyTheString(SYMBOL_MIN,SYMBOL_MAX);
    }
    private boolean verifyNumber(){
        return verifyTheString(NUMBER_MIN,NUMBER_MAX);
    }

    private boolean verifyTheString(int MIN, int MAX){
        for(int i = 0; i < newPassword.length(); i++){
            char character = returnCharacteraAtIndex(i);
            if(MIN <= character && character <= MAX){
                return true;
            }
        }
        return false;
    }

    private char returnCharacteraAtIndex(int i){
        return newPassword.charAt(i);
    }


    private void randomizeCharacterVerification(){

        randomizeLowercaseCharacter();
        randomizeUppercaseCharacter();
        randomizeSymbolCharacter();
        randomizeNumberCharacter();
    }

    private void randomizeLowercaseCharacter(){
        lowercaseVerification = rand.nextInt(MIN_PASSWORD_CHARACTERS);
    }

    private void randomizeUppercaseCharacter(){
        while(lowercaseVerification != uppercaseVerification){

            uppercaseVerification = rand.nextInt(MIN_PASSWORD_CHARACTERS);
        }
    }
    private void randomizeSymbolCharacter(){
        while(symbolVerification != uppercaseVerification &&
                symbolVerification != lowercaseVerification){

            symbolVerification = rand.nextInt(MIN_PASSWORD_CHARACTERS);
        }
    }

    private void randomizeNumberCharacter(){
        while(numberVerification != uppercaseVerification &&
                numberVerification != lowercaseVerification &&
                numberVerification != symbolVerification){

            numberVerification = rand.nextInt(MIN_PASSWORD_CHARACTERS);
        }
    }

    private char newLowercaseLetter(){
        return (char) (rand.nextInt(LOWERCASE_MAX-LOWERCASE_MIN)+LOWERCASE_MIN);
    }

    private char newUppercaseLetter(){
        return (char) (rand.nextInt(UPPERCASE_MAX-UPPERCASE_MIN)+UPPERCASE_MIN);
    }

    private char newSymbol(){
        return (char) (rand.nextInt(SYMBOL_MAX-SYMBOL_MIN)+SYMBOL_MIN);
    }

    private char newNumber(){
        return (char) (rand.nextInt(NUMBER_MAX-NUMBER_MIN)+NUMBER_MIN);
    }


}
