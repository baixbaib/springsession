package com.bai.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test(@RequestParam(name = "attr", required = false) String attr, HttpServletRequest request,
			HttpSession session) {

		Object sessionAttr = session.getAttribute("attr");

		if (sessionAttr == null) {
			System.out.println("attr does not exsit in session，set attr as " + attr);
			session.setAttribute("attr", attr);
		} else {
			System.out.println("exsit attr，and it is : " + sessionAttr.toString());
			session.setAttribute("attr", attr);
		}

		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0) {
			System.out.println("this domain has no cookie currently");
			return "this domain has no cookie currently";
		}

		String content = "";
		if ((cookies != null) && (cookies.length > 0)) {
			for (Cookie cookie : cookies) {
				String ck = cookie.getName() + " : " + cookie.getValue() + ",";
				content += ck;
				System.out.println(ck);
			}
		}

		return "attr is :" + sessionAttr + " and cookies is : " + content;
	}
}