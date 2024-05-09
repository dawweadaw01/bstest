package com.cdu.lhj.bstest.controller;

import com.cdu.lhj.bstest.service.ScoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreServiceController {

    @Resource
    private ScoreService scoreService;

}
