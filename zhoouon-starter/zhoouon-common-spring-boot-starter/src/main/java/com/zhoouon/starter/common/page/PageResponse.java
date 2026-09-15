package com.zhoouon.starter.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页响应封装类
 * 为什么不直接使用mybatis-plus的分页插件？
 * 因为这样所有层都需要依赖 mybatis-plus组件，在DDD的4层架构中不合适。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分页响应对象")
public class PageResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5853627689427566174L;

    /**
     * 当前页
     */
    @Schema(description = "当前页")
    private Long current;

    /**
     * 每页显示多少条
     */
    @Schema(description = "每页显示多少条")
    private Long size;

    /**
     * 总数
     */
    @Schema(description = "总数")
    private Long total;

    /**
     * 查询数据列表
     */
    @Schema(description = "数据列表")
    private List<T> records = Collections.emptyList();

    public PageResponse(long current, long size) {
        this(current, size, 0);
    }

    public PageResponse(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }

    public PageResponse<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

}
