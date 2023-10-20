# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## New Functionalities

1.Changed the state of the transactions list from public to private
2.Made the transactions list immutable.
3.Made fields in Transaction class immutable by setting them to private… 
4.Added 3 new filtering strategies; filtering by amount/category/both. When a particular filtering strategy is slected the rows corresponding to the given filteres gets highlighted to green in the table and rest of the rows remain as it is.
5.Made modifications to view, model and controller; added new classes amountfilter,categoryfilter and combinedfilter; added a new mothod in view to highlightfilteredrows.

