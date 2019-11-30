package ordering.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * WebMVC配置
 *
 * @version: 1.0
 * Created in 2019/11/17 17:05
 */
@Configuration  //定义为配置类
@EnableWebMvc   //启用Spring MVC
@ComponentScan("ordering.controller")   //启用组件扫描，扫描指定目录下的类
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 配置静态资源的处理
     *DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问），
     * DefaultServletHttpRequestHandler是帮助回归这个特性的
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 定义静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        super.addResourceHandlers(registry);
    }

    /**
     * 定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomerInterceptor()).
                addPathPatterns("/shoppingCart/deleteDish", "shoppingCart/minusDish",
                        "/account", "/account/**", "/order", "/order/**", "/myAddress", "/myAddress/**", "/logout");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin", "/admin/**").excludePathPatterns("/admin/alogin");
        super.addInterceptors(registry);
    }
}
