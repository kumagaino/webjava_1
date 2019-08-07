package jp.co.systena.tigerscave.tigershopping.applications.controller;

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

     // 理由はわからないが、セッションの見本にあったため記載。
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

     mav.addObject("itemId",listForm.itemId);
     mav.addObject("count",listForm.count);
     mav.setViewName("ResultView");
     return mav;
   }
}
