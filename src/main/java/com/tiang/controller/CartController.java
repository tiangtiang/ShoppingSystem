package com.tiang.controller;

import com.tiang.interceptor.RequiredLogin;
import com.tiang.model.Cart;
import com.tiang.model.User;
import com.tiang.service.UserService;
import com.tiang.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tiang
 * @date 20190203
 * 购物车相关
 */
@Controller
public class CartController {

    @Autowired
    private UserService userService;

    /**
     * 展示购物车列表
     * @param map 数据
     * @param session 用户信息
     * @return 跳转到购物车页面
     */
    @RequestMapping("/cart")
    @RequiredLogin
    public String showCart(ModelMap map, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Cart> list = userService.queryUserCart(user.getUserId());
        map.put("cart", list);
        return "show-cart";
    }

    @RequestMapping("/cart/buy")
    @RequiredLogin
    @ResponseBody
    public String clearCart(@RequestParam("cids[]") List<Integer> cids,
                            @RequestParam("counts[]") List<Integer> counts,
                            @RequestParam("prices[]") List<Double> prices, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(userService.buyCommodity(user.getUserId(), cids, counts, prices))
            return Constant.SUCCESS;
        else
            return Constant.FAIL;
    }

    /**
     * 删除购物车中的某一条记录
     * @param cid 商品id
     * @param session 会话
     * @return 修改结果
     */
    @ResponseBody
    @RequiredLogin
    @RequestMapping("/cart/del")
    public String deleteCartItem(int cid, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(userService.deleteCartItem(user.getUserId(), cid)>-1){
            return Constant.SUCCESS;
        }else
            return Constant.FAIL;
    }

    /**
     * 批量更新购物车中商品的购买数量
     * @param cids 商品编号列表
     * @param counts 购买数量列表
     * @param session 会话
     * @return 是否更新成功
     */
    @RequiredLogin
    @ResponseBody
    @RequestMapping("/cart/update")
    public String updateCartItemsCount(@RequestParam("cids[]") List<Integer> cids,
                                  @RequestParam("counts[]") List<Integer> counts, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(userService.updateCartItems(user.getUserId(), cids, counts)){
            return Constant.SUCCESS;
        }else
            return Constant.FAIL;
    }
}
