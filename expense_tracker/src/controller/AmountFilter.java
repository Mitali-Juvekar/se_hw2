// package controller;

// import model.Transaction;
// import java.util.ArrayList;
// import java.util.List;


// public class AmountFilter implements TransactionFilter {
//     private double amount;

//     public AmountFilter(double amount) {
//         this.amount = amount;
//     }

//     @Override
//     public List<Transaction> filter(List<Transaction> transactions) {
//         List<Transaction> filteredTransactions = new ArrayList<>();
//         for (Transaction transaction : transactions) {
//             System.out.println("Amount for:"+transaction);
//             if (transaction.getAmount() == amount) {
//                 filteredTransactions.add(transaction);
//             }
//         }
//         return filteredTransactions;
//     }
// }