package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.ShopsImagesMapper;
import com.cdu.lhj.bstest.pojo.ShopsImages;
import com.cdu.lhj.bstest.service.ShopsImagesService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import com.cdu.lhj.bstest.util.TencentCOSUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ShopsImagesServiceImpl extends ServiceImpl<ShopsImagesMapper, ShopsImages> implements ShopsImagesService {

    @Resource
    private TencentCOSUtil tencentCOSUtil;

    @Override
    @Transactional
    @CacheEvict(value = "shopsImages", allEntries = true)
    public String saveImage(MultipartFile file, Long storeId) {
        //先上传
        String url = tencentCOSUtil.upLoadFile(file);
        // 再保存
        ShopsImages image = new ShopsImages();
        image.setImageUrl(url);
        image.setStoreId(storeId);
        image.setId(SimpleTimestampIdGenerator.nextId());
        boolean save = save(image);
        if (!save) {
            throw new RuntimeException("保存失败");
        }
        return url;

    }

    @Override
    @Cacheable(value = "shopsImages", key = "#storeId")
    public List<ShopsImages> getImages(Long storeId) {
        return this.lambdaQuery().eq(ShopsImages::getStoreId, storeId).list();
    }

    @Override
    @CacheEvict(value = "shopsImages", allEntries = true)
    public boolean removeByImageIdAndId(Long storeId, Long imageId) {
        return this.lambdaUpdate().eq(ShopsImages::getStoreId, storeId).eq(ShopsImages::getId, imageId).remove();
    }
}
