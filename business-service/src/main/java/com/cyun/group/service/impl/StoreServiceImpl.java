package com.cyun.group.service.impl;

import com.cyun.dao.GroupStoreMapper;
import com.cyun.group.service.StoreService;
import com.cyun.model.GroupStore;
import com.cyun.param.EditStoreParam;
import com.cyun.param.SaveStoreParam;
import com.cyun.param.StoreParam;
import com.cyun.result.PageResult;
import com.cyun.result.StoreDetailResult;
import com.cyun.utils.bean.BeanRewriteUtils;
import com.cyun.utils.spring.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private GroupStoreMapper groupStoreMapper;

    @Override
    public PageResult getStores(StoreParam param) {
        return PageResult.PageResult(groupStoreMapper.selectlist(param), groupStoreMapper.selectlistCount(param));
    }

    @Override
    public void addStore(SaveStoreParam param) {
        GroupStore store = new GroupStore();
        store = (GroupStore)BeanRewriteUtils.populate(param, new GroupStore());
        store.setCreateDate(new Date());
        store.setCreateUserId(param.getCreateUserId());
        store.setId(UUIDFactory.newUUID());
        groupStoreMapper.insert(store);
    }

    @Override
    public void editStore(EditStoreParam param) {
        param.setUpdateDate(new Date());
        groupStoreMapper.updateByPrimaryKeySelective((GroupStore) BeanRewriteUtils.populate(param, new GroupStore()));
    }

    @Override
    public GroupStore getStoreDetail(String id) {
        return groupStoreMapper.selectByPrimaryKey(id);//(StoreDetailResult) BeanRewriteUtils.populate(groupStoreMapper.selectByPrimaryKey(id), new GroupStore());
    }

    @Override
    public void delStore(String id, String uid) {
        GroupStore store = new GroupStore();
        store.setId(id);
        store.setUpdateUserId(uid);
        store.setUpdateDate(new Date());
        store.setStoreStatus(2);
        groupStoreMapper.updateByPrimaryKeySelective(store);
    }
}
