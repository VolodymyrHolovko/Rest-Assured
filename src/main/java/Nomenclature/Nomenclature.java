package Nomenclature;

import java.util.List;

public class Nomenclature {
    private int establishmentId;
    private String article;
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
    private NomenclaturePortion portion;

    public NomenclaturePortion getPortion() {
        return portion;
    }

    public void setPortion(NomenclaturePortion portion) {
        this.portion = portion;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getHeatLoss() {
        return heatLoss;
    }

    public String getId() {
        return id;
    }

    public String  getArticle() {
        return article;
    }

    public boolean isActive() {
        return isActive;
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

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public boolean isPrintToKitchen() {
        return printToKitchen;
    }

    public int getParentId() {
        return parentId;
    }

    public int getTagId() {
        return tagId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
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


}
