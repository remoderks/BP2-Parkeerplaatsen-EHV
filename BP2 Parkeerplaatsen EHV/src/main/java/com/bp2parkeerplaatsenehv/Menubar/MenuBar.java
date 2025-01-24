package com.bp2parkeerplaatsenehv.Menubar;

import javafx.scene.layout.Pane;

public class MenuBar extends javafx.scene.control.MenuBar {
    ClickAbleMenuInschrijvingspagina inschrijfpagina = new ClickAbleMenuInschrijvingspagina("Inschrijven");
    ClickAbleMenuReserveren reserveren = new ClickAbleMenuReserveren("Parkeerplaats reserveren");
    ClickAbleMenuInzageReserveringen inzageReserveringen = new ClickAbleMenuInzageReserveringen("Inzage reserveringen");
    ClickAbleMenuParkeerplaatsenLijst parkeerplaatsenLijst = new ClickAbleMenuParkeerplaatsenLijst("Parkeerplaatsenlijst");
    ClickAbleMenuBeschikbaarheidParkeerplaats beschikbaarheidParkeerplaats = new ClickAbleMenuBeschikbaarheidParkeerplaats("Toevoegen parkeerplaats");
    ClickAbleMenuInzageKlanten inzageKlanten = new ClickAbleMenuInzageKlanten("Inzage klanten");

    public MenuBar(Pane root) {
        inschrijfpagina.setPane(root);
        reserveren.setPane(root);
        inzageReserveringen.setPane(root);
        parkeerplaatsenLijst.setPane(root);
        beschikbaarheidParkeerplaats.setPane(root);
        this.getMenus().addAll(parkeerplaatsenLijst, inschrijfpagina, reserveren, inzageReserveringen, beschikbaarheidParkeerplaats, inzageKlanten);
    }
}
