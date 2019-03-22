package com.tiang.dao;

import com.tiang.model.Cart;
import com.tiang.model.Commodity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tiang
 * @date 20190202
 * 用户的购物车操作
 */
@Repository
public interface CartDao {

    /**
     * 查询用户的购物车列表，顺便查询每个商品的信息
     * @param userId 用户id
     * @return 列表
     */
    List<Cart> queryCartListWithCommodity(int userId);

    /**
     * 查询用户购物车中，是否有某件产品的记录
     * @param userId 用户id
     * @param commodityId 产品id
     * @return 是否有记录，有记录就返回记录
     */
    // 当有多个参数时，参数名会被去掉
    Cart queryCart(@Param("userId") int userId, @Param("commodityId") int commodityId);

    /**
     * 向购物车中插入一条记录
     * @param cart 购物车记录
     * @return 是否插入成功
     */
    int addCommodityToCart(Cart cart);

    /**
     * 更新购物车中商品的数量
     * @param cart 购物车记录
     * @return 是否更新成功
     */
    int updateCommodityCount(Cart cart);

    /**
     * 从购物车中删除记录
     * @param userId 用户id
     * @param commodityId 商品id
     * @return 是否删除成功
     */
    int deleteCart(@Param("uid") int userId, @Param("cid") int commodityId);
}
