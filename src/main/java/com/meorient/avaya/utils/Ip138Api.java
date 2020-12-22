package com.meorient.avaya.utils;

import java.util.concurrent.Callable;

public class Ip138Api implements Callable<String>{

	private String num;
	
	public Ip138Api(String num) {
		this.num = num;
	}


	public String call() throws Exception {
		String result = HttpClient.sendGet("https://www.ip138.com/mobile.asp?mobile="+num+"&action=mobile");
		if(!result.contains("杭州")) {
			num = "0" + num;
		}
		return num;
	}

	// for test
//	public static void main(String[] args) throws Exception {
//		Ip138Api api = new Ip138Api("18758238777");
//		System.out.println(api.call());
//	}
};
