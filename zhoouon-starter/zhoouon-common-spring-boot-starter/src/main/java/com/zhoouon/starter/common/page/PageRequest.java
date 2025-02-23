package com.zhoouon.starter.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Description:
 * 分页请求对象
 * 如果需要构建复杂查询，则可以让查询类继承此分页基类
 */
@Data
@Schema(description = "分页参数")
public class PageRequest {

    @Schema(description = "当前页数")
    private Long current;

    @Schema(description = "每页显示条数")
    private Long size;

    /**
     * 默认构造器，当分页参数不传时提供默认值
     */
    public PageRequest() {
        this.size = 10L;
        this.current = 1L;
    }
}
