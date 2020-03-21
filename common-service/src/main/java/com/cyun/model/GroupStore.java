package com.cyun.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "group_store")
public class GroupStore {
    /**
     * 商家id
     */
    @Id
    private String id;

    /**
     * 商家code
     */
    @Column(name = "store_code")
    private String storeCode;

    /**
     * 商家名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 商家昵称
     */
    @Column(name = "store_nick")
    private String storeNick;

    /**
     * 状态(0正常，1禁用，2删除)
     */
    @Column(name = "store_status")
    private Integer storeStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改者id
     */
    @Column(name = "update_user_id")
    private String updateUserId;

    /**
     * 获取商家id
     *
     * @return id - 商家id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商家id
     *
     * @param id 商家id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商家code
     *
     * @return store_code - 商家code
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * 设置商家code
     *
     * @param storeCode 商家code
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    /**
     * 获取商家名称
     *
     * @return store_name - 商家名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置商家名称
     *
     * @param storeName 商家名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取商家昵称
     *
     * @return store_nick - 商家昵称
     */
    public String getStoreNick() {
        return storeNick;
    }

    /**
     * 设置商家昵称
     *
     * @param storeNick 商家昵称
     */
    public void setStoreNick(String storeNick) {
        this.storeNick = storeNick;
    }

    /**
     * 获取状态(0正常，1禁用，2删除)
     *
     * @return store_status - 状态(0正常，1禁用，2删除)
     */
    public Integer getStoreStatus() {
        return storeStatus;
    }

    /**
     * 设置状态(0正常，1禁用，2删除)
     *
     * @param storeStatus 状态(0正常，1禁用，2删除)
     */
    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建者id
     *
     * @return create_user_id - 创建者id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建者id
     *
     * @param createUserId 创建者id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改者id
     *
     * @return update_user_id - 修改者id
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改者id
     *
     * @param updateUserId 修改者id
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}