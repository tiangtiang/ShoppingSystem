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
    List<BoughtList> queryBoughtList(int userId);

    /**
     * 向已购列表中添加记录
     * @param bought 记录
     * @return 是否添加成功
     */
    int addBought(BoughtList bought);

    /**
     * 查询用户的购买列表，包括商品信息
     * @param userId 用户id
     * @return 购买列表
     */
    List<BoughtList> queryBoughtListWithCommodity(int userId);
}
