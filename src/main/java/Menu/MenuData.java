package Menu;

public class MenuData {
    public Menu reloadMenu(int departmentId){
        Menu menu = new Menu();
        menu.setDepartmentId(departmentId);
        return menu;
    }
}
