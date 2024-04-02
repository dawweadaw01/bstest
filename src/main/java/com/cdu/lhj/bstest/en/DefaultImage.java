package com.cdu.lhj.bstest.en;


import lombok.Getter;

@Getter
public enum DefaultImage {

    DEFAULT_IMAGE("https://banyan-yuncos-1315459122.cos.ap-chengdu.myqcloud.com/images/c894305a-4832-4b93-98e1-56ef91cf6065.jpeg");

    private final String url;

    DefaultImage(String url) {
        this.url = url;
    }
}
