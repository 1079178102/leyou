package com.leyou.item.controller;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Item> saveItem(Item item){
        // 校验价格
        if(item.getPrice() == null){
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BR_NULL);
         }
        item = itemService.saveItem(item);
        // CREATED 201 已建立(保存成功)
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
}
