package ordering.utils;

import ordering.config.RootConfig;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 自定义标签类OrderPriceTag，用于在考虑了折扣后计算订单的最终价格
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/24 19:43
 */
public class OrderPriceTag extends SimpleTagSupport {

    private float totalPrice;

    @Override
    public void doTag() throws IOException {
        if (totalPrice > RootConfig.TARGET_PRICE) totalPrice -= RootConfig.DISCOUNT;
        getJspContext().getOut().println(totalPrice);
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
