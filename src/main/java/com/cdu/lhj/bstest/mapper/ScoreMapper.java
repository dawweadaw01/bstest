package com.cdu.lhj.bstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.lhj.bstest.pojo.Score;
import com.cdu.lhj.bstest.pojo.Vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    List<ScoreVo> getAllUserPreference();
}