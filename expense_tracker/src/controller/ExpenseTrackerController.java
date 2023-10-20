package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;


import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController{
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  // private TransactionFilter currentFilter = null;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void applyFilter(TransactionFilter filtered) {
    List<Transaction> filteredTransactions = filtered.filter(model.getTransactions());
    filteredTransactions.forEach(element -> System.out.println(element));
    view.highlightFilteredRows(filteredTransactions);
    // view.highlightFilteredRows(filtered);
}


  public void clearFilter() {
  view.clearRowHighlights();
}

  public void refresh() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();
    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
}