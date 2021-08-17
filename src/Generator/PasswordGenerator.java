package Generator;

public class PasswordGenerator {

    String newPassword;

    public PasswordGenerator(){
        newPassword = generatePassword();
    }

    public String getPassword(){return this.newPassword;}

    private String generatePassword(){
        String password = new String();
        password = "allo";
        return password;
    }
}
