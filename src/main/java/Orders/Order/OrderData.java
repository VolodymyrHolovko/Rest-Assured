package Orders.Order;


import java.util.ArrayList;
import java.util.List;

public class OrderData {
    public Order createNewOrder(int tableId, int nomenclatureId, int departmentId,
                                int optionId, long menuVersion,String initializationId,
                                int extensionId ){
        Order order = new Order();
        order.setWaiterId("52d95859-a6cf-4d6d-b507-9d4720947075");
        order.setTableId(tableId);
        order.setAddressId(2);
        order.setDepartmentId(departmentId);
        order.setMenuVersion(menuVersion);
        order.setInitializationId(initializationId);
        order.setDescription("Цей прекрасний ордер повинен бути виконаним");
        order.setOrderTypeId(1);
        order.setTotalPrice(21);
        order.setToBePreparedAtTime("2018-05-30T12:18:25");

        List<Order.orderItems> orderItems = new ArrayList<>();
        Order.orderItems orderItem = new Order.orderItems ();
        orderItem.setNomenclatureId(nomenclatureId);
        orderItem.setAmount(1);
        orderItem.setSize(10);
        orderItem.setInitializationId(initializationId);
        orderItem.setDescription("Цей прекрасний айтем повинен бути виконаним");

        List<Order.orderItems.extensions> extensions = new ArrayList<>();
        Order.orderItems.extensions extension= new Order.orderItems.extensions();
        extension.setExtensionId(extensionId);
        extension.setOptionId(optionId);
        extension.setAmount(1);
        extensions.add(extension);
        orderItem.setExtensions(extensions);
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        return order;
    }
}
