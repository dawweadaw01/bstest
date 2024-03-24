package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagesService extends IService<Image>{

    String saveImage(MultipartFile file, Long id);

    List<Image> getImages(Long id);

    boolean removeByImageIdAndId(Long imageId, Long userIdLong);
}
