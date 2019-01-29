package com.tiang.dao;

import com.tiang.model.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询商品的信息
 */
@Repository
public interface CommodityDao {

    /**
     * 查询商品列表，但是不查询图片
     * @return
     */
    @Select("select id, title, summary, content, price from t_commodity")
    List<Commodity> queryCommodityList();

    /**
     * 查询指定商品的图片
     * @param id 商品id
     * @return 图片的二进制流
     */
    @Select("select image from t_commodity where id=#{id}")
    Commodity queryCommodityImage(int id);

    @Insert("insert into t_commodity (title, summary, content, price, image, owner_id) values(#{title}," +
            "#{summary}, #{content}, #{price}, #{image}, #{ownerId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addCommodity(Commodity commodity);
}
