package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.CatMapper;
import com.cdu.lhj.bstest.pojo.Bo.CatDeBo;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.service.CatCategoriesService;
import com.cdu.lhj.bstest.service.CatService;
import com.cdu.lhj.bstest.service.ImagesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements CatService{

    @Resource
    private CatCategoriesService catCategoriesService;

    @Resource
    private ImagesService imagesService;

    @Override
    public boolean removeByCatIdAndId(Long catId, Long id) {
        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<Cat>().eq(Cat::getCatId, catId).eq(Cat::getShopId, id);
        return this.remove(wrapper); // 删除
    }

    @Override
    public boolean updateByCatIdAndId(Cat cat, Long id) {
        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<Cat>().eq(Cat::getCatId, cat.getCatId()).eq(Cat::getShopId, id);
        return this.update(cat, wrapper); // 更新
    }

    @Override
    public IPage<Cat> getCatListByPage(CatDeBo catDeBo) {
        // 构造分页参数
        Page<Cat> page = new Page<>(catDeBo.getPage(), catDeBo.getSize());
        // 调用mapper查询
        IPage<Cat> listByPage = this.baseMapper.getCatListByPage(page, catDeBo);
        // 循环查询分类
        listByPage.getRecords().forEach(cat -> cat.setCatCategories(catCategoriesService.getCatCategoriesById(cat.getCategoryId())));
        // 循环查询图片
        listByPage.getRecords().forEach(cat -> cat.setImages(imagesService.getImages(cat.getCatId())));
        return listByPage;
    }

    @Override
    public Cat getByShopIdAndCatId(Long userIdLong, Long catId) {
        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<Cat>().eq(Cat::getShopId, userIdLong).eq(Cat::getCatId, catId);
        return this.getOne(wrapper);
    }

    @Override
    public List<Cat> getCatByShopId(Long id) {
        LambdaQueryWrapper<Cat> wrapper = new LambdaQueryWrapper<Cat>().eq(Cat::getShopId, id);
        List<Cat> list = this.list(wrapper);
        list.forEach(cat -> {
            cat.setCatCategories(catCategoriesService.getCatCategoriesById(cat.getCategoryId()));
            cat.setImages(imagesService.getImages(cat.getCatId()));
        });
        return list;
    }
}
 