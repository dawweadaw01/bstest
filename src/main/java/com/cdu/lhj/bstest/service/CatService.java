package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.CatDeBo;
import com.cdu.lhj.bstest.pojo.Cat;

import java.util.List;

public interface CatService extends IService<Cat>{

    boolean removeByCatIdAndId( Long catId, Long id);

    boolean updateByCatIdAndId(Cat cat, Long id);

    IPage<Cat> getCatListByPage(CatDeBo catDeBo);

    Cat getByShopIdAndCatId(Long userIdLong, Long catId);

    List<Cat> getCatByShopId(Long id);
}
