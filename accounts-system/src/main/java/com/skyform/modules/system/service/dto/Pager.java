package com.skyform.modules.system.service.dto;

import java.util.List;
public class Pager<T> {
    private List<T> content;//返回的记录集合
    private long totalElements;//总记录条数
    public List<T> getContent() {
        return content;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
