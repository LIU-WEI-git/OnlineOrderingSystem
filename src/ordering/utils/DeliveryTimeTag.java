package ordering.utils;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义标签类DeliveryTimeTag，用于计算开始的配送时间
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/24 21:06
 */
public class DeliveryTimeTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        //获取当前系统时间并加上30分钟
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        Date deliveryTime = calendar.getTime();
        //格式化时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(deliveryTime);
        //输出
        getJspContext().getOut().println(formattedDate);
    }
}
