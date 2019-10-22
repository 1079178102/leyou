package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOUND(404,"品牌不存在"),
    CATEGORY_NOT_FIND(404,"商品分类没查到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效的文件类型")
    ;
    private int code;
    private String msg;
}
