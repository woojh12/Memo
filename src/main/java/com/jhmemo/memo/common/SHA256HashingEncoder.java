package com.jhmemo.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 의존성 우선순위 부여
@Primary
//Spring bean에 등록하기 위한 annotation
@Component("sha256Hashing")
public class SHA256HashingEncoder implements HashingEncoder {
	public String encode(String message)
	{
		String result = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("sha256");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			// byte 배열의 값을 16진수 문자열 형태로 변환
			for(int i = 0; i < digest.length; i++)
			{
				// 0101011
				// 1101111
				// 0101011 			--> &연산
				result += Integer.toHexString(digest[i] & 0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
		return result;
	}
}
