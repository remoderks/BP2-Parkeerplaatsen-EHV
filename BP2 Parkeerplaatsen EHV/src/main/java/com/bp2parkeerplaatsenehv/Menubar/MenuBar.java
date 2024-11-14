package com.bp2parkeerplaatsenehv.Menubar;

import javafx.scene.layout.Pane;

public class MenuBar extends javafx.scene.control.MenuBar {
    ClickAbleMenuKaart overzichtParkeerplaatsen = new ClickAbleMenuKaart("Overzicht parkeerplaatsen");
    ClickAbleMenuPersoonsgegevens persoonsgegevens = new ClickAbleMenuPersoonsgegevens("Persoonsgegevens");
    ClickAbleMenuReserveren reserveren = new ClickAbleMenuReserveren("Reserveren");

    public MenuBar(Pane root) {
        overzichtParkeerplaatsen.setPane(root);
        persoonsgegevens.setPane(root);
        reserveren.setPane(root);
        this.getMenus().addAll(overzichtParkeerplaatsen, persoonsgegevens, reserveren);
    }


}