package com.cyun.param;


import lombok.Data;

/**
 * Created with IDEA
 * Description:
 *
 * @author: xiayk
 * @date: 20/4/2 下午9:32
 **/
@Data
public class SaveStoreParam {

    private String storeCode;

    private String storeName;

    private String storeNick;

    private Integer storeStatus;

    private String createUserId;
}
