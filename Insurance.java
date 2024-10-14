import java.util.Calendar;
import java.util.GregorianCalendar;

public class Insurance {
    private final int id;
    private final INSURANCE_TYPE insurance_type;
    private double monthly_cost;
    private final GregorianCalendar opening_date;
    private boolean active;
    private final int duration;
    private GregorianCalendar expiration_date;

    static int insurances_total;
    static int active_insurances;

    public Insurance (INSURANCE_TYPE insurance_type, double monthly_cost, int duration) {
        this.id = insurances_total++;
        this.insurance_type = insurance_type;
        this.opening_date = new GregorianCalendar();
        this.active = false;
        this.duration = duration;
        this.expiration_date = new GregorianCalendar();
        this.expiration_date.add(Calendar.YEAR, duration);

        active_insurances++;
    }

    public int getId() {
        return this.id;
    }
    public INSURANCE_TYPE getType() {
        return insurance_type;
    }

    public double getMonthlyCost() {
        return monthly_cost;
    }

    public int getDuration() {
        return duration;
    }

    public boolean activateInsurance() {
        if(this.active) {
            return false;
        }

        this.active = true;
        active_insurances++;
        return true;
    }

    public boolean closeInsurance() {
        if(!this.active) {
            return false;
        }
        this.active = false;
        active_insurances--;
        return true;
    }

    @Override
    public String toString() {
        return "Insurance {" +
                "insurance_type=" + insurance_type +
                ", monthly_cost=" + monthly_cost +
                ", opening_date=" + opening_date +
                ", active=" + active +
                ", expiration_date=" + expiration_date +
                '}';
    }
}
