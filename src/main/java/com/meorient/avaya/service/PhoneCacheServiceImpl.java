package com.meorient.avaya.service;

import com.meorient.avaya.mapper.PhoneCacheMapper;
import com.meorient.avaya.pojo.PhoneCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneCacheServiceImpl implements PhoneCacheService {

    @Autowired
    PhoneCacheMapper phoneCacheMapper;

    @Override
    public List<PhoneCache> getAllPhoneMsg() {
        return phoneCacheMapper.getAllPhoneMsg();
    }

    @Override
    public int insertOnePhoneCache(PhoneCache phoneCache) {
        return phoneCacheMapper.insertOnePhoneCache(phoneCache);
    }

    @Override
    public PhoneCache isExist(String num) {
        return phoneCacheMapper.isExist(num);
    }

    public boolean isNotExist(String num) {
        PhoneCache exist = isExist(num);
        return exist==null;
    }
}
