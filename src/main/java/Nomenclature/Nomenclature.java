package Nomenclature;

import java.util.List;

public class Nomenclature {
    Nomenclature data;
    private int establishmentId;
    private int article;
    private String kitchenName;
    private String shortName;
    private String publicName;
    private String barCode;
    private String recipe;
    private String description;
    private int weightInKilos;
    private int coldLoss;
    private int heatLoss;
    private boolean supportSelling;
    private boolean supportExtensioning;
    private boolean printOnCheck;
    private boolean printToKitchen;
    private int parentId;
    private String id;
    private boolean isActive;
    private int preparingTime;
    private int rushPreparingTime;
    private int maxExtensions;
    private int debitMethodId;
    private int nomenclatureTypeId;
    private int cookingPriorityId;
    private int saleMethodId;
    private int tasteGroupId;
    private int specialGroupId;
    private int supportedOrderTypes;
    private int dimensionId;
    private int tagId;
    private boolean isSuccess;
    private String descriptions;
    private List<Integer> taxesIds;

    public Nomenclature(Nomenclature data, List<Integer> taxesIds, String descriptions, String id, int establishmentId, int article, String kitchenName, String shortName, String publicName, String barCode, String recipe, String description, int weightInKilos, int coldLoss, boolean supportSelling, boolean supportExtensioning, boolean printOnCheck, boolean printToKitchen, int parentId, int preparingTime, int rushPreparingTime, int maxExtensions, int debitMethodId, int nomenclatureTypeId, int cookingPriorityId, int saleMethodId, int tasteGroupId, int specialGroupId, int supportedOrderTypes, int dimensionId, int tagId) {
        this.taxesIds = taxesIds;
        this.heatLoss = heatLoss;
        this.isSuccess = isSuccess;
        this.data = data;
        this.taxesIds = taxesIds;
        this.establishmentId = establishmentId;
        this.article = article;
        this.kitchenName = kitchenName;
        this.shortName = shortName;
        this.publicName = publicName;
        this.barCode = barCode;
        this.recipe = recipe;
        this.description = description;
        this.weightInKilos = weightInKilos;
        this.coldLoss = coldLoss;
        this.supportSelling = supportSelling;
        this.supportExtensioning = supportExtensioning;
        this.printOnCheck = printOnCheck;
        this.printToKitchen = printToKitchen;
        this.parentId = parentId;
        this.isActive = isActive;
        this.preparingTime = preparingTime;
        this.rushPreparingTime = rushPreparingTime;
        this.maxExtensions = maxExtensions;
        this.debitMethodId = debitMethodId;
        this.nomenclatureTypeId = nomenclatureTypeId;
        this.cookingPriorityId = cookingPriorityId;
        this.saleMethodId = saleMethodId;
        this.tasteGroupId = tasteGroupId;
        this.specialGroupId = specialGroupId;
        this.supportedOrderTypes = supportedOrderTypes;
        this.dimensionId = dimensionId;
        this.tagId = tagId;
        this.descriptions = descriptions;

    }

