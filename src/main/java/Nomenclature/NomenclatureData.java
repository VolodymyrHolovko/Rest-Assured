package Nomenclature;

import org.json.JSONArray;
import org.json.simple.JSONObject;

public class NomenclatureData {
    public JSONObject type1SupportSelling() {
        JSONObject type = new JSONObject();
        type.put("article",null);
        type.put("establishmentId",1);
        type.put("article",null);
        type.put("kitchenName","kitchenName");
        type.put("shortName","shortName");
        type.put("publicName","publicName");
        type.put("barCode","barCode");
        type.put("recipe","recipe");
        type.put("description","description");
        type.put("weightInKilos",3);
        type.put("heatLoss",3);
        type.put("coldLoss",3);
        type.put("supportSelling",true);
        type.put("supportExtensioning",true);
        type.put("printOnCheck",true);
        type.put("printToKitchen",true);
        type.put("parentId",1);
        type.put("preparingTime",4456);
        type.put("rushPreparingTime",4456);
        type.put("maxExtensions",1);
        type.put("debitMethodId",1);
        type.put("nomenclatureTypeId",1);
        type.put("cookingPriorityId",1);
        type.put("saleMethodId",1);
        type.put("tasteGroupId",1);
        type.put("specialGroupId",1);
        type.put("supportedOrderTypes",1);
        type.put("dimensionId",1);
        type.put("tagId",1);
        JSONArray tax = new JSONArray();
        tax.put(1);
        type.put("taxesIds",tax);
        return type;
    }

    public JSONObject updateModel(int article){
        JSONObject object = type1SupportSelling();
        object.remove("establishmentId");
        object.replace("article",null,article);
        object.replace("kitchenName","kitchenName","kitchenName1");
        object.replace("shortName","shortName","shortName1");
        object.replace("publicName","publicName","publicName1");
        object.replace("barCode","barCode","barCode1");
        object.replace("recipe","recipe","recipe1");
        object.replace("description","description","description1");
        object.replace("weightInKilos",3,4);
        object.replace("heatLoss",3,4);
        object.replace("coldLoss",3,4);
        object.replace("supportSelling",true,false);
        object.replace("supportExtensioning",true,false);
        object.replace("printOnCheck",true,false);
        object.replace("printToKitchen",true,false);
        object.replace("preparingTime",4456,23);
        object.replace("rushPreparingTime",4456,23);
        object.replace("maxExtensions",1,2);
        object.replace("debitMethodId",1,2);
        object.replace("nomenclatureTypeId",1,2);
        object.replace("cookingPriorityId",1,2);
        object.replace("saleMethodId",1,2);
        object.replace("tasteGroupId",1,2);
        object.replace("specialGroupId",1,2);
        object.replace("supportedOrderTypes",1,2);
        object.replace("dimensionId",1,2);
        return object;
    }
    public JSONObject createNomenclatureThirdType(){
        JSONObject object = type1SupportSelling();
        object.replace("nomenclatureTypeId",1,3);
        return object;
    }
    public JSONObject createNomenclatureFourthType(){
        JSONObject object = type1SupportSelling();
        object.replace("nomenclatureTypeId",1,4);
        return object;
    }
}
