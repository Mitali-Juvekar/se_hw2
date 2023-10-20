package controller;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;


public class CombinedFilter implements TransactionFilter {
    private double amount;
    private String category;

    public CombinedFilter(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(category) && transaction.getAmount() == amount) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

  

    // public CombinedFilter(TransactionFilter... filtered_transactions) {
    //     this.filtered_transactions = new ArrayList<>();
    //     for (TransactionFilter filtered_transactions : filters) {
    //         this.filtered_transactions.add(filters);
    //     }
    // }

    
}