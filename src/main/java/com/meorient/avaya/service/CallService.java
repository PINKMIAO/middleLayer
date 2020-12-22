package com.meorient.avaya.service;

import com.meorient.avaya.pojo.PhoneCache;
import com.meorient.avaya.pojo.PhoneRequestLog;
import com.meorient.avaya.utils.HttpClient;
import com.meorient.avaya.utils.Ip138Api;
import com.meorient.avaya.utils.K780Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class CallService {

	@Autowired
	PhoneCacheServiceImpl phoneCacheService;
	@Autowired
	PhoneRequestLogServiceImpl phoneRequestLogService;

	/**
	 * 杭州、上海AVAYA拨打
	 */
	public Map<String, String> callA(String phoneNum) { 	// 参数已经收到了上一层的过滤，所以这里能传进来的是电话号码

		Map<String, String> map = new HashMap<>();
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 两对象
		PhoneCache cache = new PhoneCache();
		PhoneRequestLog log = new PhoneRequestLog();

		PhoneCache phoneCache = null;
		try {
			map.put("originPhone", phoneNum);

			// 开始时间
			long l1 = System.currentTimeMillis();
			String startTime = simpleDateFormat.format(l1);

			String num = map.get("originPhone");						// 13134439827
			cache.setOriginPhone(num);
			log.setOriginPhone(num);

		
			if (num.matches("01[0-9]{10}")) {
				// 有加0的手机号，将0去掉
				num = num.substring(1);
			}

			// 判断数据库中是否存在此号码
			if (phoneCacheService.isNotExist(map.get("originPhone"))) {

				if (num.length() == 11 && num.startsWith("1")) {
					// api判断手机号归属地
					K780Api api1= new K780Api(num);
					CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPool);
					completionService.submit(api1);

					try {
						// 执行了查询归属地
						num = completionService.poll(3, TimeUnit.SECONDS).get();
					} catch (Exception e) {
						// 第一次记录
						map.put("msg1", String.valueOf(e));
						// 超时处理，进行第二组API调用
						completionService = new ExecutorCompletionService<String>(threadPool);
						Callable<String> api2 = new Ip138Api(num);
						completionService.submit(api2);
						try {
							num = completionService.poll(10, TimeUnit.SECONDS).get();
						} catch (Exception e2) {
							// 第二次记录
							map.put("msg2", String.valueOf(e2));
						}
					}
					log.setResponseData(api1.getResult());				// 存入响应值
				}

				cache.setActualPhone(num);								// 修改后电话
				cache.setCreateTime(simpleDateFormat.format(System.currentTimeMillis())); // 创建信息时间
				// 插入单条缓存数据
				phoneCacheService.insertOnePhoneCache(cache);
			}

			// 将记录保存下来
			long l2 = System.currentTimeMillis();
			String endTime = simpleDateFormat.format(l2);
			String runTime = String.valueOf(l2 - l1);
			log.setReqTime(startTime);									// 请求时间
			log.setRunTime(runTime);									// 运行时长
			log.setRequestData(String.valueOf(map.entrySet()));			// 请求数据
			phoneRequestLogService.insertPhoneRequestLog(log);

			phoneCache = phoneCacheService.isExist(map.get("originPhone"));
			// 能取到就说明没问题，没取到就说明里面部分出问题了，也就记录下 catch 的两 msg1、2 信息
			judgeCode(map, phoneCache);
			map.put("data", phoneCache.getActualPhone());

		} catch (Exception e) {
			log(e.toString());
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}

		return map;
	}

	// 判断数据是否有效
	public void judgeCode(Map<String, String> map, Object obj) {

		if (obj != null) {
			map.put("code", "1");
		} else {
			map.put("code", "0");
		}

	}

	/**
	 * 云客用户拨打
	 */
//	public static void call(String[] args) {
//		Map<String, String> map = new HashMap<String, String>(16);
//
//		System.out.println(args[0]);
//		String params = args[0].replaceAll("'omex://", "").replaceAll("/", "").replace("omex:", "");
//		System.out.println(params);
//		for (String param : params.split("&")) {
//			String[] tokens = param.split("=");
//			map.put(tokens[0], tokens[1]);
//		}
//		String num = map.get("num");
//		String user = map.get("user");
//
////			String num = "18368844873";
////			String user = "57035";
//
//		String timestamp = String.valueOf(System.currentTimeMillis());
//		Map<String, String> headers = new HashMap<String, String>(16);
//		headers.put("timestamp", timestamp);
//		headers.put("company", "auyy7f");
//		headers.put("signKey", "yk-auyy7f-yu9ixf");
//		headers.put("partnerId", MD5Util.encryptToMD5(user));
//		headers.put("sign", (CryptUtils
//				.GetMD5Code("yk-auyy7f-yu9ixf" + "auyy7f" + MD5Util.encryptToMD5(user) + timestamp).toUpperCase()));
//
//		String param = "{\"phone\":\"" + num + "\"}";
//
//		String result = HttpClient.sendPost("https://phone.yunkecn.com/open/call/partnerCustomer", param, headers);
//		log(new Date() + "--" + user + "--" + num + "--" + result);
//	}
//
	/**
	 * 云客(私有化) 用户拨打
	 */
//	public static void callC(String[] args) {
//		Map<String, String> map = new HashMap<String, String>(16);
//
//		System.out.println(args[0]);
//		String params = args[0].replaceAll("'omex://", "").replaceAll("/", "").replace("omex:", "");
//		System.out.println(params);
//		for (String param : params.split("&")) {
//			String[] tokens = param.split("=");
//			map.put(tokens[0], tokens[1]);
//		}
//		String num = map.get("num");
//		String user = map.get("user");
//
////		String num = "18368844873";
////		String user = "56548";
//
//		String timestamp = String.valueOf(System.currentTimeMillis());
//		Map<String, String> headers = new HashMap<String, String>(16);
//		headers.put("timestamp", timestamp);
//		headers.put("company", "fey2ib");
//		headers.put("signKey", "FE917712BC2F4AC18F5C15A810328538");
//		headers.put("partnerId", MD5Util.encryptToMD5(user));
//		headers.put("sign", (CryptUtils
//				.GetMD5Code("FE917712BC2F4AC18F5C15A810328538" + "fey2ib" + MD5Util.encryptToMD5(user) + timestamp).toUpperCase()));
//
//		String param = "{\"phone\":\"" + num + "\"}";
//
//		String result = HttpClient.sendPost("https://yk.meorient.com/open/call/partnerCustomer", param, headers);
//		log(new Date() + "--" + user + "--" + num + "--" + result);
//	}

	public static void log(String result) {
		// 简单日志
		File f = new File("D:/yunke.log");
		FileWriter fw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f, true);
			fw.write(result + "\r\n");
			fw.flush();
		} catch (Exception e) {

		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
