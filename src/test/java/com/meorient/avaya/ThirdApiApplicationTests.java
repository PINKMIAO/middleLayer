package com.meorient.avaya;

import com.meorient.avaya.pojo.PhoneCache;
import com.meorient.avaya.pojo.PhoneRequestLog;
import com.meorient.avaya.service.CallService;
import com.meorient.avaya.service.PhoneCacheServiceImpl;
import com.meorient.avaya.service.PhoneRequestLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ThirdApiApplicationTests {

    @Autowired
    CallService callService;
    @Autowired
    PhoneCacheServiceImpl phoneCacheService;
    @Autowired
    PhoneRequestLogServiceImpl phoneRequestLogService;

    @Test
    void contextLoads() {
        String str = "13134439823";
        Map<String, String> map = callService.callA(str);
        System.out.println(map.entrySet());
    }

    @Test
    public void getAllPhoneCache() {
        List<PhoneCache> allPhoneMsg = phoneCacheService.getAllPhoneMsg();
        for (PhoneCache phoneCache : allPhoneMsg) {
            System.out.println(phoneCache);
        }
    }
    @Test
    public void isExist() {
        if (phoneCacheService.isNotExist("13134439827")) {
            System.out.println("不存在");
        } else {
            System.out.println("存在");
        }
    }
    @Test
    public void insertOneCache() {
        PhoneCache cache = new PhoneCache(
                "131332211",
                "0131332211",
               "2020-12-12 15:15:15"
                );
        phoneCacheService.insertOnePhoneCache(cache);
    }
    @Test
    public void insertRequestLog() {
        PhoneRequestLog log = new PhoneRequestLog();
        log.setOriginPhone("13134439827");
        log.setRequestData("[hello]");
        log.setReqTime("2020-12-12 15:15:15");
        log.setRunTime("50");
        int flag = phoneRequestLogService.insertPhoneRequestLog(log);
        if (flag > 0) {
            System.out.println("succeed");
        } else {
            System.out.println("error");
        }
    }

    @Test
    public void getAllLog() {
        List<PhoneRequestLog> allRequestLog = phoneRequestLogService.getAllRequestLog();
        for (PhoneRequestLog phoneRequestLog : allRequestLog) {
            System.out.println(phoneRequestLog);
        }
    }


}
