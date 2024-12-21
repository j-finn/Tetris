package main.java;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Simple frame");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        var gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // i.e. size of window becomes size of GamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.launchGame();
    }
}
