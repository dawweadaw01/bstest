package com.cdu.lhj.bstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cdu.lhj.bstest.pojo.Bo.CatDeBo;
import com.cdu.lhj.bstest.pojo.Bo.PageBo;
import com.cdu.lhj.bstest.pojo.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CatMapper extends BaseMapper<Cat> {
    IPage<Cat> getCatListByPage(IPage<?> page, @Param("catDeBo") CatDeBo catDeBo);
}