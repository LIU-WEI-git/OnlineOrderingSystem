package ordering.config;

import ordering.domain.Admin;
import ordering.domain.Customer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 顾客端拦截器
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/25 9:35
 */
public class CustomerInterceptor implements HandlerInterceptor {

    private final static Log log = LogFactory.getLog(CustomerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session中的customer对象
        HttpSession session=httpServletRequest.getSession();
        Customer customer=(Customer)session.getAttribute("customer");
        //判断session中是否有customer数据，有则返回true，继续执行
        if(customer!=null&&customer.getCustomer_account()!=null)
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
