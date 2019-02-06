package com.tiang.service;

import com.tiang.dao.CommodityDao;
import com.tiang.model.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询商品的服务
 */
@Service
public class CommodityService {

    @Autowired
    private CommodityDao dao;

    /**
     * 查询商品列表，但不包括图片
     * @return 商品列表
     */
    public List<Commodity> queryList(){
        return dao.queryCommodityList();
    }

    /**
     * 查询商品对应的图片，以二进制流的形式返回
     * @param id 商品id
     * @return 二进制流
     */
    public byte[] queryImage(int id){
        return dao.queryCommodityImage(id).getImage();
    }

    /**
     * 添加商品
     * @param commodity 商品信息
     * @return 添加之后的商品id
     */
    public int addCommodity(Commodity commodity){
        return dao.addCommodity(commodity);
    }

    /**
     * 查询商品的详细信息
     * @param id 商品id
     * @return 详细信息
     */
    public Commodity queryCommodity(int id){
        return dao.queryCommodity(id);
    }

    /**
     * 查询用户尚未购买的商品列表
     * @param userId 用户id
     * @return 商品列表
     */
    public List<Commodity> queryCommodityListNotBuy(int userId){
        return dao.queryCommodityListNotBuy(userId);
    }

    /**
     * 更新商品信息
     * @param commodity 商品信息
     * @return 是否更新成功
     */
    public int updateCommodity(Commodity commodity){
        return dao.updateCommodity(commodity);
    }
}
