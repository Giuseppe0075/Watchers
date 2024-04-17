package storage;

public interface ImageDao {
    public void doSave(int watchCode, String path);
    public void doDelete(int watchCode);
    public String doRetrieveByKey(int watchCode);
    public void doDeleteAll(int watchCode);
    public void doDeleteAll();
}
