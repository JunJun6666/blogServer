package com.wang.server.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="Dict对象", description="")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    private String neKey;

    private String neValue;

    private String content;


}
