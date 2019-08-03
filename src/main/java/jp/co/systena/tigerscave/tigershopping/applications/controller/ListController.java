package jp.co.systena.tigerscave.tigershopping.applications.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.tigershopping.applications.model.Cart;
import jp.co.systena.tigerscave.tigershopping.applications.model.Item;
import jp.co.systena.tigerscave.tigershopping.applications.model.ListForm;
import jp.co.systena.tigerscave.tigershopping.applications.model.ListService;

@Controller
public class ListController {

  @Autowired
  HttpSession session;                  // セッション管理

   @RequestMapping(value="/ListView", method=RequestMethod.GET)
   public ModelAndView show(ModelAndView mav) {

     // セッション情報から保存したデータを取得する。
     List<Cart> cart = (List<Cart>) session.getAttribute("cart");
     if (cart == null) {
       // 初期だった場合作成
       cart = new ArrayList<Cart>();
       session.setAttribute("cart", cart);
     }
     mav.addObject("cart",cart);

     // 注文情報を格納するインスタンス生成
     // 毎回初期化する
     session.removeAttribute("listForm");
     List<ListForm> listForm = (List<ListForm>) session.getAttribute("listForm");
     listForm = new ArrayList<ListForm>();
     mav.addObject("listForm",listForm);

     // 画面表示用にリストを取得
     ListService listservice = new ListService();
     List<Item> itemlist = listservice.getItemList();
     mav.addObject("itemlist", itemlist);

     // 理由はわからないが、セッションの見本にあったため配置。
     // メソッドの引数に設定がないため不必要では？
     BindingResult bindingResult = (BindingResult) session.getAttribute("result");
     if (bindingResult != null) {
       mav.addObject("bindingResult", bindingResult);
     }
     // Viewのテンプレート名を設定
     mav.setViewName("ListView");
     return mav;
   }

   @RequestMapping(value="/ResultView", method=RequestMethod.POST)
   public ModelAndView order(@RequestParam("listForm")String listForm,ModelAndView mav) {
     mav.setViewName("ResultView");
     mav.addObject("listForm",listForm);
     return mav;
   }
}
