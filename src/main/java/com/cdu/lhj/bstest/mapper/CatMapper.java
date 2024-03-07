package com.cdu.lhj.bstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.lhj.bstest.pojo.Cat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatMapper extends BaseMapper<Cat> {
}