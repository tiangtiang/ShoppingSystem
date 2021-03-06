package com.tiang.dao;

import com.tiang.model.Commodity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询商品的信息
 */
@Repository
public interface CommodityDao {

    /**
     * 查询商品列表，但是不查询内嵌图片
     * @return 商品简要信息
     */
    @Select("select id, title, price, imgUrl, sellCount from t_commodity")
    List<Commodity> queryCommodityList();

    /**
     * 查询商品的详细信息，包括摘要和详细描述
     * @param id 商品id
     * @return 商品信息
     */
    @Select("select id, title, summary, content, price, imgUrl, sellCount from t_commodity where id=#{id}")
    Commodity queryCommodity(int id);

    /**
     * 查询指定商品的内嵌图片
     * @param id 商品id
     * @return 图片的二进制流
     */
    @Select("select image from t_commodity where id=#{id}")
    Commodity queryCommodityImage(int id);

    /**
     * 查询用户尚未购买的商品
     * @param userId 用户id
     * @return 商品列表
     */
    @Select("SELECT id, title, price, imgUrl, sellCount FROM t_commodity t2 WHERE\n" +
            "\tt2.id NOT IN ( SELECT t.commodity_id FROM t_bought_list t WHERE t.user_id = #{userId} )")
    List<Commodity> queryCommodityListNotBuy(int userId);

    /**
     * 添加商品
     * @param commodity 商品信息
     * @return 是否添加成功
     */
    @Insert("insert into t_commodity (title, summary, content, price, image, owner_id, imgUrl) values(#{title}," +
            "#{summary}, #{content}, #{price}, #{image}, #{ownerId}, #{imgUrl})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addCommodity(Commodity commodity);

    /**
     * 更新商品信息
     * @param commodity 商品信息
     * @return 是否更新成功
     */
    @Update("update t_commodity set title=#{title}, summary=#{summary}, content=#{content}, price=#{price}," +
            "image=#{image}, imgUrl=#{imgUrl} where id=#{id}")
    int updateCommodity(Commodity commodity);

    /**
     * 更新商品信息， 除了图片
     * @param commodity 商品信息
     * @return 是否更新成功
     */
    @Update("update t_commodity set title=#{title}, summary=#{summary}, content=#{content}, price=#{price}" +
            " where id=#{id}")
    int updateCommodityWithoutImage(Commodity commodity);

    /**
     * 更新商品销售量
     * @param id 商品id
     * @param count 新增销售个数
     * @return 是否更新成功
     */
    @Update("update t_commodity set sellCount = sellCount+#{count} where id=#{id}")
    int updateSellCount(@Param("id") int id, @Param("count") int count);

    /**
     * 删除商品
     * @param id 商品id
     * @return 是否删除成功
     */
    @Delete("delete from t_commodity where id=#{id}")
    int deleteCommodity(int id);
}
