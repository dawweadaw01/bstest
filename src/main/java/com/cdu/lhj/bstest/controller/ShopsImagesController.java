package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Shops;
import com.cdu.lhj.bstest.service.ShopsImagesService;
import com.cdu.lhj.bstest.service.ShopsService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/shopImages")

public class ShopsImagesController {

    @Resource
    private ShopsImagesService shopsImagesService;

    @Resource
    private ShopsService shopsService;

    @PostMapping("upload")
    public SaResult uploadImage(@RequestParam MultipartFile file) {
        String url;
        try {
            String id = (String) StpUtil.getLoginId();
            Long idLong = Long.valueOf(id);
            //查询商店是否存在
            Shops shops = shopsService.getShopsByOwnerId(idLong);
            if (shops == null) {
                return SaResult.error("商店不存在,或者不能越权上传图片");
            }
            url = shopsImagesService.saveImage(file, shops.getId());
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
        return SaResult.data(url);
    }

    @GetMapping("getList")
    public SaResult getImages() {
        String id = (String) StpUtil.getLoginId();
        Long idLong = Long.valueOf(id);
        //查询商店是否存在
        Shops shops = shopsService.getShopsByOwnerId(idLong);
        if (shops == null) {
            return SaResult.error("商店不存在");
        }
        return SaResult.data(shopsImagesService.getImages(shops.getId()));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("adminUpload")
    public SaResult adminUploadImage(@RequestParam MultipartFile file, @RequestParam Long storeId) {
        String url;
        try {
            url = shopsImagesService.saveImage(file, storeId);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
        return SaResult.data(url);
    }

    @PostMapping("Delete")
    public SaResult DeleteImage(@RequestParam Long imageId) {
        try {
            String userId = (String) StpUtil.getLoginId();
            Long userIdLong = Long.valueOf(userId);
            Shops shops = shopsService.getShopsByOwnerId(userIdLong);
            if (shops == null) {
                return SaResult.error("商店不存在");
            }
            if (!shopsImagesService.removeByImageIdAndId(shops.getId(), imageId)) {
                return SaResult.error("删除失败, 无法越权删除,或者图片不存在");
            } else {
                return SaResult.ok("删除成功");
            }
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }


    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @GetMapping("adminGetList")
    public SaResult adminGetImages(@RequestParam Long storeId) {
        return SaResult.data(shopsImagesService.getImages(storeId));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @CacheEvict(value = "images", allEntries = true)
    @PostMapping("adminDelete")
    public SaResult adminDeleteImage(@RequestParam Long id) {
        return SaResult.data(shopsImagesService.removeById(id));
    }

}
