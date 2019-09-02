package com.zxtech.platform.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zxtech.ess.app.util.SysConstants;
import com.zxtech.platform.vo.UserBean;

public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setCharacterEncoding("utf-8");

		String token = request.getParameter(SysConstants.TOKEN);
		if (StringUtils.isBlank(token)) {
			responseMessage(httpServletResponse, httpServletResponse.getWriter(), ResponseData.jwtExpireError());
			return;
		}

		// 验证JWT的签名，返回CheckResult对象
		CheckResult checkResult = JWTUtils.validateJWT(token);
		if (checkResult.isSuccess()) {
			UserBean user = JWTUtils.getUser(token);
			if (user == null) {
				responseMessage(httpServletResponse, httpServletResponse.getWriter(), ResponseData.otherDevicesLogin());
				return;
			} else {
				request.setAttribute(JWTUtils.CLAIMS_USER_KEY, user);
				chain.doFilter(request, response);
			}
		} else {
			switch (checkResult.getErrCode()) {
			// 签名过期，返回过期提示码
			case JWT_ERRCODE_EXPIRE:
				responseMessage(httpServletResponse, httpServletResponse.getWriter(), ResponseData.jwtExpireError());
				break;
			// 签名验证不通过
			case JWT_ERRCODE_FAIL:
				responseMessage(httpServletResponse, httpServletResponse.getWriter(), ResponseData.forbidden());
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 请求不通过，返回错误信息给客户端
	 * 
	 * @param response
	 * @param out
	 * @param responseData
	 */
	private void responseMessage(HttpServletResponse httpServletResponse, PrintWriter out, ResponseData responseData) {
		httpServletResponse.setContentType("text/plain;charset=utf-8");
		httpServletResponse.setStatus(responseData.getCode());
		out.print(responseData.getMessage());
		out.flush();
		out.close();
	}
}
