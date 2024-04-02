package com.cdu.lhj.bstest.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.UserDetailsMapper;
import com.cdu.lhj.bstest.pojo.UserDetails;
import com.cdu.lhj.bstest.service.UserDetailsService;
@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserDetailsMapper, UserDetails> implements UserDetailsService{

}
