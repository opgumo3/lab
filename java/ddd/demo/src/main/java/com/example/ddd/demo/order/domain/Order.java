package order.domain;

import java.util.List;

public class Order {
    private OrderId orderId;
    private Orderer orderer;
    private List<OrderLine> orderLines;
    private ShippingInfo shippingInfo;
    private OrderState orderState;

    public Order createNewOrder() {
        return null;
    }

    public Order changeReceiver(Receiver receiver) {
        return null;
    }

    public Order changeShippingInfo(ShippingInfo shippingInfo) {
        return null;
    }

    public Order changeOrderLine(OrderLine orderLine) {
        return null;
    }

    public Order cancelOrder() {
        return null;
    }
}
