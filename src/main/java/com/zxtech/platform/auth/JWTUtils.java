package com.zxtech.platform.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.types.Expiration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.platform.utils.MD5Util;
import com.zxtech.platform.utils.cache.CacheTemplate;
import com.zxtech.platform.vo.UserBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWTUtils {

	public final static String SECRET_KEY_STR = "2d4052a350f95360cba27cfc7c7cd1a1";
	private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	private final static Key SECRET_KEY = generateKey();
	private final static long EXPIRE_MILLIS = 1000 * 60 * 60 * 20L; // 20 hours
	private final static String SUBJECT_PREFIX = "user/";
	public final static String CLAIMS_USER_KEY = "claims_user";

	private static Key generateKey() {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY_STR);
		return new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
	}

	/**
	 * 生成token
	 * 
	 * @param user
	 * @return
	 */
	public static String token(UserBean user) {
		if (user != null) {
			String mapKey = MD5Util.get32BitMd5EncString(SUBJECT_PREFIX + user.getUserId());
			String token = Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MILLIS))
					.claim(CLAIMS_USER_KEY, mapKey)
					.signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
					.compact();
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				conn.set(token.getBytes(), new Gson().toJson(user).getBytes(StandardCharsets.UTF_8), Expiration.milliseconds(EXPIRE_MILLIS), null);
				return null;
			});
			return token;
		}
		return null;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param request
	 * @param user
	 * @return
	 * @author: syp637
	 */
	public static void updateUserBean(HttpServletRequest request, UserBean user) {
		if (user != null) {
			String token = request.getParameter(SysConstants.TOKEN);
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				conn.set(token.getBytes(), new Gson().toJson(user).getBytes(StandardCharsets.UTF_8), Expiration.milliseconds(EXPIRE_MILLIS), null);
				return null;
			});
		}
	}
	
	/**
	 * 删除用户信息
	 * 
	 * @param request
	 * @return
	 * @author: syp637
	 */
	public static void delUserInfo(HttpServletRequest request) {
		CacheTemplate.REDIS.execute((RedisConnection conn) -> {
			String token = request.getParameter(SysConstants.TOKEN);
			conn.del(token.getBytes());
			return null;
		});
	}

	/**
	 * 验证JWT
	 * 
	 * @param token
	 * @return
	 */
	public static CheckResult validateJWT(String token) {
		CheckResult checkResult = new CheckResult();
		Claims claims = null;
		try {
			claims = parseJWT(token);
			checkResult.setSuccess(true);
			checkResult.setClaims(claims);
		} catch (ExpiredJwtException e) {
			checkResult.setErrCode(JWTErrorCode.JWT_ERRCODE_EXPIRE);
			checkResult.setSuccess(false);
		} catch (SignatureException e) {
			checkResult.setErrCode(JWTErrorCode.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		} catch (Exception e) {
			checkResult.setErrCode(JWTErrorCode.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		}
		return checkResult;
	}

	/**
	 * 解析token字符串
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String token) throws Exception {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	protected static UserBean getUser(String token) {
		return CacheTemplate.REDIS.execute((RedisConnection conn) -> {
			byte[] userBytes = conn.get(token.getBytes());
			UserBean user = null;
			if (userBytes != null) {
				user = new Gson().fromJson(new String(userBytes, StandardCharsets.UTF_8), new TypeToken<UserBean>() {}.getType());
			}
			return user;
		});
	}
}
