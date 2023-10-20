import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import controller.AmountFilter;
import controller.CategoryFilter;
import controller.CombinedFilter;

public class ExpenseTrackerApp {
  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setController(controller);
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.applyFilterBtn().addActionListener(e -> {
      String categoryFilter = view.getCategoryField();
      double amountFilter = view.getAmountField();

      if (categoryFilter.isEmpty() && amountFilter == 0) {
        JOptionPane.showMessageDialog(view, "Please enter a category or amount for filtering.");
      } else {
        if (!categoryFilter.isEmpty() && amountFilter == 0) {
          // Combine category and amount filters using 'and' logic
          JOptionPane.showMessageDialog(view, "Please enter a amount value for filtering by both");
        }
        if (categoryFilter.isEmpty() && amountFilter != 0) {
          // Combine category and amount filters using 'and' logic
          JOptionPane.showMessageDialog(view, "Please enter a category value for filtering by both");
        }
        if (!categoryFilter.isEmpty() && amountFilter != 0) {
          // Combine category and amount filters using 'and' logic
          controller.applyFilter(new CombinedFilter(categoryFilter, amountFilter));
        }
        }
    });

    view.AmtFilterBtn().addActionListener(e -> {
      double amountFilter = view.getAmountField();

      if (amountFilter == 0) {
        JOptionPane.showMessageDialog(view, "Please enter amount Value for filteirng by amount");
      } else {
                if (amountFilter != 0) {
          // Combine amount filters 
          controller.applyFilter(new AmountFilter(amountFilter));
        }
        }
    });

    view.CatFilterBtn().addActionListener(e -> {
      String categoryFilter = view.getCategoryField();
      if (categoryFilter.isEmpty() ) {
        JOptionPane.showMessageDialog(view, "Please enter a category for filtering by category");
      } else {
        
        if (!categoryFilter.isEmpty()) {
          // Combine category and amount filters using 'and' logic
          controller.applyFilter(new CategoryFilter(categoryFilter));
        }
        }
    });


    
    // view.delTransactionBtn().addTableModelListener(e -> {
    //   if (e.getType() == TableModelEvent.UPDATE) {
    //     // Handle cell selection change here
    //     int row = myTable.getSelectedRow();
    //     int col = myTable.getSelectedColumn();
    //     Object selectedValue = myTable.getValueAt(row, col);
    //     System.out.println("Selected Cell: " + selectedValue);
    //   }
    // });

  }

}