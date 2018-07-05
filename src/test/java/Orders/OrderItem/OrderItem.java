package Orders.OrderItem;

import java.util.List;

public class OrderItem {
    private  int price;
    List<items> items;

    public int getPrice() {
        return price;
    }

    public List<OrderItem.items> getItems() {
        return items;
    }

    public void setItems(List<OrderItem.items> items) {
        this.items = items;

    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static class items {
        private int nomenclatureId;
        private int amount;
        private int size;
        private String initializationId;
        private String description;
        List<extensions> extensions;

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

        public List<OrderItem.items.extensions> getExtensions() {
            return extensions;
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

        public void setExtensions(List<OrderItem.items.extensions> extensions) {
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
