package ex04;
public class TransactionsService {
    private UsersArrayList userList;
    
    public TransactionsService(UsersArrayList userList) {
        this.userList = userList;
    }

    public void addUser (User user) {
        userList.addUser(user);
    }

    public UsersArrayList geUserList () {
        return this.userList;
    }

    public double getUserBalance (User user) {
        return user.getBalance();
    }

    public void createTransferTransaction (int userId1, int userId2, double amount) {
        User sender = userList.getUserById(userId1);
        User recipient = userList.getUserById(userId2);
        Transaction[] transactions = Transaction.createTransaction(sender, recipient, amount);
        if (transactions == null) {
            return;
        }
        recipient.setTransactionsList(transactions[0]);
        sender.setTransactionsList(transactions[1]);
    }

    public Transaction[] getUserTransferTransactions(User user) {
        TransactionsList transactions = user.getTransactionsList();
        return transactions.transformTransactionList();
    }

    public void removeTransferTransaction(User user, String transactionId) {
        TransactionsList transactions = user.getTransactionsList();
        transactions.removeTransactionById(transactionId);
    }

    private  TransactionsList checkValidity(Transaction[] transactionsA, Transaction[] transactionsB) {
        boolean found = false;
        TransactionsList invalidTransactions = new TransactionsLinkedList();
        for (int i = 0; i <= transactionsA.length; ++i) {
            for (int j = 0; j < transactionsB.length; ++j) {
                if (transactionsA[i].getIdentifier() == transactionsB[i].getIdentifier()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                invalidTransactions.addTransaction(transactionsA[i]);
            }
        }
        return invalidTransactions;
    }

    public Transaction[] checkTransferTranscationValidity() {
        TransactionsList invalidTransactions = new TransactionsLinkedList();
        TransactionsList tmpInvalidTransaction = new TransactionsLinkedList();

        for (int i = 0; i <= userList.getNumberOfUsers(); ++i) {
            User userA = userList.getUserById(i);
            Transaction[] userATransactions = userA.getTransactionsList().transformTransactionList();
            for (int j = 0; j < userList.getNumberOfUsers(); ++j) {
                User userB = userList.getUserById(j);
                Transaction[] userBTransactions = userB.getTransactionsList().transformTransactionList();
                tmpInvalidTransaction = checkValidity(userATransactions, userBTransactions);
            }
        }
        return new Transaction[0];
    }
}