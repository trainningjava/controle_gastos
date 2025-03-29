package org.example;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EconomicSystemGUI extends JFrame {
  private final EconomicSystem economicSystem;
  private final JList<Record> recordList;
  private final DefaultListModel<Record> listModel;
  private final JTextField valueField;
  private final JTextField descriptionField;
  private final JTextArea summaryArea;

  public EconomicSystemGUI() {
    economicSystem = new EconomicSystem();
    listModel = new DefaultListModel<>();
    for (Record record : economicSystem.getRecords()) {
      listModel.addElement(record);
    }

    setTitle("Economic System");
    setSize(800, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    recordList = new JList<>(listModel);
    add(new JScrollPane(recordList), BorderLayout.CENTER);

    JPanel inputPanel = new JPanel();
    valueField = new JTextField(10);
    descriptionField = new JTextField(10);
    summaryArea = new JTextArea(1, 10);
    summaryArea.setEditable(false);

    JButton addButton = new JButton("Add");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton summaryButton = new JButton("Summary");

    addButton.addActionListener(e -> insertRecord());
    updateButton.addActionListener(e -> updateRecord());
    deleteButton.addActionListener(e -> deleteRecord());
    summaryButton.addActionListener(e -> showSummary());

    inputPanel.add(new JLabel("Value:"));
    inputPanel.add(valueField);
    inputPanel.add(new JLabel("Description:"));
    inputPanel.add(descriptionField);
    inputPanel.add(addButton);
    inputPanel.add(updateButton);
    inputPanel.add(deleteButton);
    inputPanel.add(summaryButton);
    inputPanel.add(summaryArea);

    add(inputPanel, BorderLayout.SOUTH);
  }

  private void insertRecord() {
    double value = Double.parseDouble(valueField.getText());
    String description = descriptionField.getText();
    String date = getDateRecord();
    economicSystem.addRecord(value, description, date);
    listModel.addElement(new Record(value, description, date));
    cleanRecord();
  }

  private void updateRecord() {
    int index = recordList.getSelectedIndex();
    if (index != -1) {
      double value = Double.parseDouble(valueField.getText());
      String description = descriptionField.getText();
      String date = getDateRecord();
      economicSystem.updateRecord(index, value, description, date);
      listModel.set(index, new Record(value, description, date));
    }
    cleanRecord();
  }

  private void deleteRecord() {
    int index = recordList.getSelectedIndex();
    if (index != -1) {
      economicSystem.deleteRecord(index);
      listModel.remove(index);
    }
  }

  private void cleanRecord() {
    valueField.setText("");
    descriptionField.setText("");
  }

  private String getDateRecord() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  private void showSummary() {
      summaryArea.setText(economicSystem.summaryRecord().toString());
  }
}
