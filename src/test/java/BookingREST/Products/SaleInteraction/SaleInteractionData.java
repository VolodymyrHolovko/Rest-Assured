package BookingREST.Products.SaleInteraction;

public class SaleInteractionData {
    public SaleInteraction addNewSaleInteraction(int business_id, int product_id, String currency, int cost) {
        SaleInteraction addSaleInter = new SaleInteraction();
        addSaleInter.setBusiness_id(business_id);
        addSaleInter.setProduct_id(product_id);
        addSaleInter.setCurrency(currency);
        addSaleInter.setCost(cost);
        return addSaleInter;
    }
    public SaleInteraction updateSaleInteraction(String currency2, int cost2) {
        SaleInteraction updateSaleInter = new SaleInteraction();
        updateSaleInter.setCurrency(currency2);
        updateSaleInter.setCost(cost2);
        return updateSaleInter;
    }

}
