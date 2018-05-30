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
import Orders.OrderItem.OrderItemData;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class OrderTests {
        int orderFirstId;
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
        int orderId;
        int orderItemId;
        OrderItemData orderItemData = new OrderItemData();
        OrderStatusesData orderStatuses = new OrderStatusesData();
        DepartmentData departmentsData = new DepartmentData();
        TablesData tablesData = new TablesData();
        MenuData menuData = new MenuData();
        NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
        SizeData sizeData = new SizeData();
        OptionGroupData optionGroupData = new OptionGroupData();
        OptionData  optionData = new OptionData();
        OrderData orderData = new OrderData();
        OptionPatchOptionData optionPatchOptionData = new OptionPatchOptionData();
        OrderItemStatusData orderItemStatusData = new OrderItemStatusData();
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
        public void A_createOrder() {
            ResponseBody orderRespons = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(orderData.createNewOrder(tableId, nomenclatureId, departmentId,optionId,menuVersion,initialization, nomenclatureIdSizeExtensions))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://auth.staging.eservia.com:8006/api/v0.0/Orders").thenReturn().body();
            OrderResponse orderResponse= new Gson().fromJson(orderRespons.asString(), OrderResponse.class);
            Order order  = orderResponse.getData();
            this.orderFirstId = order.getId();
            this.orderItemId = order.getOrderItems().get(0).getId();
            Assert.assertEquals("e1159ff5-5b09-489f-8949-122e59c4ec44",order.getWaiterId());
            Assert.assertEquals(tableId,order.getTableId());
            Assert.assertEquals(departmentId,order.getDepartmentId());
            Assert.assertEquals(initialization,order.getInitializationId());
            Assert.assertEquals("Цей прекрасний ордер повинен бути виконаним",order.getDescription());
            Assert.assertEquals(1,order.getOrderTypeId());
            Assert.assertEquals(42,order.getTotalPrice());
            Assert.assertEquals(nomenclatureId,order.getOrderItems().get(0).getNomenclatureId());
            Assert.assertEquals(2,order.getOrderItems().get(0).getAmount());
            Assert.assertEquals(10,order.getOrderItems().get(0).getSize());
            Assert.assertEquals(initialization,order.getOrderItems().get(0).getInitializationId());
            Assert.assertEquals("Цей прекрасний айтем повинен бути виконаним",order.getOrderItems().get(0).getDescription());
            Assert.assertEquals(nomenclatureIdSizeExtensions,order.getOrderItems().get(0).getExtensions().get(0).getExtensionId());
            Assert.assertEquals(optionId,order.getOrderItems().get(0).getExtensions().get(0).getOptionId());
            Assert.assertEquals(1,order.getOrderItems().get(0).getExtensions().get(0).getAmount());
        }

        @Test
        public  void getAllOrders(){
            RequestSpecification httpRequest = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization",token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter());
            Response response = httpRequest.get("http://staging.eservia.com:8006/api/v0.0/Orders?AddressId=2");
            Assert.assertEquals(200,response.getStatusCode());
        }

    @Test
    public void B_pickUpOrder(){
        int a = random.nextInt(8999)+1000;
        int b = random.nextInt(8999)+1000;
        this.initialization = "6f81303a-"+a+"-"+b+"-83f9-7927b927d2cf";
        ResponseBody orderRespons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderData.withoutWaiter(tableId, nomenclatureId, departmentId,optionId,menuVersion,initialization, nomenclatureIdSizeExtensions))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://auth.staging.eservia.com:8006/api/v0.0/Orders").thenReturn().body();
        OrderResponse orderResponse= new Gson().fromJson(orderRespons.asString(), OrderResponse.class);
        Order order  = orderResponse.getData();
        this.orderId=order.getId();

            ResponseBody pickUp = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId+"/PickUp").thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(initialization,orders.getInitializationId());
    }

    @Test
    public void C_chackoutOrder(){
        ResponseBody res = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId+"/Checkout").thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId).thenReturn().body();

        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order order  = orderResponse1.getData();
        Assert.assertEquals("52d95859-a6cf-4d6d-b507-9d4720947075",order.getCashierId());
        Assert.assertEquals("52d95859-a6cf-4d6d-b507-9d4720947075",order.getWaiterId());
        Assert.assertEquals(tableId,order.getTableId());
        Assert.assertEquals(departmentId,order.getDepartmentId());
        Assert.assertEquals(initialization,order.getInitializationId());
        Assert.assertEquals("Цей прекрасний ордер повинен бути виконаним",order.getDescription());
        Assert.assertEquals(1,order.getOrderTypeId());
        Assert.assertEquals(42,order.getTotalPrice());
        Assert.assertEquals(nomenclatureId,order.getOrderItems().get(0).getNomenclatureId());
        Assert.assertEquals(2,order.getOrderItems().get(0).getAmount());
        Assert.assertEquals(10,order.getOrderItems().get(0).getSize());
        Assert.assertEquals(initialization,order.getOrderItems().get(0).getInitializationId());
        Assert.assertEquals("Цей прекрасний айтем повинен бути виконаним",order.getOrderItems().get(0).getDescription());
        Assert.assertEquals(nomenclatureIdSizeExtensions,order.getOrderItems().get(0).getExtensions().get(0).getExtensionId());
        Assert.assertEquals(optionId,order.getOrderItems().get(0).getExtensions().get(0).getOptionId());
        Assert.assertEquals(1,order.getOrderItems().get(0).getExtensions().get(0).getAmount());
    }

    @Test
    public void D_CookingOrderItemStus(){
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Cooking"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId+"/Items/"+orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(4,orders.getOrderItems().get(0).getStatusId());
    }

    @Test
    public void F_ReadyOrderItemStus(){
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Ready"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId+"/Items/"+orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(5,orders.getOrderItems().get(0).getStatusId());
    }

    @Test
    public void G_DeliveredOrderItemStus(){
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Delivered"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId+"/Items/"+orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderFirstId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(6,orders.getOrderItems().get(0).getStatusId());
    }

    @Test
    public void H_paidOrderStus(){
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderStatuses.changeOrderStatus("Paid"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId+"/Status").thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(4,orders.getOrderStatusId());
    }

    @Test
    public void I_closedOrderStus(){
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderStatuses.changeOrderStatus("Closed"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId+"/Status").thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        Assert.assertEquals(5,orders.getOrderStatusId());
    }

    @Test
    public void G_addOrderItem(){
        int a = random.nextInt(8999)+1000;
        int b = random.nextInt(8999)+1000;
        this.initialization = "6f81303a-"+a+"-"+b+"-83f9-7927b927d2cf";
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemData.addOrderItem(nomenclatureId,initialization,nomenclatureIdSizeExtensions,optionId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId+"/Items").thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/"+orderId).thenReturn().body();
        OrderResponse orderResponse1= new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders  = orderResponse1.getData();
        this.orderItemId = orders.getOrderItems().get(0).getId();
        Assert.assertEquals(nomenclatureId,orders.getOrderItems().get(1).getNomenclatureId());
        Assert.assertEquals(1,orders.getOrderItems().get(1).getAmount());
        Assert.assertEquals(10,orders.getOrderItems().get(1).getSize());
        Assert.assertEquals(initialization,orders.getOrderItems().get(1).getInitializationId());
        Assert.assertEquals("Додайту цю страву до замовлення",orders.getOrderItems().get(1).getDescription());
        Assert.assertEquals(nomenclatureIdSizeExtensions,orders.getOrderItems().get(0).getExtensions().get(0).getExtensionId());
        Assert.assertEquals(optionId,orders.getOrderItems().get(0).getExtensions().get(0).getOptionId());
        Assert.assertEquals(1,orders.getOrderItems().get(0).getExtensions().get(0).getAmount());
    }

    @Test
    public void K_CookingOrderItemStatus() {
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Cooking"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId + "/Items/" + orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId).thenReturn().body();
        OrderResponse orderResponse1 = new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders = orderResponse1.getData();
        Assert.assertEquals(4, orders.getOrderItems().get(0).getStatusId());

    }

    @Test
    public void L_ReadyOrderItemStatus() {
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Ready"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId + "/Items/" + orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId).thenReturn().body();
        OrderResponse orderResponse1 = new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders = orderResponse1.getData();
        Assert.assertEquals(5, orders.getOrderItems().get(0).getStatusId());

    }

    @Test
    public void M_DeliveredOrderItemStatus() {
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Delivered"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId + "/Items/" + orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId).thenReturn().body();
        OrderResponse orderResponse1 = new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders = orderResponse1.getData();
        Assert.assertEquals(6, orders.getOrderItems().get(0).getStatusId());
    }

    @Test
    public void O_DeliveredOrderItemStatus() {
        ResponseBody pickUp = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(orderItemStatusData.changeStatus("Problem"))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId + "/Items/" + orderItemId).thenReturn().body();

        ResponseBody get = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8006/api/v0.0/Orders/" + orderId).thenReturn().body();
        OrderResponse orderResponse1 = new Gson().fromJson(get.asString(), OrderResponse.class);
        Order orders = orderResponse1.getData();
        Assert.assertEquals(7, orders.getOrderItems().get(0).getStatusId());
    }

    @AfterClass
        public void deleteCreateditems(){
            ResponseBody table = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().delete("http://staging.eservia.com:8009/api/v0.0/Tables/"+tableId).thenReturn().body();

            ResponseBody department = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().delete("http://staging.eservia.com:8009/api/v0.0/Departments/"+departmentId).thenReturn().body();

            ResponseBody nomenclature = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().delete("http://staging.eservia.com:8008/api/v0.0/Nomenclature/"+nomenclatureId).thenReturn().body();

            ResponseBody extension = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().delete("http://staging.eservia.com:8008/api/v0.0/Nomenclature/"+nomenclatureIdSizeExtensions).thenReturn().body();
        }
    }

