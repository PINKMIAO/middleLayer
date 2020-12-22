package com.meorient.avaya.mapper;

import com.meorient.avaya.pojo.PhoneCache;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PhoneCacheMapper {

    // 测试 => 获取全信息
    List<PhoneCache> getAllPhoneMsg();
    // 插入单条电话信息
    int insertOnePhoneCache(PhoneCache phoneCache);
    // 获取单条电话信息，判断存在
    PhoneCache isExist(String num);

}
