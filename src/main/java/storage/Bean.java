package storage;

import org.tinylog.Logger;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public abstract class Bean {

    public Bean(ResultSet rs){
        for(Field field: this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                field.set(this, rs.getObject(field.getName()));
            }catch (Exception e ){
                Logger.error(e, "Failed to create Bean from ResultSet | class: " + this.getClass().getSimpleName() + " field name: " + field.getName());
            }
        }
    }

    public Bean(){}

}
