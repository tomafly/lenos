package com.len.core.filter;

import com.alibaba.fastjson.JSONArray;
import com.len.entity.SysMenu;
import com.len.service.MenuService;
import com.len.service.SysUserService;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhuxiaomeng
 * @date 2017/12/13.
 * @email 154040976@qq.com
 * 自定义拦截器 暂时不用
 */
public class CustomAdvicFilter extends FormAuthenticationFilter {

  @Autowired
  private SysUserService userService;

  @Autowired
  private MenuService menuService;


  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      return true;
  }
}
