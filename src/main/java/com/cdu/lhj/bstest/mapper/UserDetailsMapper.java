package com.cdu.lhj.bstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.lhj.bstest.pojo.UserDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper extends BaseMapper<UserDetails> {
}