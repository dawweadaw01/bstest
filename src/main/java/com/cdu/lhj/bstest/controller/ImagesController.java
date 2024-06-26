package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.pojo.Image;
import com.cdu.lhj.bstest.service.CatService;
import com.cdu.lhj.bstest.service.ImagesService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    @Resource
    private ImagesService imagesService;

    @Resource
    private CatService catService;

    @PostMapping("upload")
    public SaResult uploadImage(@RequestParam MultipartFile file) {
        String url;
        try {
            String id = (String)StpUtil.getLoginId();
            Long idLong = Long.valueOf(id);
            url = imagesService.saveImage(file, idLong);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
        return SaResult.data(url);
    }

    @GetMapping("getList")
    public SaResult getImages() {
        return SaResult.data(imagesService.getImages(Long.valueOf((String)StpUtil.getLoginId())));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("adminUpload")
    public SaResult adminUploadImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        String url;
        try {
            url = imagesService.saveImage(file, id);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
        return SaResult.data(url);
    }

    @PostMapping("Delete")
    public SaResult DeleteImage(@RequestParam Long imageId) {
        try {
            String userId = (String)StpUtil.getLoginId();
            Long userIdLong = Long.valueOf(userId);
            if(!imagesService.removeByImageIdAndId(imageId, userIdLong)){
                return SaResult.error("删除失败, 无法越权删除,或者图片不存在");
            }else {
                return SaResult.ok();
            }
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("ShopCatDelete")
    public SaResult ShopCatDeleteImage(@RequestParam Long imageId,@RequestParam Long catId) {
        try {
            String userId = (String)StpUtil.getLoginId();
            Long userIdLong = Long.valueOf(userId);
            List<Cat> catByShopId = catService.getCatByShopId(userIdLong);
            // 查询是否是本店的猫
            if(catByShopId.stream().noneMatch(cat -> cat.getCatId().equals(catId))){
                return SaResult.error("无法删除不是本店的猫咪照片");
            }
            if(!imagesService.removeByImageIdAndId(imageId, catId)){
                return SaResult.error("删除失败, 无法越权删除,或者图片不存在");
            }else {
                return SaResult.ok();
            }
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("Update")
    public SaResult UpdateImage(@RequestBody Image image) {
        try {
            String id = (String)StpUtil.getLoginId();
            Long idLong = Long.valueOf(id);
            if(!Objects.equals(image.getId(), idLong)){
                return SaResult.error("无法越权修改，id不匹配");
            }
            return SaResult.data(imagesService.updateById(image));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }


    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @GetMapping("adminGetList")
    public SaResult adminGetImages(@RequestParam Long id) {
        return SaResult.data(imagesService.getImages(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @CacheEvict(value = "images", allEntries = true)
    @PostMapping("adminDelete")
    public SaResult adminDeleteImage(@RequestParam Long id) {
        return SaResult.data(imagesService.removeById(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("adminUpdate")
    @CacheEvict(value = "images", allEntries = true)
    public SaResult adminUpdateImage(@RequestBody Image image) {
        return SaResult.data(imagesService.updateById(image));
    }
}
