package Orders.Order;

import java.util.List;

public class Order {
    private int id;
    private String waiterId;
    private int tableId;
    private int addressId;
    private int departmentId;
    private long menuVersion;
    private String initializationId;
    private String description;
    private int orderTypeId;
    private int totalPrice;
    private String toBePreparedAtTime;
    private List<orderItems> orderItems;
    private String cashierId;
    private int orderStatusId;

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getToBePreparedAtTime() {
        return toBePreparedAtTime;
    }

    public List<orderItems> getOrderItems() {
        return orderItems;
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

    public void setToBePreparedAtTime(String toBePreparedAtTime) {
        this.toBePreparedAtTime = toBePreparedAtTime;
    }

    public void setOrderItems(List<orderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public static class orderItems {
        private int statusId;
        private int id;
        private int nomenclatureId;
        private int amount;
        private int size;
        private String initializationId;
        private String description;
        private List<extensions> extensions;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatusId() {
            return statusId;
        }

        public void setStatusId(int statusId) {
            this.statusId = statusId;
        }

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

        public List<Order.orderItems.extensions> getExtensions() {
            return extensions;
        }

        public void setExtensions(List<Order.orderItems.extensions> extensions) {
            this.extensions = extensions;
        }

        public static class extensions {
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
}
