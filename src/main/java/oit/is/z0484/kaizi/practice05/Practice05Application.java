package oit.is.z0484.kaizi.practice05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Practice05Application {

  public static void main(String[] args) {
    SpringApplication.run(Practice05Application.class, args);
  }

}
