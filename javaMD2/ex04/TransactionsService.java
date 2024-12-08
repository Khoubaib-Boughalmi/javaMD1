package ex04;

public class TransactionsService {
    private final UsersArrayList userList;

    public TransactionsService(UsersArrayList userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.addUser(user);
    }

    public UsersArrayList geUserList() {
        return this.userList;
    }

    public double getUserBalance(User user) {
        return user.getBalance();
    }

    public void createTransferTransaction(int userId1, int userId2, double amount) {
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
        System.out.println("trasnsationID" + transactionId);
        transactions.removeTransactionById(transactionId);
    }

    public Transaction[] checkTransferTranscationValidity() {
        TransactionsList invalidTransactions = new TransactionsLinkedList();

        for (int i = 1; i <= userList.getNumberOfUsers(); ++i) {
            User currentUser = userList.getUserById(i);
            Transaction[] userTransactions = currentUser.getTransactionsList().transformTransactionList();

            for (Transaction transaction : userTransactions) {
                User sender = userList.getUserById(transaction.getSender().getIdentifier());
                User receiver = userList.getUserById(transaction.getSender().getIdentifier());

                boolean existsInSenderList = isTransactionInList(sender.getTransactionsList(), transaction);
                boolean existsInReceiverList = isTransactionInList(receiver.getTransactionsList(), transaction);

                if (!existsInSenderList || !existsInReceiverList) {
                    invalidTransactions.addTransaction(transaction);
                    System.out.println("Unpaired transaction: " + transaction.getIdentifier());
                }
            }
        }

        return invalidTransactions.transformTransactionList();
    }

    // check if a transaction exists
    private boolean isTransactionInList(TransactionsList list, Transaction transaction) {
        Transaction[] transactions = list.transformTransactionList();
        for (Transaction t : transactions) {
            if (t.getIdentifier().equals(transaction.getIdentifier())) {
                return true;
            }
        }
        return false;
    }
}