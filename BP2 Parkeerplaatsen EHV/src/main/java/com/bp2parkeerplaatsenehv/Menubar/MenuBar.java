package com.bp2parkeerplaatsenehv.Menubar;

import javafx.scene.layout.Pane;

public class MenuBar extends javafx.scene.control.MenuBar {
    ClickAbleMenuOverzichtParkeerplaatsen overzichtParkeerplaatsen = new ClickAbleMenuOverzichtParkeerplaatsen("Overzicht parkeerplaatsen");
    ClickAbleMenuInschrijvingspagina inschrijfpagina = new ClickAbleMenuInschrijvingspagina("Inschrijven");
    ClickAbleMenuReserveren reserveren = new ClickAbleMenuReserveren("Parkeerplaats reserveren");
    ClickAbleMenuInzageReserveringen inzageReserveringen = new ClickAbleMenuInzageReserveringen("Inzage reserveringen");

    public MenuBar(Pane root) {
        overzichtParkeerplaatsen.setPane(root);
        inschrijfpagina.setPane(root);
        reserveren.setPane(root);
        this.getMenus().addAll(overzichtParkeerplaatsen, inschrijfpagina, reserveren, inzageReserveringen);
    }
}