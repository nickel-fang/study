package observer;

import java.util.Observable;
import java.util.Observer;

public class BuyerEmail implements Observer {
    private String buyerId = "";
    private String email = "";

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void update(Observable o, Object arg) {
        Book b = (Book) arg;
        System.out.println("给顾客的发电子邮件:" + b.getName() + "降价了,目前价格为：" + b.getPrice());
    }
}
