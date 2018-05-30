package Orders.Order;

public class OrderStatusesData {
    public OrderStatuses changeOrderStatus(String status){
        OrderStatuses orderStatuses = new OrderStatuses();
        orderStatuses.setOrderStatus(status);
        return  orderStatuses;
    }
}
