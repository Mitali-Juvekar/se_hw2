import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import controller.CombinedFilter;

public class ExpenseTrackerApp{
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
        if (!categoryFilter.isEmpty() && amountFilter != 0) {
          // Combine category and amount filters using 'and' logic
          controller.applyFilter(new CombinedFilter(categoryFilter, amountFilter));
        }
        // else if (!categoryFilter.isEmpty()) {
        // controller.applyFilter(new CategoryFilter(categoryFilter));
        // } else if (amountFilter != 0) {
        // controller.applyFilter(new AmountFilter(amountFilter));
        // }
      }
    });

  

  }

}