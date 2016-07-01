package pms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect(request.getContextPath() + "/auth/login.do");
      return false;
    }
    
    return true;
  }

}




