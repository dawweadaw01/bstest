package com.cdu.lhj.bstest.service;

import com.cdu.lhj.bstest.pojo.Bo.CatCategoriesBo;
import com.cdu.lhj.bstest.pojo.CatCategories;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CatCategoriesService extends IService<CatCategories>{


    List<CatCategories> getCatCategoriesListByPage(CatCategoriesBo catCategoriesBo);
}
