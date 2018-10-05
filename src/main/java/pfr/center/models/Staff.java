package pfr.center.models;

import java.sql.Date;

public class Staff {
    private Long id_staff;
    private Long id_depart;
    private Long id_otdel;
    private Long id_post;
    private String tabel;
    private String fa;
    private String im;
    private String ot;
    private Date date_begin;
    private Date date_end;
    private String password;
    private int active;

    public Staff() {
    }

    public Staff(Long id_staff, Long id_depart, Long id_otdel, Long id_post, String tabel, String fa,
                 String im, String ot, Date date_begin, Date date_end, String password, int active) {
        this.id_staff = id_staff;
        this.id_depart = id_depart;
        this.id_otdel = id_otdel;
        this.id_post = id_post;
        this.tabel = tabel;
        this.fa = fa;
        this.im = im;
        this.ot = ot;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.password = password;
        this.active = active;
    }

    public Long getId_staff() {
        return id_staff;
    }

    public void setId_staff(Long id_staff) {
        this.id_staff = id_staff;
    }

    public Long getId_depart() {
        return id_depart;
    }

    public void setId_depart(Long id_depart) {
        this.id_depart = id_depart;
    }

    public Long getId_otdel() {
        return id_otdel;
    }

    public void setId_otdel(Long id_otdel) {
        this.id_otdel = id_otdel;
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public String getTabel() {
        return tabel;
    }

    public void setTabel(String tabel) {
        this.tabel = tabel;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
