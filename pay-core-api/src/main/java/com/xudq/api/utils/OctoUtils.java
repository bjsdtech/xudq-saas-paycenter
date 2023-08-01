package com.xudq.api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class OctoUtils {

	private static String OctoPrivilegeKey;
	private static String OctoPrivilegeSecretBase64;
	public static void setOctoPrivilegeKey(String octoPrivilegeKey) {
		OctoPrivilegeKey = octoPrivilegeKey;
	}

	public static void setOctoPrivilegeSecretBase64(String octoPrivilegeSecretBase64) {
		OctoPrivilegeSecretBase64 = octoPrivilegeSecretBase64;
	}

	public static String generateJwtToken(){
		Date beginDate = new Date();
		Timestamp endDate = new Timestamp(System.currentTimeMillis() + 10*60*1000);
		HashMap<String,Object> map = new HashMap<>();
		map.put("typ", "JWT");
		return Jwts.builder().setHeader(map).setIssuer(OctoPrivilegeKey).setIssuedAt(beginDate).setExpiration(endDate).signWith(SignatureAlgorithm.HS256, OctoPrivilegeSecretBase64).compact();
	}
}
