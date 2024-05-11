package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.en.ShopStatus;
import com.cdu.lhj.bstest.mapper.ShopsMapper;
import com.cdu.lhj.bstest.pojo.Bo.ShopsSearchBo;
import com.cdu.lhj.bstest.pojo.Cat;
import com.cdu.lhj.bstest.pojo.Shops;
import com.cdu.lhj.bstest.pojo.ShopsImages;
import com.cdu.lhj.bstest.pojo.SysUserRole;
import com.cdu.lhj.bstest.service.CatService;
import com.cdu.lhj.bstest.service.ShopsImagesService;
import com.cdu.lhj.bstest.service.ShopsService;
import com.cdu.lhj.bstest.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopsServiceImpl extends ServiceImpl<ShopsMapper, Shops> implements ShopsService {

    @Resource
    private CatService catService;

    @Resource
    private ShopsImagesService shopsImagesService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    @Transactional
    @CacheEvict(value = "shops", allEntries = true)
    public boolean insertShops(Shops shops) {
        // 给用户赋予shop权限
        sysUserRoleService.saveUserRole(new SysUserRole(null,shops.getOwnerId(),25708423365376L));
        shops.setId(shops.getOwnerId());
        // 初始化为无状态申请中
        shops.setStatus(1);
        return save(shops);
    }

    @Override
    @CacheEvict(value = "shops", allEntries = true)
    public boolean updateShops(Shops shops) {
        //根据id和ownerId更新
        LambdaUpdateWrapper<Shops> eq = new LambdaUpdateWrapper<Shops>()
                .eq(Shops::getId, shops.getId())
                .eq(Shops::getOwnerId, shops.getOwnerId());
        // 先查询是否存在
        Shops one = getOne(new LambdaQueryWrapper<Shops>().eq(Shops::getId, shops.getId()));
        if (one == null) {
            throw new RuntimeException("商店不存在");
        }
        return update(shops, eq);
    }

    @Override
    @CacheEvict(value = "shops", allEntries = true)
    public boolean deleteShops(Shops shops) {
        LambdaUpdateWrapper<Shops> eq = new LambdaUpdateWrapper<Shops>()
                .eq(Shops::getId, shops.getId())
                .eq(Shops::getOwnerId, shops.getOwnerId());
        return remove(eq);
    }

    @Override
    @Cacheable(value = "shops", key = "#shopsSearchBo.keyword + '-' + #shopsSearchBo.page+'-' + #shopsSearchBo.size")
    public IPage<Shops> getShopsBySearch(ShopsSearchBo shopsSearchBo) {
        LambdaQueryWrapper<Shops> eq = new LambdaQueryWrapper<Shops>()
                .like(shopsSearchBo.getKeyword() != null, Shops::getName, shopsSearchBo.getKeyword())
                .like(shopsSearchBo.getKeyword() != null, Shops::getAddress, shopsSearchBo.getKeyword())
                .eq(Shops::getStatus, ShopStatus.OPEN.getStatus());
        if (shopsSearchBo.getPage() == null || shopsSearchBo.getPage() == 0) {
            shopsSearchBo.setPage(1);
        }
        if (shopsSearchBo.getSize() == null || shopsSearchBo.getSize() == 0) {
            shopsSearchBo.setSize(10);
        }
        return page(new Page<>(shopsSearchBo.getPage(), shopsSearchBo.getSize()), eq);
    }

    @Override
    //@Cacheable(value = "shops", key = "#OwnerId")
    public Shops getShopsByOwnerId(Long OwnerId) {
        Shops shops = lambdaQuery().eq(Shops::getOwnerId, OwnerId).one();
        shops.setShopImagesForManage(shopsImagesService.getImages(shops.getId()));
        return shops;
    }

    @Override
    public Shops getShopsById(Long id) {
        LambdaUpdateWrapper<Shops> eq = new LambdaUpdateWrapper<Shops>().eq(Shops::getId, id).eq(Shops::getStatus, ShopStatus.OPEN.getStatus());
        Shops shops = getOne(eq);
        List<Cat> catList = catService.getCatByShopId(shops.getId());
        List<String> catImages = new ArrayList<>();
        catList.forEach(cat -> {
            if (!cat.getImages().isEmpty()) {
                catImages.add(cat.getImages().get(0).getImageUrl());
            }
        });
        shops.setCatImages(catImages);
        shops.setShopImages(shopsImagesService.getImages(shops.getId()).stream().map(ShopsImages::getImageUrl).toList());
        return shops;
    }

}
