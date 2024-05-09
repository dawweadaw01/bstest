package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.ShopsSearchBo;
import com.cdu.lhj.bstest.pojo.Shops;

public interface ShopsService extends IService<Shops>{


    boolean insertShops(Shops shops);

    boolean updateShops(Shops shops);

    boolean deleteShops(Shops shops);

    IPage<Shops> getShopsBySearch(ShopsSearchBo shopsSearchBo);

    Shops getShopsByOwnerId(Long OwnerId);

    Shops getShopsById(Long id);

}
