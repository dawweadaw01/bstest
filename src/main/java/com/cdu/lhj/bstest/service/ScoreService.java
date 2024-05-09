package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Score;
import com.cdu.lhj.bstest.pojo.Vo.ScoreVo;

import java.util.List;

public interface ScoreService extends IService<Score>{


    boolean insertScore(Score score);


    List<ScoreVo> getAllUserPreference();

    Long getScoreByUserIdAndStoreId(Long storeId, long loginIdAsLong);
}
