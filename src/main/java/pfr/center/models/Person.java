package pfr.center.models;

public class Person {
    private Long id;
    private String fa;
    private String im;
    private String ot;
    private String dolzhnost;
    private int pens1;
    private int pens2;
    private int pens3;
    private int pens4;
    private int pens5;
    private int pens6;

    public Person(Long id, String fa, String im, String ot, String dolzhnost, int pens1, int pens2, int pens3, int pens4, int pens5, int pens6) {
        this.id = id;
        this.fa = fa;
        this.im = im;
        this.ot = ot;
        this.dolzhnost = dolzhnost;
        this.pens1 = pens1;
        this.pens2 = pens2;
        this.pens3 = pens3;
        this.pens4 = pens4;
        this.pens5 = pens5;
        this.pens6 = pens6;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDolzhnost() {
        return dolzhnost;
    }

    public void setDolzhnost(String dolzhnost) {
        this.dolzhnost = dolzhnost;
    }

    public int getPens1() {
        return pens1;
    }

    public void setPens1(int pens1) {
        this.pens1 = pens1;
    }

    public int getPens2() {
        return pens2;
    }

    public void setPens2(int pens2) {
        this.pens2 = pens2;
    }

    public int getPens3() {
        return pens3;
    }

    public void setPens3(int pens3) {
        this.pens3 = pens3;
    }

    public int getPens4() {
        return pens4;
    }

    public void setPens4(int pens4) {
        this.pens4 = pens4;
    }

    public int getPens5() {
        return pens5;
    }

    public void setPens5(int pens5) {
        this.pens5 = pens5;
    }

    public int getPens6() {
        return pens6;
    }

    public void setPens6(int pens6) {
        this.pens6 = pens6;
    }
}
