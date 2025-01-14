package com.spachecor.ejerciciofinalsgecn.controller.service;

import javafx.scene.text.Font;

public class FontService {
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
}
