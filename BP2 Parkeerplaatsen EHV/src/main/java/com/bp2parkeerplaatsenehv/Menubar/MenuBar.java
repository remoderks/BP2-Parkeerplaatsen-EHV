package com.bp2parkeerplaatsenehv.Menubar;

import javafx.scene.layout.Pane;

public class MenuBar extends javafx.scene.control.MenuBar {
    ClickAbleMenuInschrijvingspagina inschrijfpagina = new ClickAbleMenuInschrijvingspagina("Inschrijven");
    ClickAbleMenuReserveren reserveren = new ClickAbleMenuReserveren("Parkeerplaats reserveren");
    ClickAbleMenuInzageReserveringen inzageReserveringen = new ClickAbleMenuInzageReserveringen("Inzage reserveringen");
    ClickAbleMenuParkeerplaatsenLijst parkeerplaatsenLijst = new ClickAbleMenuParkeerplaatsenLijst("Parkeerplaatsenlijst");
    ClickAbleMenuBeschikbaarheidParkeerplaats beschikbaarheidParkeerplaats = new ClickAbleMenuBeschikbaarheidParkeerplaats("Toevoegen parkeerplaats");
    ClickAbleMenuInzageKlanten inzageKlanten = new ClickAbleMenuInzageKlanten("Inzage klanten");

    // Constructor for the MenuBar
    public MenuBar(Pane p) {
        inschrijfpagina.setPane(p);
        reserveren.setPane(p);
        inzageReserveringen.setPane(p);
        parkeerplaatsenLijst.setPane(p);
        beschikbaarheidParkeerplaats.setPane(p);
        inzageKlanten.setPane(p);

        getMenus().addAll(parkeerplaatsenLijst, inschrijfpagina, reserveren, inzageReserveringen, inzageKlanten, beschikbaarheidParkeerplaats);
    }
}
