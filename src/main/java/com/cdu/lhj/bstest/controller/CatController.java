package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Bo.CatDeBo;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.service.CatService;
import com.cdu.lhj.bstest.service.ImagesService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/api/cat")
@SaCheckRole(value ={"shop","super-admin","admin"}, mode = SaMode.OR)
public class CatController {

    @Resource
    private CatService catService;

    @Resource
    private ImagesService imagesService;

    @PostMapping("/insertCat")
    public SaResult insertCat(@RequestBody Cat cat) {
        // 拿到id
        String loginId = (String) StpUtil.getLoginId();
        Long id = Long.valueOf(loginId);
        cat.setShopId(id);
        cat.setCatId(SimpleTimestampIdGenerator.nextId());
        return SaResult.data(catService.save(cat));
    }

    @PostMapping("/deleteCat")
    public SaResult deleteCat(@RequestParam Long catId) {
        String loginId = (String) StpUtil.getLoginId();
        Long id = Long.valueOf(loginId);
        return SaResult.data(catService.removeByCatIdAndId(catId, id));
    }

    @PostMapping("/updateCat")
    public SaResult updateCat(@RequestBody Cat cat) {
        String loginId = (String) StpUtil.getLoginId();
        Long id = Long.valueOf(loginId);
        return SaResult.data(catService.updateByCatIdAndId(cat, id));
    }

    // 分页查询
    @PostMapping("/getCat")
    public SaResult getCat(@RequestBody CatDeBo catDeBo) {
        String loginId = (String) StpUtil.getLoginId();
        Long id = Long.valueOf(loginId);
        catDeBo.setShopId(id);
        return SaResult.data(catService.getCatListByPage(catDeBo));
    }

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/adminGetCatByIdByPage")
    public SaResult getCatById(@RequestBody CatDeBo catDeBo) {
        return SaResult.data(catService.getCatListByPage(catDeBo));
    }

    @PostMapping("catUpload")
    public SaResult catUploadImage(@RequestParam MultipartFile file, @RequestParam Long catId) {
        String url;
        try {
            String userId = (String)StpUtil.getLoginId();
            Long userIdLong = Long.valueOf(userId);
            if(catService.getByshopIdAndCatId(userIdLong, catId) == null){
                return SaResult.error("无法上传不是本店的猫咪照片");
            }
            url = imagesService.saveImage(file, catId);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
        return SaResult.data(url);
    }

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/adminDeleteCat")
    public SaResult adminDeleteCat(@RequestParam Long catId) {
        return SaResult.data(catService.removeById(catId));
    }

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/adminUpdateCat")
    public SaResult adminUpdateCat(@RequestBody Cat cat) {
        return SaResult.data(catService.updateById(cat));
    }
}
