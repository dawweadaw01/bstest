package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Score;
import com.cdu.lhj.bstest.service.ScoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceController {

    @Resource
    private ScoreService scoreService;


    @PostMapping("/updateScore")
    public SaResult updateScore(@RequestBody Score score){
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            score.setUserId(loginIdAsLong);
            return SaResult.data(scoreService.insertScore(score));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @GetMapping("/getScore")
    public SaResult getScore(@RequestParam Long storeId){
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            return SaResult.data(scoreService.getScoreByUserIdAndStoreId(storeId,loginIdAsLong));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

}
