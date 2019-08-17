package jp.co.systena.tigerscave.tigershopping.applications.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.tigershopping.applications.model.Cart;
import jp.co.systena.tigerscave.tigershopping.applications.model.Item;
import jp.co.systena.tigerscave.tigershopping.applications.model.ListForm;
import jp.co.systena.tigerscave.tigershopping.applications.model.ListService;
import jp.co.systena.tigerscave.tigershopping.applications.model.Order;

@Controller
public class ListController {

  @Autowired
  HttpSession session;                  // セッション管理

   @RequestMapping(value="/ListView", method=RequestMethod.GET)
   public ModelAndView show(ModelAndView mav) {

     // 注文情報を格納するインスタンス生成
     // 毎回初期化する
     session.removeAttribute("listForm");

     // 注文用の配列
     mav.addObject("listForm", new ListForm());

     // 画面表示用にリストを取得
     // リスト用
     ListService listservice = new ListService();
     List<Item> itemlist = listservice.getItemList();
     mav.addObject("itemlist", itemlist);


     // Viewのテンプレート名を設定
     mav.setViewName("ListView");
     return mav;
   }

   @RequestMapping(value="/ResultView", method=RequestMethod.POST)
   public ModelAndView order(ModelAndView mav, @Valid ListForm listForm, BindingResult bindingResult, HttpServletRequest request) {

     // セッション情報から保存したデータを取得する。
     Cart cart = (Cart) session.getAttribute("cart");
     if (cart == null) {
       // 初期だった場合作成
       cart = new Cart();
       session.setAttribute("cart", cart);
     }

     if (bindingResult.getAllErrors().size() > 0) {
       // エラーがある場合はそのまま戻す
       mav.addObject("listForm",listForm);  // 新規クラスを設定
       mav.addObject("cart", cart);

       // Viewのテンプレート名を設定
       mav.setViewName("ListView");
       return mav;
     }

     // itemIdから商品名と価格を取得(Mapを使用)
     HashMap<Integer, Item> ItemList = new HashMap<Integer, Item>();
     ListService service = new ListService();
     List<Item> itemList = service.getItemList();
     for (Item item : itemList) {
       ItemList.put(item.getItemid(), item);
     }
     mav.addObject("itemList", ItemList);

     // セッションに保存する
     Order order = new Order();
     order.setItemid(listForm.itemId);
     order.setNum(listForm.count);
     cart.add(order);
     session.setAttribute("cart", cart);

     mav.addObject("orderList", cart.getOrderList());

     mav.setViewName("ResultView");
     return mav;
   }
}
