package Nomenclature.Option.Option;

import java.util.List;

public class Option {
    private int addressId;
    private int minQuantity;
    private int maxQuantity;
    private String name;
    private int parentId;
    private boolean isFree;
    private List<nomenclatureOptions> nomenclatureOptions;

    public int getAddressId() {
        return addressId;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public boolean isFree() {
        return isFree;
    }

    public List<Option.nomenclatureOptions> getNomenclatureOptions() {
        return nomenclatureOptions;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setNomenclatureOptions(List<Option.nomenclatureOptions> nomenclatureOptions) {
        this.nomenclatureOptions = nomenclatureOptions;
    }

    public static class nomenclatureOptions {
        private int nomenclatureId;
        private int minQuantity;
        private int maxQuantity;
        private int defaultQuantity;
        private boolean printIfDefaultQuantity;
        private boolean isConstant;

        public int getNomenclatureId() {
            return nomenclatureId;
        }

        public int getMinQuantity() {
            return minQuantity;
        }

        public int getMaxQuantity() {
            return maxQuantity;
        }

        public int getDefaultQuantity() {
            return defaultQuantity;
        }

        public boolean isPrintIfDefaultQuantity() {
            return printIfDefaultQuantity;
        }

        public boolean isConstant() {
            return isConstant;
        }

        public void setNomenclatureId(int nomenclatureId) {
            this.nomenclatureId = nomenclatureId;
        }

        public void setMinQuantity(int minQuantity) {
            this.minQuantity = minQuantity;
        }

        public void setMaxQuantity(int maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public void setDefaultQuantity(int defaultQuantity) {
            this.defaultQuantity = defaultQuantity;
        }

        public void setPrintIfDefaultQuantity(boolean printIfDefaultQuantity) {
            this.printIfDefaultQuantity = printIfDefaultQuantity;
        }

        public void setConstant(boolean constant) {
            isConstant = constant;
        }
    }
}
