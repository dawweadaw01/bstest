package com.cdu.lhj.bstest.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.mapper.CatMapper;
import com.cdu.lhj.bstest.service.CatService;
@Service
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements CatService{

}
