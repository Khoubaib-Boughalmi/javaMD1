
import java.util.UUID;



enum category {
  DEBITS,
  CREDITS
}

public class Transaction {
  private UUID      identifier;
  private User      recipient;  
  private User      sender;  
  private category  transferCategory;
  private int       amount;

  public Transaction(
      UUID identifier,
      User recipient,
      User sender,
      category transferCategory,
      int amount
  ) {
      if (sender.getBalance() - amount < 0 || amount <= 0) return;
      if (transferCategory == category.CREDITS && amount < 0) return;
      if (transferCategory == category.DEBITS && amount > 0) return;
      this.identifier = identifier;
      this.sender = sender;
      this.recipient = recipient;
      this.transferCategory = transferCategory;
      this.amount = amount; 

      sender.setBalance(sender.getBalance() - amount);
      recipient.setBalance(recipient.getBalance() + amount);
  }
}

