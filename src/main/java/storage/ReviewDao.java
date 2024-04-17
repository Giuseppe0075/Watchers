package storage;

public interface ReviewDao {
    public void doSave(int userCode, int watchCode, String review);
    public void doDelete(int userCode, int watchCode);
    public String doRetrieveByKey(int userCode, int watchCode);
    public void doDeleteAll(int userCode);
    public void doDeleteAll();
}
