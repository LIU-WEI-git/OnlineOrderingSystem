package ordering.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * 数据库访问配置类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/17 17:05
 */
@Configuration  //定义为配置类
@ComponentScan(basePackages={"ordering"},excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class )})
public class RootConfig {

    // 读取数据库配置文件
    private static ResourceBundle resource = ResourceBundle.getBundle("ordering.config.config");

    // 获取数据库配置
    private static String DRIVER = resource.getString("DRIVER");
    private static String URL = resource.getString("URL");
    private static String DBNAME = resource.getString("DBNAME");
    private static String DBCONFIG = resource.getString("DBCONFIG");
    private static String USER = resource.getString("USER");
    private static String PASSWORD = resource.getString("PASSWORD");

    //获取AES加密的密钥
    public static String SECRET_KEY=resource.getString("SECRET_KEY");

    //获取配置的折扣信息
    public static float TARGET_PRICE = Float.valueOf(resource.getString("TARGET_PRICE"));
    public static float DISCOUNT = Float.valueOf(resource.getString("DISCOUNT"));

    /**
     * 数据源设置，采用MySQL数据库，此处运用了数据源连接池BasicDataSource
     * @return
     */
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER); //jdbc驱动
        //数据库url
        dataSource.setUrl(URL + DBNAME + DBCONFIG);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        return dataSource;
    }

    /**
     * jdbc模板设置，采用spring默认的JdbcTemplate
     *
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}