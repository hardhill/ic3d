package pfr.center.models;

public class Department {
    Long id_depart;
    String name_dep;
    String num_reg;
    String name_reg;
    String info;


    public Department() {

    }


    public Long getId_depart() {
        return id_depart;
    }

    public void setId_depart(Long id_depart) {
        this.id_depart = id_depart;
    }

    public String getName_dep() {
        return name_dep;
    }

    public void setName_dep(String name_dep) {
        this.name_dep = name_dep;
    }

    public String getNum_reg() {
        return num_reg;
    }

    public void setNum_reg(String num_reg) {
        this.num_reg = num_reg;
    }

    public String getName_reg() {
        return name_reg;
    }

    public void setName_reg(String name_reg) {
        this.name_reg = name_reg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
