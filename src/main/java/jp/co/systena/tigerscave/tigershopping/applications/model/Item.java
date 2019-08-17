package jp.co.systena.tigerscave.tigershopping.applications.model;

public class Item {

    public int itemId;

    public String name;

    public int price;

    public int getItemid() {
      return itemId;
    }

    public void setItemid(int itemid) {
      this.itemId = itemid;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

}
