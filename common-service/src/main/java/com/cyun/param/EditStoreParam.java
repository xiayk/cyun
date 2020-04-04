package com.cyun.param;

import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * Description:
 *
 * @author: xiayk
 * @date: 20/4/3 上午11:29
 **/
@Data
public class EditStoreParam {

    private String id;

    private String storeCode;

    private String storeName;

    private String storeNick;

    private Integer storeStatus;

    private String updateUserId;

    private Date updateDate;
}
