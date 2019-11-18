package ordering.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/17 17:05
 */
@Configuration  //定义为配置类
@ComponentScan(basePackages={"ordering"},excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class )})

public class RootConfig {

    /**
     * 数据源设置，采用MySQL数据库，此处运用了数据源连接池BasicDataSource
     * @return
     */
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); //jdbc驱动
        //数据库url
        dataSource.setUrl("jdbc:mysql://localhost:3306/online_ordering_system?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
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
