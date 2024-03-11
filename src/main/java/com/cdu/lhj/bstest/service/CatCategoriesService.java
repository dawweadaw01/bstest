package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cdu.lhj.bstest.pojo.Bo.CatCategoriesBo;
import com.cdu.lhj.bstest.pojo.CatCategories;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CatCategoriesService extends IService<CatCategories>{

    CatCategories getCatCategoriesById(Long id);

    IPage<CatCategories> getCatCategoriesListByPage(CatCategoriesBo catCategoriesBo);
}
