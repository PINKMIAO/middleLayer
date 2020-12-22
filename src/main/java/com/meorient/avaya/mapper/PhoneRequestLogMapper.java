package com.meorient.avaya.mapper;

import com.meorient.avaya.pojo.PhoneRequestLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PhoneRequestLogMapper {

    // 测试 => 获取全信息
    List<PhoneRequestLog> getAllRequestLog();
    // 插入一条请求记录
    int insertPhoneRequestLog(PhoneRequestLog phoneRequestLog);

}
