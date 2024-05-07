package com.cdu.lhj.bstest.en;


import lombok.Getter;

@Getter
public enum ShopStatus {
    OPEN(1),
    APPLICATION(0),
    CLOSE(2);
    private final Integer status;

    ShopStatus(int status) {
        this.status = status;
    }
}
