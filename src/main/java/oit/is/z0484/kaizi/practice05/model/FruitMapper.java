package oit.is.z0484.kaizi.practice05.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FruitMapper {

  @Select ("SELECT * FROM FRUIT")
  ArrayList<Fruit> selectAllFruit();


}
