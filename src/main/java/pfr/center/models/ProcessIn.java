package pfr.center.models;

import java.sql.Date;

public class ProcessIn {
    private Long id_task;
    private Integer id_depart;
    private Long id_process;
    private Integer id_staff;
    private String id_user;
    private Integer id_stage_to;
    private Integer id_stage_from;
    private Long id_typeproc;
    private Date dateofcommit;
    private Date dateoftake;
    private Date dateofcomplite;
    private Integer transact;
    private Integer summ;

    public ProcessIn() {
        id_depart = null;
        summ = 0;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

    public Integer getId_depart() {
        return id_depart;
    }

    public void setId_depart(Integer id_depart) {
        this.id_depart = id_depart;
    }

    public Long getId_process() {
        return id_process;
    }

    public void setId_process(Long id_process) {
        this.id_process = id_process;
    }

    public Integer getId_staff() {
        return id_staff;
    }

    public void setId_staff(Integer id_staff) {
        this.id_staff = id_staff;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Integer getId_stage_to() {
        return id_stage_to;
    }

    public void setId_stage_to(Integer id_stage_to) {
        this.id_stage_to = id_stage_to;
    }

    public Integer getId_stage_from() {
        return id_stage_from;
    }

    public void setId_stage_from(Integer id_stage_from) {
        this.id_stage_from = id_stage_from;
    }

    public Long getId_typeproc() {
        return id_typeproc;
    }

    public void setId_typeproc(Long id_typeproc) {
        this.id_typeproc = id_typeproc;
    }

    public Date getDateofcommit() {
        return dateofcommit;
    }

    public void setDateofcommit(Date dateofcommit) {
        this.dateofcommit = dateofcommit;
    }

    public Date getDateoftake() {
        return dateoftake;
    }

    public void setDateoftake(Date dateoftake) {
        this.dateoftake = dateoftake;
    }

    public Date getDateofcomplite() {
        return dateofcomplite;
    }

    public void setDateofcomplite(Date dateofcomplite) {
        this.dateofcomplite = dateofcomplite;
    }

    public Integer getTransact() {
        return transact;
    }

    public void setTransact(Integer transact) {
        this.transact = transact;
    }

    public Integer getSumm() {
        return summ;
    }

    public void setSumm(Integer summ) {
        this.summ = summ;
    }

    public boolean isEmpty() {
        return (id_depart == null) && (summ == 0);
    }
}
