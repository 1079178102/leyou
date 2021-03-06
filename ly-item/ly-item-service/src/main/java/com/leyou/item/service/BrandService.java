package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)){
            // 过滤条件
            example.createCriteria().orLike("name","%" + key + "%")
                    .orEqualTo("letter",key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)){
            String orderByClasuser = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClasuser);
        }
        // 查询
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        // 解析分页结果
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        return new PageResult<>(pageInfo.getTotal(),list);
    }

    @Transactional // 开启事务
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增品牌
        brand.setId(null);
        int count = brandMapper.insertSelective(brand);
        if(count != 1){
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        // 新增中间表
        for (Long cid : cids) {
            int count1 = brandMapper.insertCategoryBrand(cid, brand.getId());// 此处的brand的id会在上面sql执行后自动回显至brand
            if(count1 != 1){
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }
}
