import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        switch(Main.menu()) {

        }
    }

    public static byte menu() {
        return menu(0);
    }

    private static byte menu(int calls) {
        if(calls == 3) {
            System.out.println("\nYou typed an unexpected number three times. Exit...");
            System.exit(1);
        }

        byte choice = -1;
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option between the following ones: ");
        System.out.println("1) Load money to an account");
        System.out.println("2) Withdraw money from an account");
        System.out.println("3) Print information about an account");
        System.out.println("4) Transfer money from an account to another");
        System.out.println("5) View options about insurances");
        System.out.println("6) View options for bank administrators");
        System.out.println("7) Open an insurance policy");
        System.out.println("8) Print insurances list for an account");
        System.out.println("9) Print the list of every active insurances");
        System.out.println("10) Close an insurance policy");
        System.out.println("11) See how many insurances have been opened in bank's history");
        System.out.println("12) See how much money is held in the bank");
        System.out.println("0) Terminate the program\n> ");
        choice = sc.nextByte();

        if(choice == 0) {
            System.out.println("\nOk, bye. Exit...");
            System.exit(1);
        }

        else if(choice < 0 || choice > 12) {
            System.out.println("Unexpected value: " + choice + "\n");
            return menu(calls++);
        }

        return choice;
    }


}