package com.tiang.dao;

import com.tiang.model.BoughtList;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
