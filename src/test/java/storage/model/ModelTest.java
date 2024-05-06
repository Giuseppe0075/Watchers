package storage.model;

import org.junit.jupiter.api.Test;
import storage.Beans.CartElementBean;
import storage.Beans.PurchaseBean;
import storage.Beans.ReviewBean;
import storage.Models.CartElementModel;
import storage.Models.PurchaseModel;

import java.util.Collection;
import java.util.List;

public class ModelTest {

    //Modificare la classe Model e del Bean per cambiare argomento del test
    CartElementModel model = new CartElementModel();
    CartElementBean bean;

    @Test
    public void testDoSaveOrUpdate(){
        try {
            //Creare il bean qui
            bean = model.doRetrieveByKey(List.of(1,1,1));
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
    public void testDoDelete(){
        try{
            bean = new CartElementBean(1L,10L,10);
            model.doDelete(bean);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoDeleteByCond(){
        try {
            String condition = "where user=? AND watch= ?";
            model.doDeleteByCond(condition, List.of(1,9));
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
            model.doRetrieveByCond(condition, List.of());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDoRetrieveAll(){
        try {
            //Modificare il tipo di Collection in base al Bean
            Collection<CartElementBean> beans = model.doRetrieveAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}