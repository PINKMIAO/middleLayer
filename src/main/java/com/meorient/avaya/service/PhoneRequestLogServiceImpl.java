package com.meorient.avaya.service;

import com.meorient.avaya.mapper.PhoneRequestLogMapper;
import com.meorient.avaya.pojo.PhoneRequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneRequestLogServiceImpl implements PhoneRequestLogService{

    @Autowired
    PhoneRequestLogMapper phoneRequestLogMapper;

    @Override
    public List<PhoneRequestLog> getAllRequestLog() {
        return phoneRequestLogMapper.getAllRequestLog();
    }

    @Override
    public int insertPhoneRequestLog(PhoneRequestLog phoneRequestLog) {
        return phoneRequestLogMapper.insertPhoneRequestLog(phoneRequestLog);
    }
}
