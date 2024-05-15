package user;

import jakarta.servlet.http.HttpServletRequest;
import org.tinylog.Logger;
import storage.Beans.UserBean;
import utils.FieldDescriptor;
import utils.Security;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;

public class SignupDataForm {
    @FieldDescriptor(description = "Insert your name...")
    public String name;
    @FieldDescriptor(description = "Insert your surname...")
    public String surname;
    @FieldDescriptor(description = "Insert your date of birth...", type = "date")
    public Date birthday;
    @FieldDescriptor(description = "Insert your city...")
    public String city;
    @FieldDescriptor(description = "Insert your address...")
    public String address;
    @FieldDescriptor(description = "Insert your CAP...")
    public String CAP;
    @FieldDescriptor(description = "Insert your civic number...")
    public String civicNumber;
    @FieldDescriptor(description = "Insert your email...")
    public String email;
    @FieldDescriptor(description = "Repeat your email...")
    public String repeatedEmail;
    @FieldDescriptor(description = "Insert your password...")
    public String password;
    @FieldDescriptor(description = "Repeat your password...")
    public String repeatedPassword;

    private byte[] salt;

    public SignupDataForm(HttpServletRequest request) throws Exception {
        for(Field field:this.getClass().getFields()){
            try {
                field.setAccessible(true);
                Object sanitizeData;
                if(field.getName().equals("birthday")){
                     sanitizeData = Date.valueOf(request.getParameter(field.getName()));
                }else {
                    sanitizeData = Security.sanitizeInput(request.getParameter(field.getName()));
                }
                field.set(this, sanitizeData);
            }catch (Exception e){
                Logger.warn("Failed to set signupData, on field: {}, exception: {}", field.getName(), e);
            }
        }
        hashPassword();
        if(!Security.checkPasswordForm(password)){
            throw new Exception("Invalid password");
        }
    }

    private void hashPassword() throws NoSuchAlgorithmException {
        this.salt = Security.generateSalt();
        this.password = Security.hash(password, salt);
    }



    private void checkForDoubles() throws Exception{
        if(!email.equals(repeatedEmail)){
            throw  new Exception("Email doesn't match");
        }
        if(!password.equals(repeatedPassword)){
            throw  new Exception("Passwords doesn't match");
        }
    }

}
