package pfr.center.models;

import java.sql.Date;

public class ProcessEnd {
    Long id_pr_end;
    int id_depart;
    int id_staff;
    int id_process;
    int id_task;
    Long id_typproc;
    Date dateofcomp;
    int summ;

    public ProcessEnd() {
    }

    public Long getId_pr_end() {
        return id_pr_end;
    }

    public int getId_depart() {
        return id_depart;
    }

    public void setId_depart(int id_depart) {
        this.id_depart = id_depart;
    }

    public void setId_pr_end(Long id_pr_end) {
        this.id_pr_end = id_pr_end;
    }

    public int getId_staff() {
        return id_staff;
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public int getId_process() {
        return id_process;
    }

    public void setId_process(int id_process) {
        this.id_process = id_process;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public Long getId_typproc() {
        return id_typproc;
    }

    public void setId_typproc(Long id_typproc) {
        this.id_typproc = id_typproc;
    }

    public Date getDateofcomp() {
        return dateofcomp;
    }

    public void setDateofcomp(Date dateofcomp) {
        this.dateofcomp = dateofcomp;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }
}
