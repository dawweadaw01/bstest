package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.ScoreMapper;
import com.cdu.lhj.bstest.pojo.Score;
import com.cdu.lhj.bstest.pojo.Vo.ScoreVo;
import com.cdu.lhj.bstest.service.ScoreService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService{

    @Override
    public boolean insertScore(Score score) {
        score.setId(SimpleTimestampIdGenerator.nextId());
        return save(score);
    }

    @Override
    @Cacheable(value = "score", key = "'allUserPreference'")
    public List<ScoreVo> getAllUserPreference() {
        // 查询所有用户评分
        return baseMapper.getAllUserPreference();
    }
}
