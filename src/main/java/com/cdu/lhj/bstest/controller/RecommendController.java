package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Bo.ShopsSearchBo;
import com.cdu.lhj.bstest.pojo.Shops;
import com.cdu.lhj.bstest.service.RecommendService;
import com.cdu.lhj.bstest.service.ShopsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {
    @Resource
    private RecommendService recommendService;

    @Resource
    private ShopsService shopsService;

    @GetMapping("/getShopsRecommend")
    @SaIgnore
    public SaResult recommend(){
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
        }catch (Exception e){
            List<Shops> records = shopsService.getShopsBySearch(new ShopsSearchBo(null, 1, 6)).getRecords();
            return SaResult.data(records);
        }

        try {
            // 拿到id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            List<Long> recommend = recommendService.recommend(loginIdAsLong);
            List<Shops> shops = new ArrayList<>();
            for (Long l : recommend) {
                Shops shopsById = shopsService.getShopsById(l);
                shops.add(shopsById);
            }
            if (shops.size() < 6) {
                List<Shops> records = shopsService.getShopsBySearch(new ShopsSearchBo(null, 1, 6 - shops.size())).getRecords();
                shops.addAll(records);
            }
            return SaResult.data(shops);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

}
