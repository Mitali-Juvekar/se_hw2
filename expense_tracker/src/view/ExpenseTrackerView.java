package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

// import controller.AmountFilter;
// import controller.CategoryFilter;
import controller.InputValidation;
import controller.CombinedFilter;
import controller.TransactionFilter;
import controller.ExpenseTrackerController; // Import the ExpenseTrackerController class

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JButton applyFilterBtn;
  private JButton clearFilterBtn;
  private JButton AmtFilterBtn;
  private JButton CatFilterBtn;
  // private TransactionFilter currentFilter = null;

  private ExpenseTrackerController controller; // Add a controller reference

  public void setController(ExpenseTrackerController controller) {
    this.controller = controller;
  }

  public ExpenseTrackerView() {
    setTitle("Expense Tracker");
    setSize(600, 400);

    String[] columnNames = { "Serial", "Amount", "Category", "Date" };
    this.model = new DefaultTableModel(columnNames, 0);
    addTransactionBtn = new JButton("Add Transaction");
    applyFilterBtn = new JButton("Filter by both");
    AmtFilterBtn = new JButton("Filter by amount");
    CatFilterBtn = new JButton("Filter by category");
    clearFilterBtn = new JButton("Clear Filter");

    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    transactionsTable = new JTable(model);

    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel);
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    inputPanel.add(AmtFilterBtn);
    inputPanel.add(CatFilterBtn);
  

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(applyFilterBtn);
    buttonPanel.add(AmtFilterBtn);
    buttonPanel.add(CatFilterBtn);
    buttonPanel.add(clearFilterBtn);

    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    setSize(700, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    clearFilterBtn.addActionListener(e -> {
      categoryField.setText("");
      amountField.setText("");
      controller.clearFilter();
    });

    // Set a custom cell renderer to highlight rows in green
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
          c.setBackground(new Color(173, 255, 168)); // Highlight selected rows in green
        } else {
          c.setBackground(Color.WHITE); // Reset other rows to white
        }
        return c;
      }
    };

    transactionsTable.setDefaultRenderer(Object.class, renderer);
  }

  public void highlightFilteredRows(List<Transaction> filteredTransactions) {
    
    transactionsTable.clearSelection(); // Clear previous selection
    for (Transaction transaction : filteredTransactions) {

      System.out.println(transaction.getTimestamp());
      for (int i = 0; i < model.getRowCount()-1; i++) {
        boolean shouldHighlight = false;
        Object amountValue = model.getValueAt(i, 1);
        Object categoryValue = model.getValueAt(i, 2);
        boolean matchesAmount = amountValue.equals(transaction.getAmount());
        boolean matchesCategory = categoryValue.equals(transaction.getCategory());
        //System.out.println("Category Value: " + matchesCategory + " Amount Value " + matchesAmount);
        if (matchesAmount && matchesCategory) 
        shouldHighlight = true;
        
        if (shouldHighlight)
        transactionsTable.addRowSelectionInterval(i, i);
          // Highlight the row by setting its background color to green
          // for (int j = 0; j < model.getColumnCount(); j++) {
          //     model.setValueAt(Color.GREEN, i, j);
          // } 
      }
    }
  }

    
  public void clearRowHighlights() {
    transactionsTable.clearSelection();
  }

  public void refreshTable(List<Transaction> transactions) {
    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = model.getRowCount();
    double totalCost = 0;
    // Calculate total cost
    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }
    // Add rows from transactions list
    for (Transaction t : transactions) {
      model.addRow(new Object[] { rowNum += 1, t.getAmount(), t.getCategory(), t.getTimestamp() });
    }
    // Add total row
    Object[] totalRow = { "Total", null, null, totalCost };
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();
  }

  public JButton applyFilterBtn() {
    return applyFilterBtn;
  }
  public JButton clearFilterBtn() {
    return clearFilterBtn;
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public JButton AmtFilterBtn() {
    return AmtFilterBtn;
  }

  public JButton CatFilterBtn() {
    return CatFilterBtn;
  }


  public DefaultTableModel getTableModel() {
    return model;
  }

  // Other view methods
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
