package storage.model;

import org.junit.jupiter.api.Test;
import storage.*;

import java.util.Collection;
import java.util.List;

public class ModelTest {

    //Modificare la classe Model e del Bean per cambiare argomento del test
    PurchaseModel model = new PurchaseModel();
    PurchaseBean bean;

    @Test
    public void testDoSaveOrUpdate(){
        try {
            //Creare il bean qui
            bean = model.doRetrieveByKey(List.of(1,1,1));
            bean.setPrice(105.0);
            model.doSaveOrUpdate(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoSave(){
        try {
            //Creare il bean qui
            model.doSave(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoDeleteByCond(){
        try {
            String condition = "";
            model.doDeleteByCond(condition);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoRetrieveByKey(){
        try {
            //Modificare le chiavi in base al Bean
            bean = model.doRetrieveByKey(List.of(1,1,1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoRetrieveByCond(){
        try {
            //Modificare la condizione in base al Bean
            String condition = "";
            model.doRetrieveByCond(condition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoRetrieveAll(){
        try {
            //Modificare il tipo di Collection in base al Bean
            //Collection<ReviewBean> beans = model.doRetrieveAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}