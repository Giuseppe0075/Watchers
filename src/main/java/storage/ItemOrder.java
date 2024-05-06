package storage;

import storage.Beans.WatchBean;

public class ItemOrder {


    private WatchBean item;
    private int numItems;

    public ItemOrder(WatchBean item) {
        setItem(item);
        setNumItems(1);
    }

    public WatchBean getItem() {
        return(item);
    }

    protected void setItem(WatchBean item) {
        this.item = item;
    }

    public Long getItemID() {
        return(getItem().getId());
    }

    public String getDescriptionItem() {
        return(getItem().getDescription());
    }

    public double getUnitCost() {
        return(getItem().getPrice());
    }

    public int getNumItems() {
        return(numItems);
    }

    public void setNumItems(int n) {
        this.numItems = n;
    }

    public void incrementNumItems() {
        setNumItems(getNumItems() + 1);
    }

    public void cancelOrder() {
        setNumItems(0);
    }

    public double getTotalCost() {
        return(getNumItems() * getUnitCost());
    }
}
