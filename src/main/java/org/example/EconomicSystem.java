package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EconomicSystem {
  private final ArrayList<Record> records;
  private final String filename = "valor.txt";

  public EconomicSystem() {
    records = new ArrayList<>();
    loadRecords();
  }

  public void loadRecords() {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        records.add(new Record(Double.parseDouble(parts[0]), parts[1], parts[2]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveRecords() {
    try (
      BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for (Record record : records) {
        bw.write(record.getValue() + "," + record.getDescription() + "," + record.getDate());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addRecord(double value, String description, String date) {
    records.add(new Record(value, description, date));
    saveRecords();
  }

  public void updateRecord(int index, double value, String description, String date) {
    if (index >= 0 && index < records.size()) {
      records.get(index).setValue(value);
      records.get(index).setDescription(description);
      records.get(index).setDate(date);
      saveRecords();
    }
  }

  public void deleteRecord(int index) {
    if (index >= 0 && index < records.size()) {
      records.remove(index);
      saveRecords();
    }
  }

  public Double summaryRecord() {
    double value = 0.0;
    for (Record record : records) {
      value += record.getValue();
    }
    return value;
  }

  public ArrayList<Record> getRecords() {
    return records;
  }
}
