package storage;

public interface OperationDAO {
    public void doSave(int userCode, int watchCode);
    public void doDelete(int userCode, int watchCode);
    public boolean doRetrieveByKey(int userCode, int watchCode);
    public void doDeleteAll(int userCode);
    public void doDeleteAll();
}