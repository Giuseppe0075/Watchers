package user;

import jakarta.servlet.http.HttpServletRequest;
import org.tinylog.Logger;
import utils.FieldDescriptor;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class SignupDataForm {
    @FieldDescriptor(description = "Insert your name...")
    String name;
    @FieldDescriptor(description = "Insert your surname...")
    String surname;
    @FieldDescriptor(description = "Insert your date of birth...")
    LocalDate birthday;
    @FieldDescriptor(description = "Insert your city...")
    String city;
    @FieldDescriptor(description = "Insert your address...")
    String address;
    @FieldDescriptor(description = "Insert your civic number...")
    String civicNumber;
    @FieldDescriptor(description = "Insert your email...")
    String email;
    @FieldDescriptor(description = "Repeat your email...")
    String repeatedEmail;
    @FieldDescriptor(description = "Insert your password...")
    String password;
    @FieldDescriptor(description = "Repeat your password...")
    String repeatedPassword;

    public SignupDataForm(HttpServletRequest request) {
        for(Field field:this.getClass().getDeclaredFields()){
            try {
                field.setAccessible(true);
                field.set(this, request.getAttribute(field.getName()));
            }catch (Exception e){
                Logger.warn("Failed to set signupData, on field: {}", field.getName(), e);
            }
        }
    }

    private void sanitaize(){

    }
}
