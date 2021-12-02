package com.wang.server.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Resource对象", description="")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源名")
    private String resourceName;

    @ApiModelProperty(value = "权限路径")
    private String url;

    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    @ApiModelProperty(value = "父权限id")
    private Integer parentId;

    @ApiModelProperty(value = "是否匿名访问 0否 1是")
    private Boolean isAnonymous;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
