public class Account_Holder {
    private String name;
    private String last_name;
    private String account_number;
    private double amount;
    private Insurance insurances[];
    private int active_insurances;

    static int counter = 0;

    public Account_Holder (String name, String last_name, String account_code) {
        this.name = name;
        this.last_name = last_name;
        this.account_number = "" + account_code + ++counter;
        this.amount = 0;
        this.insurances = new Insurance[5];
        this.active_insurances = 0;
    }

    public String getCode () {
        return this.account_number;
    }

    public void loadMoney (double amount) {
      if(amount >= 0) {
          this.amount += amount;
      }
    }

    public boolean withdrawMoney (double amount) {
        if(this.isWithdrawable(amount)) {
            this.amount -= amount;
            return true;
        }
        return false;
    }

    public boolean isWithdrawable(double amount) {
        return this.amount - amount > 0;
    }

    public boolean addInsurance (Insurance insurance) {
        return addInsurance(insurance.getType(), insurance.getMonthlyCost(), insurance.getDuration());
    }

    public boolean addInsurance(INSURANCE_TYPE insurance_type, double monthly_cost, int duration) {
        if(active_insurances >= 5) {
            return false;
        }

        // Non ci possono essere più assicurazioni dello stesso tipo
        for(int i=0; i<active_insurances; i++) {
            if(insurance_type == insurances[i].getType()) {
                return false;
            }
        }

        Insurance new_insurance = new Insurance(insurance_type, monthly_cost, duration);
        insurances[active_insurances] = new_insurance;
        this.active_insurances++;
        return true;
    }

    public boolean removeInsuranceById (final int id) {
        int i = 0;
        for(; i<active_insurances; i++) {
            if(insurances[i].getId() == id) {
                break;
            }
        }
        return this.removeInsurance(i);
    }

    public boolean removeInsurance (int index) {
        if (index < 0 || index >= 5 || index > active_insurances-1) {
            return false;
        }

        insurances[active_insurances-1].closeInsurance();

        for(; index<active_insurances; index++) {
            insurances[index] = insurances[index+1];
        }

        insurances[active_insurances] = null;
        active_insurances--;
        return true;
    }

    public String printInsurances () {
        String s = "";
        for(int i=0; i<active_insurances; i++) {
            s += insurances[i].toString();
        }
        return s;
    }

    @Override
    public String toString() {
        return "Account_Holder{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", account_number='" + account_number + '\'' +
                ", amount=" + amount +
                ", insurances=" + printInsurances() +
                ", active_insurances=" + active_insurances +
                '}';
    }
}
