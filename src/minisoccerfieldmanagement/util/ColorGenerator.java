/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.util;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author trank
 */
public class ColorGenerator {
    private static final Color[] BEAUTIFUL_COLORS = {
        new Color(255, 105, 180), // HotPink
        new Color(255, 20, 147), // DeepPink
        new Color(75, 0, 130), // Indigo
        new Color(0, 191, 255), // DeepSkyBlue
        new Color(127, 255, 0), // Chartreuse
        new Color(255, 165, 0), // Orange
        new Color(255, 99, 71), // Tomato
        new Color(220, 20, 60), // Crimson
        new Color(123, 104, 238), // MediumSlateBlue
        new Color(0, 206, 209), // DarkTurquoise
        new Color(50, 205, 50), // LimeGreen
        new Color(255, 140, 0), // DarkOrange
        new Color(255, 0, 255), // Magenta
        new Color(0, 255, 255), // Cyan
        new Color(0, 255, 0), // Lime
        new Color(255, 0, 0), // Red
        new Color(0, 0, 255), // Blue
        new Color(128, 0, 128), // Purple
        new Color(0, 128, 0), // Green
        new Color(255, 255, 0) // Yellow
    };

    public static Color getRandomColor() {
        Random random = new Random();
        return BEAUTIFUL_COLORS[random.nextInt(BEAUTIFUL_COLORS.length)];
    }
}