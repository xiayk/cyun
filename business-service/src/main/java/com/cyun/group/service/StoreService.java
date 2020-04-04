package com.cyun.group.service;

import com.cyun.model.GroupStore;
import com.cyun.param.EditStoreParam;
import com.cyun.param.SaveStoreParam;
import com.cyun.param.StoreParam;
import com.cyun.result.PageResult;
import com.cyun.result.StoreDetailResult;

public interface StoreService {

    PageResult getStores(StoreParam param);

    void addStore(SaveStoreParam param);

    void editStore(EditStoreParam param);

    GroupStore getStoreDetail(String id);

    void delStore(String id, String uid);
}
