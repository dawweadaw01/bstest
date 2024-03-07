package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Bo.CatCategoriesBo;
import com.cdu.lhj.bstest.pojo.CatCategories;
import com.cdu.lhj.bstest.service.CatCategoriesService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catCategories")
public class CatCategoriesController {

    @Resource
    private CatCategoriesService catCategoriesService;

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/insertCatCategories")
    public SaResult insertCatCategories(@RequestBody CatCategories catCategories) {
        // 拿到id
        try {
            catCategories.setCategoryId(SimpleTimestampIdGenerator.nextId());
            return SaResult.data(catCategoriesService.save(catCategories));
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/deleteCatCategories")
    public SaResult deleteCatCategories(@RequestParam Long categoryId) {
        try {
            return SaResult.data(catCategoriesService.removeById(categoryId));
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    @SaCheckPermission(value = "admin", orRole = "super-admin")
    @PostMapping("/updateCatCategories")
    public SaResult updateCatCategories(@RequestBody CatCategories catCategories) {
        try {
            return SaResult.data(catCategoriesService.updateById(catCategories));
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    // 分页查询
    @PostMapping("/getCatCategories")
    public SaResult getCatCategories(@RequestBody CatCategoriesBo catCategoriesBo) {
        try {
            return SaResult.data(catCategoriesService.getCatCategoriesListByPage(catCategoriesBo));
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }
}
