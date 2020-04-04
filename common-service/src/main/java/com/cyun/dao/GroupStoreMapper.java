package com.cyun.dao;

import com.cyun.model.GroupStore;
import com.cyun.param.StoreParam;
import com.cyun.utils.mapper.CommonMapper;

import java.util.List;

public interface GroupStoreMapper extends CommonMapper<GroupStore> {

    List<GroupStore> selectlist(StoreParam param);

    Integer selectlistCount(StoreParam param);
}