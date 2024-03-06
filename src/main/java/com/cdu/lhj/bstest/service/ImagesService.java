package com.cdu.lhj.bstest.service;

import com.cdu.lhj.bstest.pojo.Image;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagesService extends IService<Image>{

    @Transactional
    String saveImage(MultipartFile file, Long id);

    List<Image> getImages(Long id);

    boolean removeByImageIdAndId(Long imageId, Long userIdLong);
}
