package admin;

import jakarta.servlet.http.HttpServletRequest;
import org.tinylog.Logger;
import utils.FieldDescriptor;

import java.lang.reflect.Field;

public class WatchDataForm {
    @FieldDescriptor(description = "Insert the name of the watch...")
    String name;
    @FieldDescriptor(description = "Insert the brand of the watch...")
    String brand;
    @FieldDescriptor(description = "Insert the description of the watch...")
    String description;
    @FieldDescriptor(description = "Insert the price of the watch...")
    double price;
    @FieldDescriptor(description = "Insert the material of the watch")
    String material;
    @FieldDescriptor(description = "Insert the stock quantity of the watch...")
    int stock;
    @FieldDescriptor(description = "Insert the dimension of the watch...")
    int dimension;
    @FieldDescriptor(description = "Insert the IVA of the watch...")
    int iva;
    @FieldDescriptor(description = "Insert the sex for the watch...")
    String sex;

    public WatchDataForm(HttpServletRequest request) {
        for(Field field:this.getClass().getDeclaredFields()){
            try {
                field.setAccessible(true);
                field.set(this, request.getAttribute(field.getName()));
            }catch (Exception e){
                Logger.warn("Failed to set signupData, on field: {}", field.getName(), e);
            }
        }
    }
}
