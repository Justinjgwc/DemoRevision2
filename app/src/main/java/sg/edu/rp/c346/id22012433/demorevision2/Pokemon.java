package sg.edu.rp.c346.id22012433.demorevision2;

public class Pokemon {//POJO
    private int id;
    private String type;
    private double power;

    public Pokemon(int id, String type, double power) {
        this.id = id;
        this.type = type;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", power=" + power +
                '}';
    }



}
