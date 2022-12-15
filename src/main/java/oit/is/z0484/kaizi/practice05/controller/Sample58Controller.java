package oit.is.z0484.kaizi.practice05.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sample58")
public class Sample58Controller {

  private final Logger logger = LoggerFactory.getLogger(Sample58Controller.class);

  @GetMapping("step1")
  public SseEmitter pushCount58(@AuthenticationPrincipal UserDetails user) {

    logger.info("pushCount");
    logger.info(user.getUsername());
    logger.info(user.getAuthorities().toString() + ":toString()");

    for (GrantedAuthority g : user.getAuthorities()) {
      logger.info(g.getAuthority());
    }

    SseEmitter emitter = new SseEmitter();

    if (user.getAuthorities().toString().contains("CUSTOMER")) {
      logger.info("CUSTOMER!!");
    } else if (user.getAuthorities().toString().contains("SELLER")) {
      logger.info("SELLER!!");
    }

    return emitter;

  }

}
