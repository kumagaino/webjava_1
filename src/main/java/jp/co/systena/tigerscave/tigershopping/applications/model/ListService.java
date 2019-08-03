package jp.co.systena.tigerscave.tigershopping.applications.model;

import java.util.ArrayList;
import java.util.List;

public class ListService {

  // ゲッター
  public List<Item> getItemList() {
    // 商品をインスタンスとして一つずつ生成

    // リンゴ
    Item apple = new Item();
    apple.itemId = 1;
    apple.name = "apple";
    apple.price = 100;

    // オレンジ
    Item orange = new Item();
    orange.itemId = 2;
    orange.name = "orange";
    orange.price = 200;

    // グレープ
    Item grape = new Item();
    grape.itemId = 3;
    grape.name = "grape";
    grape.price = 300;

    // 商品をすべて一つの配列に格納
    List<Item> itemList = new ArrayList<Item>();
    itemList.add(apple);
    itemList.add(orange);
    itemList.add(grape);
    return itemList;
  }

}
