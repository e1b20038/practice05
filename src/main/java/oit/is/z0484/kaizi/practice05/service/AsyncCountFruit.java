package oit.is.z0484.kaizi.practice05.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z0484.kaizi.practice05.model.Fruit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncCountFruit {
  int count = 1;
  private final Logger logger = LoggerFactory.getLogger(AsyncCountFruit.class);

  @Async
  public void count(SseEmitter emitter) throws IOException {
    logger.info("count start");
    try {
      while (true) {
        logger.info("send:" + count);
        emitter.send(count);
        count++;

        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());

    }
  }

  @Async
  public void sendpeach(SseEmitter emitter) {
    logger.info("push peach start");
    Fruit peach = new Fruit();
    peach.setName("桃");
    peach.setPrice(300);

    for (int i = 0; i < 10; i++) {
      try {
        logger.info("send(peach)");
        TimeUnit.SECONDS.sleep(1);

        emitter.send(peach);

      } catch (Exception e) {
        logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());

        break;
      }
    }
    emitter.complete();

  }
}
