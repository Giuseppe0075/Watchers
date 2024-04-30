package storage.model;

import org.junit.jupiter.api.Test;
import storage.UserBean;
import storage.UserModel;

public class UserModelTest {
    private UserModel model;
    private UserBean userBean = null;

    public UserModelTest(){
        model = new UserModel();
    }

    @Test
    public void testDoRetrieveByCond(){
        try {
            model.doRetrieveByCond("road = 'Street 1'");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoSaveOrUpdate(){
        try {
            userBean = model.doRetrieveByKey("1");
            userBean.setCAP("00000");
            userBean.setId(0);
            userBean.setEmail("Mannagg1@laputtana.it");
            model.doSaveOrUpdate(userBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDelete(){
        try {
            model.doDeleteByCond("WHERE CAP = 00000");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoRetrieveAll(){
        try {
            model.doRetrieveAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
