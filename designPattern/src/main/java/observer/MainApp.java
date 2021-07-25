package observer;

public class MainApp {
    public static void main(String[] args) {
        Book b1 = new Book();

        b1.setName("<>");

        b1.setPrice(45.00);//假设原价是60，现在是降价促销

//下面的观察者在实际的应用中可以从数据库或文件中读取

        BuyerMobileMessage bm = new BuyerMobileMessage();

        bm.setBuyerId("001");

        bm.setMobileNo("13810500085");

        BuyerEmail be = new BuyerEmail();

        be.setBuyerId("001");

        be.setEmail("dobodo@163.com");

//增加观察者，在实际应用中就是那些人对该书做了关注

        b1.addObserver(bm);

        b1.addObserver(be);

        b1.modifyPrice(b1);
    }
}
