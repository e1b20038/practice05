package oit.is.z0484.kaizi.practice05.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
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
  public void count(SseEmitter emitter) throws IOException{
    logger.info("count start");
    try{
      while(true){
        logger.info("send:" + count);
        emitter.send(count);
        count++;

        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());

    }
  }

}
