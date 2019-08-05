package jp.co.systena.tigerscave.tigershopping.applications.model;

public class Order {
  private int itemId;
  private int num;

  public int getNum() {
    return this.num;
  }
  public void setNum(int num) {
    this.num += num;
  }
}
