
import javafx.beans.property.*;

public class t_table {
    private final IntegerProperty transId;
    private final IntegerProperty sendId;
    private final IntegerProperty recId;
    private final IntegerProperty amount;
    private final StringProperty date;

    public t_table(int transId, int sendId, int recId, int amount, String date) {
        this.transId = new SimpleIntegerProperty(transId);
        this.sendId = new SimpleIntegerProperty(sendId);
        this.recId = new SimpleIntegerProperty(recId);
        this.amount = new SimpleIntegerProperty(amount);
        this.date = new SimpleStringProperty(date);
    }

    public int getTransId() {
        return transId.get();
    }

    public IntegerProperty transIdProperty() {
        return transId;
    }

    public int getSendId() {
        return sendId.get();
    }

    public IntegerProperty sendIdProperty() {
        return sendId;
    }

    public int getRecId() {
        return recId.get();
    }

    public IntegerProperty recIdProperty() {
        return recId;
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty getDateProperty() {
        return date;
    }
}