package Orders.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemData {
    public OrderItem addOrderItem(int nomenclatureId, String initializationId, int extensionId, int optionId ){
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(1);

        List<OrderItem.items> item =  new ArrayList<>();
        OrderItem.items items = new OrderItem.items();
        items.setNomenclatureId(nomenclatureId);
        items.setAmount(1);
        items.setSize(10);
        items.setInitializationId(initializationId);
        items.setDescription("Додайту цю страву до замовлення");

        List<OrderItem.items.extensions> extensions = new ArrayList<>();
        OrderItem.items.extensions extension = new OrderItem.items.extensions();
        extension.setExtensionId(extensionId);
        extension.setOptionId(optionId);
        extension.setAmount(1);
        extensions.add(extension);
        items.setExtensions(extensions);
        item.add(items);
        orderItem.setItems(item);
        return orderItem;
    }
}
