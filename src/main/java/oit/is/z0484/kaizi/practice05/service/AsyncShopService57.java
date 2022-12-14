package oit.is.z0484.kaizi.practice05.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z0484.kaizi.practice05.model.Fruit;
import oit.is.z0484.kaizi.practice05.model.FruitMapper;

@Service
public class AsyncShopService57 {
  boolean updateDB = false;
  private final Logger logger = LoggerFactory.getLogger(AsyncShopService57.class);

  @Autowired
  FruitMapper fMapper;

  public ArrayList<Fruit> syncShowFruitsList() {
    return fMapper.selectAllFruit();
  }

}