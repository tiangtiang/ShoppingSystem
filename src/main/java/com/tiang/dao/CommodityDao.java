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
    List<Commodity> queryCommodityList();

    /**
     * 查询商品的详细信息，包括摘要和详细描述
     * @param id 商品id
     * @return 商品信息
     */
    Commodity queryCommodity(int id);

    /**
     * 查询指定商品的内嵌图片
     * @param id 商品id
     * @return 图片的二进制流
     */
    Commodity queryCommodityImage(int id);

    /**
     * 查询用户尚未购买的商品
     * @param userId 用户id
     * @return 商品列表
     */
    List<Commodity> queryCommodityListNotBuy(int userId);

    /**
     * 添加商品
     * @param commodity 商品信息
     * @return 是否添加成功
     */
    int addCommodity(Commodity commodity);

    /**
     * 更新商品信息
     * @param commodity 商品信息
     * @return 是否更新成功
     */
    int updateCommodity(Commodity commodity);

    /**
     * 更新商品信息， 除了图片
     * @param commodity 商品信息
     * @return 是否更新成功
     */
    int updateCommodityWithoutImage(Commodity commodity);

    /**
     * 更新商品销售量
     * @param id 商品id
     * @param count 新增销售个数
     * @return 是否更新成功
     */
    int updateSellCount(@Param("id") int id, @Param("count") int count);

    /**
     * 删除商品
     * @param id 商品id
     * @return 是否删除成功
     */
    int deleteCommodity(int id);
}
