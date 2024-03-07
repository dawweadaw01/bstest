package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.lhj.bstest.pojo.Bo.CatCategoriesBo;
import com.cdu.lhj.bstest.service.ImagesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.CatCategoriesMapper;
import com.cdu.lhj.bstest.pojo.CatCategories;
import com.cdu.lhj.bstest.service.CatCategoriesService;
@Service
public class CatCategoriesServiceImpl extends ServiceImpl<CatCategoriesMapper, CatCategories> implements CatCategoriesService{


    @Resource
    private ImagesService imagesService;
    @Override
    public List<CatCategories> getCatCategoriesListByPage(CatCategoriesBo catCategoriesBo) {
        // 判断是否有分页信息，没有则赋默认值
        if (catCategoriesBo.getPage() == null) {
            catCategoriesBo.setPage(1);
        }
        if (catCategoriesBo.getSize() == null) {
            catCategoriesBo.setSize(10);
        }
        Page<CatCategories> catCategoriesPage = new Page<>(catCategoriesBo.getPage(), catCategoriesBo.getSize());
        LambdaQueryWrapper<CatCategories> wrapper
                = new LambdaQueryWrapper<CatCategories>().eq(CatCategories::getCategoryId, catCategoriesBo.getCategoryId());
        List<CatCategories> records = this.baseMapper.selectPage(catCategoriesPage, wrapper).getRecords();
        records.forEach(catCategories -> catCategories.setImages(imagesService.getImages(catCategories.getCategoryId())));
        return records;
    }
}
