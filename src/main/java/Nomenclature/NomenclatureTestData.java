package Nomenclature;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;


public class NomenclatureTestData {
    public Nomenclature type1SupportSelling() {
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setAddressId(2);
        nomenclature.setArticle(null);
        nomenclature.setEstablishmentId(1);
        nomenclature.setActive(true);
        nomenclature.setKitchenName("kitchenName");
        nomenclature.setShortName("shortName");
        nomenclature.setPublicName("publicName");
        nomenclature.setBarCode("barCode");
        nomenclature.setRecipe("recipe");
        nomenclature.setDescription("description");
        nomenclature.setWeightInKilos(3);
        nomenclature.setHeatLoss(3);
        nomenclature.setColdLoss(3);
        nomenclature.setSupportSelling(true);
        nomenclature.setSupportExtensioning(true);
        nomenclature.setPrintOnCheck(true);
        nomenclature.setPrintToKitchen(true);
        nomenclature.setParentId(1);
        nomenclature.setPreparingTime(4456);
        nomenclature.setRushPreparingTime(4456);
        nomenclature.setMaxExtensions(1);
        nomenclature.setDebitMethodId(1);
        nomenclature.setNomenclatureTypeId(1);
        nomenclature.setCookingPriorityId(1);
        nomenclature.setSaleMethodId(1);
        nomenclature.setTasteGroupId(1);
        nomenclature.setSpecialGroupId(1);
        nomenclature.setSupportedOrderTypes(1);
        nomenclature.setDimensionId(1);
        nomenclature.setTagId(1);
        ArrayList<Integer> tax = new ArrayList<>();
//        tax.add(1);
        nomenclature.setTaxesIds(tax);
        return nomenclature;
    }

    public Nomenclature updateModel(String article){
        Nomenclature nomenclatureUpdate = type1SupportSelling();

        nomenclatureUpdate.setArticle(article);
        nomenclatureUpdate.setEstablishmentId(1);
        nomenclatureUpdate.setKitchenName("kitchenName1");
        nomenclatureUpdate.setShortName("shortName1");
        nomenclatureUpdate.setPublicName("publicName1");
        nomenclatureUpdate.setBarCode("barCode1");
        nomenclatureUpdate.setRecipe("recipe1");
        nomenclatureUpdate.setDescription("description1");
        nomenclatureUpdate.setWeightInKilos(4);
        nomenclatureUpdate.setHeatLoss(4);
        nomenclatureUpdate.setColdLoss(4);
        nomenclatureUpdate.setSupportSelling(false);
        nomenclatureUpdate.setSupportExtensioning(false);
        nomenclatureUpdate.setPrintOnCheck(false);
        nomenclatureUpdate.setPrintToKitchen(false);
        nomenclatureUpdate.setPreparingTime(23);
        nomenclatureUpdate.setRushPreparingTime(23);
        nomenclatureUpdate.setMaxExtensions(2);
        nomenclatureUpdate.setDebitMethodId(2);
        nomenclatureUpdate.setNomenclatureTypeId(2);
        nomenclatureUpdate.setCookingPriorityId(2);
        nomenclatureUpdate.setSaleMethodId(2);
        nomenclatureUpdate.setTasteGroupId(2);
        nomenclatureUpdate.setSpecialGroupId(2);
        nomenclatureUpdate.setSupportedOrderTypes(2);
        nomenclatureUpdate.setDimensionId(2);
        return nomenclatureUpdate;
    }

    public Nomenclature createNomenclatureThirdType(){
        Nomenclature object = type1SupportSelling();
        object.setNomenclatureTypeId(3);
        return object;
    }

    public Nomenclature createNomenclatureFourthType(){
        Nomenclature object = type1SupportSelling();
        object.setNomenclatureTypeId(4);
        return object;
    }
}
