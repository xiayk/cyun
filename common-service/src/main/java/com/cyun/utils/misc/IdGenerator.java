/*******************************************************************************
 * Copyright (c) 2005, 2017 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cyun.utils.misc;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;


import com.cyun.text.EncodeUtil;
import com.cyun.utils.number.RandomUtil;
import com.google.common.annotations.Beta;

/**
 * 封装各种生成唯一性ID算法的工具类.
 */
@Beta
public class IdGenerator {

	private static SecureRandom random = new SecureRandom();

	private static Sequence keyGenerator = new Sequence(31, 31);

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return RandomUtil.nextLong();
	}

	/**
	 * 基于URLSafeBase64编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase64(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeUtil.encodeBase64UrlSafe(randomBytes);
	}

	/**
	 * 基于当当开源的shard ID生成ID @see http://shardingjdbc.io/docs/02-guide/key-generator/
	 * @return
	 */
	public static String shardId() {
		return uuid2();
	}


	/**
	 * 返回String 类型ID
	 * @return
	 */
	public static String strShardId() {
		return uuid2().toString();
	}


	/**
	 * 生成随即密码
	 *
	 * @param length
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String randomNum(int length) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
        /*char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };*/

		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < length) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

//	public  static void main(String[] args) {
////		for (int i = 0; i < 1000; i++) {
////			System.out.println(randomNum(16));
////
//////			System.out.println(IdGenerator.strShardId());
////		}
//		System.out.println("args = [" + IdGenerator.strShardId() + "]");
//
//	}
}
