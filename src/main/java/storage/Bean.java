package storage;

import org.tinylog.Logger;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;

public abstract class Bean {
    /**
     * Preconditon:
     * - The attribute name must be the same name of the database column
     * - The attribute type must be the same name of the database column
     * @param rs Result of the database
     */
    public Bean(ResultSet rs) {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                if (fieldType == Date.class) {
                    // Se il tipo di campo è Date, utilizza getDate() o getTimestamp() in base alla tua esigenza
                    if (rs.getObject(fieldName) instanceof java.sql.Date) {
                        field.set(this, rs.getDate(fieldName));
                    } else {
                        field.set(this, rs.getTimestamp(fieldName));
                    }
                } else {
                    // Altrimenti, utilizza getObject() come prima
                    field.set(this, rs.getObject(fieldName));
                }
            } catch (Exception e) {
                Logger.error(e, "Failed to create Bean from ResultSet | class: " + this.getClass().getSimpleName() + " field name: " + field.getName());
            }
        }
    }


    public Bean(){}

}
