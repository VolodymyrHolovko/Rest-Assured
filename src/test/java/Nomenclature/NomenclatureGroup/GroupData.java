package Nomenclature.NomenclatureGroup;

public class GroupData {
   public GroupResponse createGroup(){
       GroupResponse groupResponse = new GroupResponse();
       groupResponse.setParentId(1);
       groupResponse.setDefaultDimensionId(1);
       groupResponse.setDefaultDebitingMethodId(1);
       groupResponse.setDefaultSellingMethodId(1);
       groupResponse.setName("TestGroup");
       groupResponse.setDescription("My tests");
       groupResponse.setColorHex("#443322");
       groupResponse.setIconPath("Images/201710/file636449551149284980.png");

       return  groupResponse;
   }
    public GroupResponse updateGroup(){
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setParentId(1);
        groupResponse.setDefaultDimensionId(2);
        groupResponse.setDefaultDebitingMethodId(2);
        groupResponse.setDefaultSellingMethodId(2);
        groupResponse.setName("TestGroup2");
        groupResponse.setDescription("My tests2");
        groupResponse.setColorHex("#443323");
        groupResponse.setIconPath("Images/201710/file636449551671201110.png");
        return  groupResponse;
    }
}
