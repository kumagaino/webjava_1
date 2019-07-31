package jp.co.systena.tigerscave.tigershopping.applications.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.tigershopping.applications.model.Item;
import jp.co.systena.tigerscave.tigershopping.applications.model.ListService;

@Controller
public class ListController {

   @RequestMapping(value="/ListView", method=RequestMethod.GET)
   public ModelAndView show(ModelAndView mav) {
     ListService listservice = new ListService();
     List<Item> itemlist = listservice.getItemList();
     mav.addObject("itemlist", itemlist);
     mav.setViewName("ListView");
     return mav;
   }

   @RequestMapping(value="/ResultView", method=RequestMethod.POST)
   public ModelAndView order(@RequestParam("inputvalue")String inputvalue,ModelAndView mav) {
     mav.setViewName("ResultView");
     mav.addObject("item",inputvalue);
     return mav;
   }
}
