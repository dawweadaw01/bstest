package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Bo.ShopsSearchBo;
import com.cdu.lhj.bstest.pojo.Shops;
import com.cdu.lhj.bstest.service.ShopsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
public class ShopsController {

    @Resource
    private ShopsService shopsService;

    @PostMapping("/insertShops")
    public SaResult insertShops(@RequestBody Shops shops) {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            shops.setOwnerId(loginIdAsLong);
            boolean inserted = shopsService.insertShops(shops);
            if(inserted){
                return SaResult.ok();
            }else {
                return SaResult.error("插入失败,检查参数");
            }
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/updateShops")
    public SaResult updateShops(@RequestBody Shops shops) {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            shops.setOwnerId(loginIdAsLong);
            boolean updated = shopsService.updateShops(shops);
            if(updated){
                return SaResult.ok();
            }else {
                return SaResult.error("更新失败,检查参数");
            }
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/deleteShops")
    public SaResult deleteShops(@RequestBody Shops shops) {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            shops.setOwnerId(loginIdAsLong);
            boolean deleted = shopsService.deleteShops(shops);
            if(deleted){
                return SaResult.ok();
            }else {
                return SaResult.error("删除失败,检查参数");
            }
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }
    @PostMapping("/getShopsBySearch")
    @SaIgnore
    public SaResult getShopsBySearch(@RequestBody ShopsSearchBo shopsSearchBo) {
        try {
            return SaResult.data(shopsService.getShopsBySearch(shopsSearchBo));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @GetMapping("/getShopsById")
    public SaResult getShopsById(@RequestParam Long id) {
        try {
            return SaResult.data(shopsService.getShopsById(id));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @GetMapping("/getShopsByOwnerId")
    public SaResult getShopsByOwnerId() {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            return SaResult.data(shopsService.getShopsByOwnerId(loginIdAsLong));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }
}
