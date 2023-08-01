package com.xudq.api.service.impl;


import com.xudq.api.service.OctoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@ConditionalOnProperty(value = {"octo.key","ocot.secret"},havingValue = "octo.key")
@Service
public class OctoServiceImpl implements OctoService {

	@Value("${octo.key}")
	private String OCTO_KEY;
	@Value("${octo.secret}")
	private String OCTO_SECRET;

	@Override
	public String generateJwtToken(){
		Date beginDate = new Date();
		Timestamp endDate = new Timestamp(System.currentTimeMillis() + 3*60*1000);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("typ", "JWT");
		String OctoPrivilegeSecretBase64 = Base64.getEncoder().encodeToString(OCTO_SECRET.getBytes());
		return Jwts.builder().setHeader(map).setIssuer(OCTO_KEY).setIssuedAt(beginDate).setExpiration(endDate).signWith(SignatureAlgorithm.HS256, OctoPrivilegeSecretBase64).compact();
	}
}
