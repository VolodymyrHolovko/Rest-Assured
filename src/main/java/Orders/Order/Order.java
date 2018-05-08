package Orders.Order;

import java.util.List;

public class Order {
    private String waiterId;
    private int tableId;
    private int addressId;
    private int departmentId;
    private long menuVersion;
    private String initializationId;
    private String description;
    private int orderTypeId;
    private int totalPrice;
    private int toBePreparedAtTime;
    private List<orderItems> orderItemsList;

    public String getWaiterId() {
        return waiterId;
    }

    public int getTableId() {
        return tableId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public long getMenuVersion() {
        return menuVersion;
    }

    public String getInitializationId() {
        return initializationId;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getToBePreparedAtTime() {
        return toBePreparedAtTime;
    }

    public List<orderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setMenuVersion(long menuVersion) {
        this.menuVersion = menuVersion;
    }

    public void setInitializationId(String initializationId) {
        this.initializationId = initializationId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setToBePreparedAtTime(int toBePreparedAtTime) {
        this.toBePreparedAtTime = toBePreparedAtTime;
    }

    public void setOrderItemsList(List<orderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    private class orderItems{
        private int nomenclatureId;
        private int amount;
        private int size;
        private String initializationId;
        private String description;
        private List<extensions> orderItemsList;

        public int getNomenclatureId() {
            return nomenclatureId;
        }

        public int getAmount() {
            return amount;
        }

        public int getSize() {
            return size;
        }

        public String getInitializationId() {
            return initializationId;
        }

        public String getDescription() {
            return description;
        }

        public List<extensions> getOrderItemsList() {
            return orderItemsList;
        }

        public void setNomenclatureId(int nomenclatureId) {
            this.nomenclatureId = nomenclatureId;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setInitializationId(String initializationId) {
            this.initializationId = initializationId;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setOrderItemsList(List<extensions> orderItemsList) {
            this.orderItemsList = orderItemsList;
        }
    }

    private class extensions{
        private int extensionId;
        private int optionId;
        private int amount;

        public int getExtensionId() {
            return extensionId;
        }

        public int getOptionId() {
            return optionId;
        }

        public int getAmount() {
            return amount;
        }

        public void setExtensionId(int extensionId) {
            this.extensionId = extensionId;
        }

        public void setOptionId(int optionId) {
            this.optionId = optionId;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

}
