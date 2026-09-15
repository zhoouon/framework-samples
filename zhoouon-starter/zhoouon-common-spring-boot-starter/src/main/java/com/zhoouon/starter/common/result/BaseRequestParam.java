package com.zhoouon.starter.common.result;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhoudong
 * @Description: 请求参数封装
 * @Date: 2024-07-09 11:41
 * @Version: 1.0.0
 **/
public class BaseRequestParam<T> {

    private String requestSeq;
    private String systemCode;
    private String requestDate;
    private String requestTime;
    private String tenantNo;
    private String tenantName;
    private String orgNo;
    private String orgName;
    private String userNo;
    private String userName;
    private String userType;
    private String isGroupCustomer;
    private List<String> roleList;
    private Map<String, List> dataAuthority;
    private T requestBody;

    public BaseRequestParam() {
    }

    public String getRequestSeq() {
        return this.requestSeq;
    }

    public void setRequestSeq(String requestSeq) {
        this.requestSeq = requestSeq;
    }

    public String getSystemCode() {
        return this.systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getTenantNo() {
        return this.tenantNo;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public String getTenantName() {
        return this.tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getOrgNo() {
        return this.orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsGroupCustomer() {
        return this.isGroupCustomer;
    }

    public void setIsGroupCustomer(String isGroupCustomer) {
        this.isGroupCustomer = isGroupCustomer;
    }

    public List<String> getRoleList() {
        return this.roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public Map<String, List> getDataAuthority() {
        return this.dataAuthority;
    }

    public void setDataAuthority(Map<String, List> dataAuthority) {
        this.dataAuthority = dataAuthority;
    }

    public T getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(T requestBody) {
        this.requestBody = requestBody;
    }
}
