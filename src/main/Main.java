package main;

import com.formdev.flatlaf.FlatDarkLaf;
import frame.Menu;
import sound.Sound;

public class Main {
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar tema: " + ex.getMessage());
        }

        new Menu();
    }
}