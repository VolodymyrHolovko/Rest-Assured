package Orders.Order;

public class OrderItemStatusData {

    public OrderItemStatus changeStatus(String status) {
        OrderItemStatus orderItemStatus = new OrderItemStatus();
        orderItemStatus.setStatus(status);
        return orderItemStatus;
    }
}



