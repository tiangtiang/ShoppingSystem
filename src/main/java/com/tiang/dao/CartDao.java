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

    List<Cart> queryCartList(int userId);

    /**
     * 查询用户购物车中，是否有某件产品的记录
     * @param userId 用户id
     * @param commodityId 产品id
     * @return 是否有记录，有记录就返回记录
     */
    @Select("select id, user_id, commodity_id, count, add_time from t_cart" +
            " t where t.user_id = #{userId} and t.commodity_id = #{commodityId}")
    @Results(
            {
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "commodityId", column = "commodity_id"),
                    @Result(property = "addTime", column = "add_time")
            }
    )
    // 当有多个参数时，参数名会被去掉
    Cart queryCart(@Param("userId") int userId, @Param("commodityId") int commodityId);

    /**
     * 向购物车中插入一条记录
     * @param cart 购物车记录
     * @return 是否插入成功
     */
    @Insert("insert into t_cart (user_id, commodity_id, count) values(#{userId}, #{commodityId}, #{count})")
    int addCommodityToCart(Cart cart);

    /**
     * 更新购物车中商品的数量
     * @param cart 购物车记录
     * @return 是否更新成功
     */
    @Update("update t_cart set count = #{count} where user_id = #{userId} and commodity_id = #{commodityId}")
    int updateCommodityCount(Cart cart);
}
