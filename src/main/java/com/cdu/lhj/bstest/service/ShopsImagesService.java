package com.cdu.lhj.bstest.service;

import com.cdu.lhj.bstest.pojo.Image;
import com.cdu.lhj.bstest.pojo.ShopsImages;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShopsImagesService extends IService<ShopsImages>{


    String saveImage(MultipartFile file, Long storeId);

    List<ShopsImages> getImages(Long storeId);

    boolean removeByImageIdAndId(Long storeId, Long imageId);
}
