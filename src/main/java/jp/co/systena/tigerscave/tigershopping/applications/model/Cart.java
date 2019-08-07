package jp.co.systena.tigerscave.tigershopping.applications.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  public List<Order> orderList = new ArrayList<Order>();

  public Cart() {

  }

  public void add(Order order) {
    orderList.add(order);
  }

  public List<Order> getOrderList() {
    return this.orderList;
  }

}
