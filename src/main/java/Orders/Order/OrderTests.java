package Orders.Order;

import Auth.GetToken;
import Departments.Department;
import Departments.DepartmentData;
import Departments.DepartmentResponse;
import Departments.Tables.TableResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesData;
import Menu.MenuData;
import Menu.MenuResponse;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Option.Group.OptionGroup;
import Nomenclature.Option.Group.OptionGroupData;
import Nomenclature.Option.Group.OptionGroupResponse;
import Nomenclature.Option.Option.Option;
import Nomenclature.Option.Option.OptionData;
import Nomenclature.Option.Option.OptionPatchOptionData;
import Nomenclature.Option.Option.OptionResponse;
import Nomenclature.Sizes.Size;
import Nomenclature.Sizes.SizeData;
import Nomenclature.Sizes.SizeResponse;
import com.google.gson.Gson;
import com.ibm.icu.impl.UResource;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class OrderTests {

        String name = (LocalTime.now()).toString();
        String code = LocalTime.now().toString();
        int departmentId;
        int tableId;
        long menuVersion;
        int nomenclatureId;
        int nomenclatureIdSizeExtensions;
        int sizeId;
        int sizeIdExtension;
        int optionGroupId;
        int optionId;
        DepartmentData departmentsData = new DepartmentData();
        TablesData tablesData = new TablesData();
        MenuData menuData = new MenuData();
        NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
        SizeData sizeData = new SizeData();
        OptionGroupData optionGroupData = new OptionGroupData();
        OptionData  optionData = new OptionData();
        OrderData orderData = new OrderData();
        OptionPatchOptionData optionPatchOptionData = new OptionPatchOptionData();
        public String token;
        Random random = new Random();
        int a = random.nextInt(8999)+1000;
        int b = random.nextInt(8999)+1000;
        public  String initialization = "6f81303a-"+a+"-"+b+"-83f9-7927b927d2cf";

        @BeforeClass
        public void setup() {
            GetToken getToken = new GetToken();
            this.token = getToken.GetFinallyToken();

            ResponseBody departmentresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(departmentsData.CreatePreparingDepartments(name))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://staging.eservia.com:8009/api/v0.0/Departments").thenReturn().body();
            DepartmentResponse departmentResponse = new Gson().fromJson(departmentresponse.asString(), DepartmentResponse.class);
            Department department = departmentResponse.data;
            this.departmentId = department.getId();

            ResponseBody tableresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(tablesData.createTable(departmentId,code))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8009/api/v0.0/Tables").thenReturn().body();
            TableResponse tableResponse = new Gson().fromJson(tableresponse.asString(), TableResponse.class);
            Tables tables = tableResponse.data;
            this.tableId = tables.getId();

            ResponseBody nomenclatureresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(nomenclatureTestData.createNomenclatureThirdType())
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Nomenclature").thenReturn().body();
            NomenclatureResponse nomenclatureResponse= new Gson().fromJson(nomenclatureresponse.asString(), NomenclatureResponse.class);
            Nomenclature nomenclature= nomenclatureResponse.data;
            this.nomenclatureId = Integer.parseInt(nomenclature.getId());

            ResponseBody sizeresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(sizeData.createSize(nomenclatureId))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Sizes").thenReturn().body();
            SizeResponse sizeResponse= new Gson().fromJson(sizeresponse.asString(), SizeResponse.class);
            Size size= sizeResponse.getData();
            this.sizeId = size.getId();

            ResponseBody nomenclatureresponse1 = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(nomenclatureTestData.createNomenclatureFourthType())
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Nomenclature").thenReturn().body();
            NomenclatureResponse nomenclatureResponse1= new Gson().fromJson(nomenclatureresponse1.asString(), NomenclatureResponse.class);
            Nomenclature nomenclature1= nomenclatureResponse1.data;
            this.nomenclatureIdSizeExtensions = Integer.parseInt(nomenclature1.getId());

            ResponseBody sizeresponse1 = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(sizeData.createSizeExtension(nomenclatureIdSizeExtensions))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Sizes").thenReturn().body();
            SizeResponse sizeResponse1= new Gson().fromJson(sizeresponse1.asString(), SizeResponse.class);
            Size size1= sizeResponse1.getData();
            this.sizeIdExtension = size1.getId();

            ResponseBody optiongroupresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(optionGroupData.createOptionGroup())
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Options/Groups").thenReturn().body();
            OptionGroupResponse optionGroupResponse= new Gson().fromJson(optiongroupresponse.asString(), OptionGroupResponse.class);
            OptionGroup optionGroup= optionGroupResponse.getData();
            this.optionGroupId = optionGroup.getId();

            ResponseBody optionresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(optionData.createOption(optionGroupId,nomenclatureIdSizeExtensions))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Options").thenReturn().body();
            OptionResponse optionResponse= new Gson().fromJson(optionresponse.asString(), OptionResponse.class);
            Option option= optionResponse.getData();
            this.optionId = option.getId();

            ResponseBody patchOption = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(optionPatchOptionData.setOption(optionId))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().patch("http://staging.eservia.com:8008/api/v0.0/Nomenclature/"+nomenclatureId+"/Options").thenReturn().body();

            ResponseBody menuReload = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(menuData.reloadMenu(departmentId))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8008/api/v0.0/Menu/Reload").thenReturn().body();

            ResponseBody menuresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().get("http://auth.staging.eservia.com:8006/api/v0.0/Menu/Version?AddressId=2&DepartmentId="+departmentId).thenReturn().body();
            MenuResponse menuResponse= new Gson().fromJson(menuresponse.asString(), MenuResponse.class);
            this.menuVersion = menuResponse.getData();
        }

        @Test
        public void CreateOrder() {
            ResponseBody optionresponse = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(orderData.createNewOrder(tableId, nomenclatureId, departmentId,optionId,menuVersion,initialization, nomenclatureIdSizeExtensions))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8006/api/v0.0/Orders").thenReturn().body();
            System.out.println(2);
        }
    }

