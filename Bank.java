public class Bank {
    private static final int MAX = 10;
    private String name;
    private Account_Holder[] account_holders;
    private int holders_index;

    static double amounts_sum = 0;

    public Bank (String name) {
        this.name = name;
        this.account_holders = new Account_Holder[MAX];
        this.holders_index = 0;
    }

    public boolean addAccount (String name, String last_name) {
        if(MAX < holders_index+1) {
            return false;
        }

        Account_Holder acc = new Account_Holder(name, last_name, "IT");
        account_holders[holders_index] = acc;
        holders_index++;
        return true;
    }

    public boolean removeAccount (String code) {
        int index = getAccountIndexByCode(code);
        if(index == -1) {
            return false;
        }

        Account_Holder acc = account_holders[index];

        for(; index<holders_index; index++) {
            account_holders[index] = account_holders[index+1];
        }

        account_holders[holders_index] = null;
        holders_index--;
        System.out.println("Account removed successfully: " + acc.toString());
        return true;
    }

    public boolean loadMoneyToAccount(double amount, String code) {
        int index = getAccountIndexByCode(code);
        if(index == -1 || amount < 0) {
            return false;
        }

        account_holders[index].loadMoney(amount);
        amounts_sum += amount;
        return true;
    }

    public boolean withdrawMoneyFromAccount(double amount, String code) {
        int index = getAccountIndexByCode(code);
        if(index == -1 || amount < 0) {
            return false;
        }

        if(account_holders[index].isWithdrawable(amount)) {
            account_holders[index].withdrawMoney(amount);
        }

        amounts_sum -= amount;
        return true;
    }

    public boolean transferMoney(double amount, String sender_code, String receiver_code) {
        if(sender_code.equals(receiver_code)) {
            return false;
        }

        int sender_index = getAccountIndexByCode(sender_code);
        int receiver_index = getAccountIndexByCode(receiver_code);

        if (sender_index == -1 || receiver_index == -1 || amount < 0) {
            return false;
        }

        Account_Holder sender = account_holders[sender_index];
        if(!sender.isWithdrawable(amount)) {
            return false;
        }

        Account_Holder receiver = account_holders[receiver_index];
        sender.withdrawMoney(amount);
        receiver.loadMoney(amount);
        return true;
    }

    public boolean addInsuranceTo (Insurance new_insurance, String account_code) {
        int account_index = getAccountIndexByCode(account_code);
        if(account_index == -1) {
            return false;
        }

        Account_Holder acc = account_holders[account_index];
        return acc.addInsurance(new_insurance);
    }

    public boolean removeInsuranceFrom (final int insurance_id, String account_code) {
        int account_index = getAccountIndexByCode(account_code);
        if(account_index == -1) {
            return false;
        }

        Account_Holder acc = account_holders[account_index];
        return acc.removeInsuranceById(insurance_id);
    }

    private int getAccountIndexByCode (String code) {
        for(int i=0; i<holders_index; i++) {
            if(code.equals(account_holders[i].getCode())) {
                return i;
            }
        }
        return -1;
    }
}
