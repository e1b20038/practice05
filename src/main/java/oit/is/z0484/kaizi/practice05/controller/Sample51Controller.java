package oit.is.z0484.kaizi.practice05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample5")
public class Sample51Controller {

  @GetMapping("step1")
  public String sample51() {
    return "sample51.html";
  }

}
