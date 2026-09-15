package com.zhoouon.starter.datasource.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 基础设施层的基表，每个数据表应该包含的公共字段，如 createTime,updateTime,delFlag
 * 这里使用SuperBuilder注解是为了让子类在使用MapStructs转换时可以转换delFlag的值
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDO {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标志
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
