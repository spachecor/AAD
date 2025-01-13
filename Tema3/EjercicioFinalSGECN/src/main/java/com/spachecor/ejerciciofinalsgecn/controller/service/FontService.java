package com.spachecor.ejerciciofinalsgecn.controller.service;

import javafx.scene.text.Font;

public class FontService {
    public static final Font NORMAL_FONT = Font.loadFont(
            FontService.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/font/austie-bost-flowers-solid.ttf"),
            24
    );
}
