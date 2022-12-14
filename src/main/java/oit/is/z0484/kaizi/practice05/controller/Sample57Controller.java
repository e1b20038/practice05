package oit.is.z0484.kaizi.practice05.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.practice05.model.Fruit;
import oit.is.z0484.kaizi.practice05.service.AsyncShopService57;

@Controller
@RequestMapping("/sample5")
public class Sample57Controller {

  @Autowired
  private AsyncShopService57 shop57;

  @GetMapping("step7")
  public String sample57(ModelMap model) {
    final ArrayList<Fruit> fruits7 = shop57.syncShowFruitsList();
    model.addAttribute("fruits7", fruits7);
    return "sample57.html";
  }

  @GetMapping("step8")
  @Transactional
  public String sample58(@RequestParam Integer id, ModelMap model) {
    final Fruit fruit8 = this.shop57.syncBuyFruits(id);
    model.addAttribute("fruit8", fruit8);

    final ArrayList<Fruit> fruits7 = shop57.syncShowFruitsList();
    model.addAttribute("fruits7", fruits7);

    return "sample57.html";
}
}
