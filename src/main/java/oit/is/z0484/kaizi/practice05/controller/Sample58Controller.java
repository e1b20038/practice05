package oit.is.z0484.kaizi.practice05.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z0484.kaizi.practice05.service.AsyncCount58;

@RestController
@RequestMapping("/sample58")
public class Sample58Controller {

  private final Logger logger = LoggerFactory.getLogger(Sample58Controller.class);

  int emitterCouter = 0;

  ConcurrentHashMap<String, SseEmitter> semap = new ConcurrentHashMap<String, SseEmitter>();

  @Autowired
  private AsyncCount58 counter58;

  @GetMapping("step1")
  public SseEmitter pushCount58(@AuthenticationPrincipal UserDetails user) {

    logger.info("pushCount");
    logger.info(user.getUsername());
    logger.info(user.getAuthorities().toString() + ":toString()");

    for (GrantedAuthority g : user.getAuthorities()) {
      logger.info(g.getAuthority());
    }

    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    this.emitterCouter++;
    String role = new String();

    if (user.getAuthorities().toString().contains("CUSTOMER")) {
      logger.info("CUSTOMER!!");
      role = "CUSTOMER";
    } else if (user.getAuthorities().toString().contains("SELLER")) {
      logger.info("SELLER!!");
      role = "SELLER";
    }

    String semapId = "" + this.emitterCouter;
    this.semap.put(semapId, emitter);

    try {
      counter58.count(emitter, role);
    } catch (IOException e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    }

    return emitter;

  }

  @Scheduled(fixedRate = 3000)
  public void heartbeat() {

    for (Map.Entry<String, SseEmitter> entry : this.semap.entrySet()) {
      logger.info("heartbeat");
      try {
        entry.getValue().send(SseEmitter.event()
            .data("heartbeat")
            .id("" + entry.getKey()));
      } catch (IOException e) {
        logger.warn("Exception" + e.getClass().getName() + ":" + e.getMessage());
        logger.info("emitter" + entry.getKey() + "is Removed");
        this.semap.remove(entry.getKey());
      }

    }

  }

}
