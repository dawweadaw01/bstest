package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.lhj.bstest.pojo.Bo.CatDeBo;
import com.cdu.lhj.bstest.pojo.Bo.PageBo;
import com.cdu.lhj.bstest.service.CatCategoriesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.mapper.CatMapper;
import com.cdu.lhj.bstest.service.CatService;
@Service
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements CatService{

    @Resource
    private CatCategoriesService catCategoriesService;

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
    public List<Cat> getCatListByPage(CatDeBo catDeBo) {
        // 构造分页参数
        Page<Cat> page = new Page<>(catDeBo.getPageBo().getPage(), catDeBo.getPageBo().getSize());
        // 调用mapper查询
        List<Cat> listByPage = this.baseMapper.getCatListByPage(page, catDeBo);
        // 循环查询分类
        listByPage.forEach(cat -> cat.setCatCategories(catCategoriesService.getCatCategoriesById(cat.getCategoryId())));
        return listByPage;
    }
}
