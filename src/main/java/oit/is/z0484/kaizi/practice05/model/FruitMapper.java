package oit.is.z0484.kaizi.practice05.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FruitMapper {

  @Select("SELECT * FROM FRUIT")
  ArrayList<Fruit> selectAllFruit();

  @Select("SELECT * FROM FRUIT WHERE ID = #{id}")
  Fruit selectById(int id);

  @Delete("DELETE FROM FRUIT WHERE ID = #{id}")
  boolean deleteById(int id);

  @Update ("UPDATE FRUIT SET NAME=#{name}, PRICE=#{price} WHERE ID=#{id}")
  void updateById(Fruit fruit);

}
