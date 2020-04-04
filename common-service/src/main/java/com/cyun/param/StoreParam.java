package com.cyun.param;

import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * Description:
 *
 * @author: xiayk
 * @date: 20/4/2 下午8:43
 **/
@Data
public class StoreParam extends PageParam {

    private String storeName;

    private String storeNick;

    private Date dateStart;

    private Date dateEnd;
}
