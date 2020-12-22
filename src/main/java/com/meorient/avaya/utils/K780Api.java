package com.meorient.avaya.utils;

import java.util.Map;
import java.util.concurrent.Callable;

public class K780Api implements Callable<String>{

	private String num;
	private String result;

	public K780Api(String num) {
		this.num = num;
	}

	public String call() throws Exception {
		result = HttpClient.sendGet("http://api.k780.com/?app=phone.get&phone="+num+"&appkey=*&sign=*&format=json");
		if(result.indexOf("\"success\":\"0\"") > 0) {
			// 超过免费次数，弃用
			Thread.sleep(8000L);
			return num;
		}

		// 0571 杭州归属地
		if (result.indexOf("0571") < 0) {
			num = "0" + num;
		}
		return num;
	}

	// 获取响应
	public String getResult() {
		return result;
	}

	// for test
//	public static void main(String[] args) throws Exception {
//		K780Api api = new K780Api("13857772230");
//		System.out.println(api.call());
//	}
}
