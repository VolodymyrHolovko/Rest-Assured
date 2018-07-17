package BookingREST.Movement;

public class MovementData {
    public Movement addNewMovement(int business_id, int sender_warehouse_id, int receiver_warehouse_id, int sender_responsible_id, int receiver_responsible_id, int sender_stock_id, int product_id, int count, String comment) {
        Movement addMovement = new Movement();
        addMovement.setBusiness_id(business_id);
        addMovement.setSender_warehouse_id(sender_warehouse_id);
        addMovement.setReceiver_warehouse_id(receiver_warehouse_id);
        addMovement.setSender_responsible_id(sender_responsible_id);
        addMovement.setReceiver_responsible_id(receiver_responsible_id);
        addMovement.setSender_stock_id(sender_stock_id);
        addMovement.setProduct_id(product_id);
        addMovement.setCount(count);
        addMovement.setComment(comment);
        return addMovement;
    }
    public Movement updateMovements(String comment2) {
        Movement updateMovement = new Movement();
        updateMovement.setComment(comment2);
        return updateMovement;
    }
}
