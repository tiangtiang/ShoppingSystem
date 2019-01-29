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
}
