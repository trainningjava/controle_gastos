package org.example;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      EconomicSystemGUI gui = new EconomicSystemGUI();
      gui.setVisible(true);
    });
  }
}