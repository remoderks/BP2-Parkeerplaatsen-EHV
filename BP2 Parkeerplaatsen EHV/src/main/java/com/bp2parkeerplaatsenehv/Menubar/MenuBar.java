package com.bp2parkeerplaatsenehv.Menubar;

public class MenuBar {
    private String menuName;
    private String menuIcon;

    public MenuBar(String menuName, String menuIcon) {
        this.menuName = menuName;
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }
}
