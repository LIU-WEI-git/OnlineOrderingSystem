package ordering.utils;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 自定义标签类DiscountTag，用于显示折扣
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/24 20:36
 */
public class DiscountTag extends SimpleTagSupport {

    private float totalPrice;
    private static ResourceBundle resource = ResourceBundle.getBundle("ordering.config.discount");
    private static float TARGET_PRICE = Float.valueOf(resource.getString("TARGET_PRICE"));
    private static float DISCOUNT = Float.valueOf(resource.getString("DISCOUNT"));

    @Override
    public void doTag() throws IOException {
        //如果标签没有给totalPrice赋值，则默认输出折扣
        if (totalPrice == 0.0) getJspContext().getOut().println(DISCOUNT);
        else if (totalPrice > TARGET_PRICE)
            getJspContext().getOut().println(DISCOUNT);
        else getJspContext().getOut().println("0.0");
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
