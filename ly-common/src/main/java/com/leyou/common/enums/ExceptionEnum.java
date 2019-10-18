package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    PRICE_CANNOT_BR_NULL(400,"价格不能为空"),
    CATEGORY_NOT_FIND(404,"商品分类没查到"),
    ;
    private int code;
    private String msg;
}
