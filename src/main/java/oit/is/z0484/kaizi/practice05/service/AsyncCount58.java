package oit.is.z0484.kaizi.practice05.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncCount58 {
  private int customerCount = 1;
  private int sellerCount = 1;

  private final Logger logger = LoggerFactory.getLogger(AsyncCount58.class);

  @Async
  public void count(SseEmitter emitter, String role) throws IOException {
    logger.info("AsyncCount58 start");

    try {
      while (true) {
        int count = 0;
        if (role.equals("CUSTOMER")) {
          count = customerCount;
          customerCount++;
        } else if (role.equals("SELLER")) {
          count = sellerCount;
          sellerCount++;
        }

        emitter.send(SseEmitter.event().data(count).id(role));
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
