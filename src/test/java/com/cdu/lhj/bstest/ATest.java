package com.cdu.lhj.bstest;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cdu.lhj.bstest.pojo.Bo.ShopsSearchBo;
import com.cdu.lhj.bstest.pojo.Bo.UserSearchBo;
import com.cdu.lhj.bstest.pojo.Score;
import com.cdu.lhj.bstest.pojo.Shops;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.pojo.SysUserRole;
import com.cdu.lhj.bstest.service.*;
import jakarta.annotation.Resource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ATest {
    @Resource
    private ShopsService shopsService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private RecommendService recommendService;

    @Test
    public void test(){
        UserSearchBo userSearchBo = new UserSearchBo();
        userSearchBo.setPage(1);
        userSearchBo.setSize(100);
        IPage<SysUser> sysUserIPage = sysUserService.listUsers(userSearchBo);
        Shops shopsById = shopsService.getShopsById(25662670247936L);
        shopsById.setOwnerId(shopsById.getOwnerId()+1);
        List<SysUser> records = sysUserIPage.getRecords();
        for (SysUser record : records) {
            sysUserRoleService.saveUserRole(new SysUserRole(null, record.getId(), 25708423365376L));
            if(record.getId() != 25662670247936L){
                shopsById.setOwnerId(record.getId());
                shopsService.insertShops(shopsById);
            }
        }
    }

    @Test
    public void test1(){
        UserSearchBo userSearchBo = new UserSearchBo();
        userSearchBo.setPage(1);
        userSearchBo.setSize(100);
        IPage<SysUser> sysUserIPage = sysUserService.listUsers(userSearchBo);
        IPage<Shops> shopsBySearch = shopsService.getShopsBySearch(new ShopsSearchBo(null, 1, 10));
        List<SysUser> users = sysUserIPage.getRecords();
        List<Shops> shops = shopsBySearch.getRecords();
        for (SysUser user : users) {
            List<Shops> shuffledShops = new ArrayList<>(shops);
            Collections.shuffle(shuffledShops); // 随机排序商店列表
            int count = 0;
            for (Shops shop : shuffledShops) {
                if (count >= 4) {
                    break; // 达到30个评分则退出循环
                }
                Score score = new Score();
                score.setUserId(user.getId());
                score.setStoreId(shop.getId());
                // 1-5
                double v = Math.random() * 5 + 1;
                // 转换为整数
                int i = (int) v;
                // 转换为long类型
                score.setScore((long) i);
                scoreService.insertScore(score);
                count++;
            }
        }
    }

    @Test
    public void test2() throws TasteException {
        List<Long> recommend = recommendService.recommend(25662670247936L);
        System.out.println(recommend);
    }

}
