package com.tiang.dao;

import com.tiang.model.BoughtList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tiang
 * @date 20190202
 * 查询已购列表
 */
@Repository
public interface BoughtDao {

    /**
     * 根据用户id查询该用户购买过的所有商品信息
     * @param userId 用户id
     * @return 已购列表
     */
    @Select("select * from t_bought_list t where t.user_id = #{userId}")
    @Results(
            {
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "commodityId", column = "commodity_id"),
                    @Result(property = "buyTime", column = "buy_time"),
                    @Result(property = "buyPrice", column = "buy_price")
            }
    )
    List<BoughtList> queryBoughtList(int userId);

    /**
     * 向已购列表中添加记录
     * @param bought 记录
     * @return 是否添加成功
     */
    @Insert("insert into t_bought_list (user_id, commodity_id, buy_price, count, buy_time) " +
            "values(#{userId}, #{commodityId}, #{buyPrice}, #{count}, #{buyTime})")
    int addBought(BoughtList bought);

    /**
     * 查询用户的购买列表，包括商品信息
     * @param userId 用户id
     * @return 购买列表
     */
    @Select("select * from t_bought_list t where t.user_id = #{userId}")
    @Results(
            {
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "commodityId", column = "commodity_id"),
                    @Result(property = "buyTime", column = "buy_time"),
                    @Result(property = "buyPrice", column = "buy_price"),
                    @Result(property = "commodity", column = "commodity_id",
                            one = @One(select = "com.tiang.dao.CommodityDao.queryCommodity"))
            }
    )
    List<BoughtList> queryBoughtListWithCommodity(int userId);
}
