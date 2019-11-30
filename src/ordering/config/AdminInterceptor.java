package ordering.config;

import ordering.domain.Admin;
import ordering.domain.Customer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员端拦截器
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/30 21:23
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session中的admin对象
        HttpSession session=httpServletRequest.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        //判断session中是否有admin数据，有则返回true，继续执行
        if(admin!=null&&admin.getAdmin_account()!=null)
            return true;
        //不符合上述条件则转发到首页
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
