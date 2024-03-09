package com.cdu.lhj.bstest.service.impl;

import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import com.cdu.lhj.bstest.util.TencentCOSUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.pojo.Image;
import com.cdu.lhj.bstest.mapper.ImagesMapper;
import com.cdu.lhj.bstest.service.ImagesService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Image> implements ImagesService{


    @Resource
    private TencentCOSUtil tencentCOSUtil;
    @Override
    @Transactional
    @CacheEvict(value = "images", key = "#id")
    public String saveImage(MultipartFile file, Long id){
        //先上传
        String url = tencentCOSUtil.upLoadFile(file);
        // 再保存
        Image image = new Image();
        image.setImageUrl(url);
        image.setId(id);
        image.setImageId(SimpleTimestampIdGenerator.nextId());
        boolean save = save(image);
        if (!save){
            throw new RuntimeException("保存失败");
        }
        return url;

    }

    @Override
    @Cacheable(value = "images", key = "#id")
    public List<Image> getImages(Long id) {
        return this.lambdaQuery().eq(Image::getId, id).list();
    }

    @Override
    @CacheEvict(value = "images", key = "#userIdLong")
    public boolean removeByImageIdAndId(Long imageId, Long userIdLong) {
        return this.lambdaUpdate().eq(Image::getId, userIdLong).eq(Image::getImageId, imageId).remove();
    }
}