    public Nomenclature getData() {
        return data;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setData2(Nomenclature data2) {
        this.taxesIds = taxesIds;
    }

    public String getDescriptions() {

        return descriptions;
    }

    public int getHeatLoss() {
        return heatLoss;
    }

    public String getId() {
        return id;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public int getArticle() {
        return article;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getDescription() {
        return description;
    }

    public int getWeightInKilos() {
        return weightInKilos;
    }

    public int getColdLoss() {
        return coldLoss;
    }

    public boolean isSupportSelling() {
        return supportSelling;
    }

    public boolean isSupportExtensioning() {
        return supportExtensioning;
    }

    public boolean isPrintOnCheck() {
        return printOnCheck;
    }

    public boolean isPrintToKitchen() {
        return printToKitchen;
    }

    public int getParentId() {
        return parentId;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public int getRushPreparingTime() {
        return rushPreparingTime;
    }

    public int getMaxExtensions() {
        return maxExtensions;
    }

    public int getDebitMethodId() {
        return debitMethodId;
    }

    public int getNomenclatureTypeId() {
        return nomenclatureTypeId;
    }

    public int getCookingPriorityId() {
        return cookingPriorityId;
    }

    public int getSaleMethodId() {
        return saleMethodId;
    }

    public int getTasteGroupId() {
        return tasteGroupId;
    }

    public int getSpecialGroupId() {
        return specialGroupId;
    }

    public int getSupportedOrderTypes() {
        return supportedOrderTypes;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public int getTagId() {
        return tagId;
    }


    public void setData(Nomenclature data) {
        this.data = data;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public void setArticle(int article) {
        this.article = article;
    }



    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeightInKilos(int weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setColdLoss(int coldLoss) {
        this.coldLoss = coldLoss;
    }

    public void setSupportSelling(boolean supportSelling) {
        this.supportSelling = supportSelling;
    }

    public void setSupportExtensioning(boolean supportExtensioning) {
        this.supportExtensioning = supportExtensioning;
    }

    public void setHeatLoss(int heatLoss) {
        this.heatLoss = heatLoss;
    }

    public void setPrintOnCheck(boolean printOnCheck) {
        this.printOnCheck = printOnCheck;
    }

    public void setPrintToKitchen(boolean printToKitchen) {
        this.printToKitchen = printToKitchen;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
    }

    public void setRushPreparingTime(int rushPreparingTime) {
        this.rushPreparingTime = rushPreparingTime;
    }

    public void setMaxExtensions(int maxExtensions) {
        this.maxExtensions = maxExtensions;
    }

    public void setDebitMethodId(int debitMethodId) {
        this.debitMethodId = debitMethodId;
    }

    public void setNomenclatureTypeId(int nomenclatureTypeId) {
        this.nomenclatureTypeId = nomenclatureTypeId;
    }

    public void setCookingPriorityId(int cookingPriorityId) {
        this.cookingPriorityId = cookingPriorityId;
    }

    public void setSaleMethodId(int saleMethodId) {
        this.saleMethodId = saleMethodId;
    }

    public void setTasteGroupId(int tasteGroupId) {
        this.tasteGroupId = tasteGroupId;
    }

    public void setSpecialGroupId(int specialGroupId) {
        this.specialGroupId = specialGroupId;
    }

    public void setTaxesIds(List<Integer> taxesIds) {
        this.taxesIds = taxesIds;
    }

    public List<Integer> getTaxesIds() {
        return taxesIds;
    }

    public void setSupportedOrderTypes(int supportedOrderTypes) {
        this.supportedOrderTypes = supportedOrderTypes;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "NomenclatureData.Nomenclature{" + "data=" + data + ", establishmentId=" + establishmentId + ", article=" + article + ", kitchenName='" + kitchenName + '\'' + ", shortName='" + shortName + '\'' + ", publicName='" + publicName + '\'' + ", barCode='" + barCode + '\'' + ", recipe='" + recipe + '\'' + ", description='" + description + '\'' + ", weightInKilos=" + weightInKilos + ", coldLoss=" + coldLoss + ", heatLoss=" + heatLoss + ", supportSelling=" + supportSelling + ", supportExtensioning=" + supportExtensioning + ", printOnCheck=" + printOnCheck + ", printToKitchen=" + printToKitchen + ", parentId=" + parentId + ", id='" + id + '\'' + ", isActive=" + isActive + ", preparingTime=" + preparingTime + ", rushPreparingTime=" + rushPreparingTime + ", maxExtensions=" + maxExtensions + ", debitMethodId=" + debitMethodId + ", nomenclatureTypeId=" + nomenclatureTypeId + ", cookingPriorityId=" + cookingPriorityId + ", saleMethodId=" + saleMethodId + ", tasteGroupId=" + tasteGroupId + ", specialGroupId=" + specialGroupId + ", supportedOrderTypes=" + supportedOrderTypes + ", dimensionId=" + dimensionId + ", tagId=" + tagId + ", isSuccess=" + isSuccess + ", descriptions='" + descriptions + '\'' + ", taxesIds=" + taxesIds + '}';
    }
}
