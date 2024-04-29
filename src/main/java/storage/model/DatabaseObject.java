package storage.model;

import database.Connection;
import database.DatabaseConnectionPool;
import org.tinylog.Logger;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.*;

public abstract class DatabaseObject {
    /**
     * Retrives all the object from the database for the specified table
     * The class needs an empty Constructor, and all the field have to represent the actual db type
     * @return a list of object
     */
    public static <T> List<T> retriveAll(Class<T> targetClass) {
        DatabaseTable table = targetClass.getAnnotation(DatabaseTable.class);
        //take the annotation if present or the class Name
        String tableName = table != null ? table.tableName() : targetClass.getSimpleName();
        String query = "SELECT * FROM " + tableName;
        List<T> objects = new ArrayList<>();

        try(Connection connection = DatabaseConnectionPool.getInstance().getConnection2()){
            try(ResultSet resultSet = connection.executeQuery(query)){
                while (resultSet.next()){
                    // creates an empty object

                    objects.add(buildObject(resultSet, targetClass));
                }
            }catch (Exception exception){
                Logger.error("failed to retrive the object: " + tableName, exception);
            }
        }catch (Exception exception){
            Logger.error("failed to retrive all Object", exception);
        }
        return objects;
    }

    private static  <T> T buildObject(ResultSet resultSet, Class<T> targetClass) throws Exception {
        T newObject = targetClass.getDeclaredConstructor().newInstance();
        for(Field field : targetClass.getDeclaredFields()){
            try {
                field.setAccessible(true);
                // takes the object with the field Name and set into the new object
                Object dbObject = resultSet.getObject(field.getName());
                Logger.debug("Result From db of " + field.getName() + " : " + dbObject + " type: " + dbObject.getClass().getSimpleName());
                field.set(newObject, dbObject);
            }catch (Exception e){
                Logger.error("failed to set Attribute: " + field.getName(), e);
                e.printStackTrace();
            }
        }
        return newObject;
    }

    public static <T> T operateOnDb(Class<T> targetClass, Object id, String startQuery) throws Exception {
        DatabaseTable table = targetClass.getAnnotation(DatabaseTable.class);
        //take the annotation if present or the class Name
        String tableName = table != null ? table.tableName() : targetClass.getSimpleName();
        Optional<Field> optIdName = Arrays.stream(targetClass.getDeclaredFields()).filter(f -> f.getAnnotation(DatabaseKey.class) != null).findFirst();
        if(optIdName.isEmpty()){
            throw new Exception("No database key found");
        }
        String idName = optIdName.get().getName();
        String query = startQuery + " FROM " + tableName + " WHERE " + idName + "= ?";
        try(Connection connection = DatabaseConnectionPool.getInstance().getConnection2()){
            try(ResultSet resultSet = connection.executeQuery(query, Collections.singletonList(id))){
                resultSet.next();
                return buildObject(resultSet, targetClass);
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.error("Failed to get Element", e);
        }
        return null;
    }


    public static <T> T retriveById(Class<T> targetClass, Object id) throws Exception {
        return operateOnDb(targetClass, id, "SELECT *");

    }

    public static <T> T deleteById(Class<T> targetClass,Object id) throws Exception{
        return operateOnDb(targetClass, id, "DELETE");
    }

}
