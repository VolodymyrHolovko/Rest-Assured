package Nomenclature.Option.Option;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Option {
    private int id;
    private int addressId;
    private int minQuantity;
    private int maxQuantity;
    private String name;
    private int parentId;
    @JsonProperty
    private boolean isFree;
    @JsonProperty
    private boolean isActive;
    private List<NomenclatureOptions> nomenclatureOptions;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isFree(boolean b) {
        return isFree;
    }

    public boolean isFree() {
        return isFree;
    }

    public List<NomenclatureOptions> getNomenclatureOptions() {
        return nomenclatureOptions;
    }

    public void setNomenclatureOptions(List<NomenclatureOptions> nomenclatureOptions) {
        this.nomenclatureOptions = nomenclatureOptions;
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



    public static class NomenclatureOptions {
        private String nomenclatureId;
        private int minQuantity;
        private int maxQuantity;
        private int defaultQuantity;
        @JsonProperty
        private boolean printIfDefaultQuantity;
        @JsonProperty
        private boolean isConstant;

        public String getNomenclatureId() {
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

        public boolean isPrintIfDefaultQuantity(boolean b) {
            return printIfDefaultQuantity;
        }

        public boolean isConstant(boolean b) {
            return isConstant;
        }

        public void setNomenclatureId(String nomenclatureId) {
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
