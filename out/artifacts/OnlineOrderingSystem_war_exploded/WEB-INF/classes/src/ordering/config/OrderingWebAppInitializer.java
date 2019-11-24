package ordering.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *用于配置Servlet容器的类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/17 17:03
 */
public class OrderingWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //用RootConfig类来配置ContextLoaderListener创建的应用上下文中的bean
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //加载应用上下文时，使用定义在WebConfig类中的bean来定义DispatcherServlet应用上下文中的bean
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        //将“/”路径映射到DispatcherServlet上，它将是应用的默认Servlet
        return new String[]{"/"};
    }
}
