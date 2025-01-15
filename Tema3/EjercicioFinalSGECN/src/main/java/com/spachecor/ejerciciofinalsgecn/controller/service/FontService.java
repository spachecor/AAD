package com.spachecor.ejerciciofinalsgecn.controller.service;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.text.Font;

/**
 * Clase FontService que sirve para aplicar fuentes personalizadas.
 * @author Selene
 * @version 1.0
 */
public class FontService {
    public static final Font BIG_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            52
    );
    public static final Font LARGE_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            32
    );
    public static final Font NORMAL_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            24
    );
    public static final Font SMALL_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            18
    );
    public static final Font X_SMALL_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            14
    );

    /**
     * Método que aplica una fuente a un objeto del tipo Control.
     * @param font La fuente a aplicar
     * @param controls El objeto tipo Control al que aplicar la fuente
     */
    public static void setFont(Font font, Control... controls) {
        for (Control control : controls) {
            if (control instanceof Labeled) {
                ((Labeled) control).setFont(font);
            } else if (control instanceof TextField) {
                ((TextField) control).setFont(font);
            } else if (control instanceof ChoiceBox) {
                ChoiceBox<?> choiceBox = (ChoiceBox<?>) control;
                choiceBox.setStyle("-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "px;");
                choiceBox.showingProperty().addListener((obs, wasShowing, isShowing) -> {
                    if (isShowing) {
                        ListView<?> listView = (ListView<?>) choiceBox.lookup(".list-view");
                        if (listView != null) {
                            listView.setStyle("-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "px;");
                        }
                    }
                });
            } else if (control instanceof DatePicker) {
                DatePicker datePicker = (DatePicker) control;
                datePicker.setStyle("-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "px;");

                datePicker.showingProperty().addListener((obs, wasShowing, isShowing) -> {
                    if (isShowing) {
                        ComboBox<?> comboBox = (ComboBox<?>) datePicker.lookup(".combo-box");
                        if (comboBox != null) {
                            comboBox.setStyle("-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "px;");
                        }
                        DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
                        if (skin != null) {
                            Node popupContent = skin.getPopupContent();
                            if (popupContent != null) {
                                popupContent.setStyle("-fx-font-family: '" + font.getFamily() + "'; -fx-font-size: " + font.getSize() + "px;");
                            }
                        }
                    }
                });
            }
        }
    }

}
