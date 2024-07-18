import com.ofss.main.domain.Account;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Account account = new Account();

        account.accountNumber = 101;

        System.out.println(account.accountNumber);

    }
}
