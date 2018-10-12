package pfr.center.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import pfr.center.MainUI;

import java.sql.Date;
import java.util.List;

public class InfocenterDAO implements IRepository{

    private static final String SQL_GET_PROCESSBYDATE = "select pr.id_depart as DEP, count(pr.summ) as SUMM " +
            "from (select ID_DEPART, count(ID_PROCESS) as SUMM FROM process_task WHERE (ID_DEPART=?)" +
            "AND(DATE(DATEOFCOMP)=?) group by ID_PROCESS) as pr group by pr.id_depart";
    private static final String SQL_GET_OSTATBYDATE = "select pr.id_depart as DEP, count(pr.summ) as SUMM " +
            "from (select ID_DEPART, count(ID_PROCESS) as SUMM FROM process_task WHERE (ID_DEPART=?)" +
            "AND((DATE(DATEOFCOMP)>?)OR(DATEOFCOMP IS null)) group by ID_PROCESS) as pr group by pr.id_depart";
    private static final String SQL_GET_NEWPROCESSBYDATE = "select pr.id_depart as DEP, count(pr.summ) as SUMM " +
            "from (select ID_DEPART, count(ID_PROCESS) as SUMM FROM process_task WHERE (ID_DEPART=?)" +
            "AND(DATE(DATEOFCOMM)=?) group by ID_PROCESS) as pr group by pr.id_depart";

    @Autowired
    JdbcTemplate jdbcTemplate;

    final private String SQL_GET_ALL = "SELECT * FROM staff";
    final private String SQL_GET_STAFF = "SELECT * FROM staff WHERE staff.ID_STAFF = ?";
    final private String SQL_GET_ALLDEPART = "SELECT * FROM department ORDER BY department.ID_DEPART";

    public InfocenterDAO() {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(MainUI.getDataSource());
    }

    @Override
    public List<Staff> getAllStaff() {
        return jdbcTemplate.query(SQL_GET_ALL, new StaffMapper());
    }

    @Override
    public Staff getStaffbyId(Long id) {
        return (Staff) jdbcTemplate.query(SQL_GET_STAFF,new Object[] { id }, new StaffMapper());
    }

    @Override
    public List<Department> getAllDepartment() {
        return jdbcTemplate.query(SQL_GET_ALLDEPART, new DepartmentMapper());
    }

    @Override
    public ProcessOne getProcessCompl(int id_depart, Date date) {
        ProcessOne processOne = new ProcessOne();
        SqlRowSet result=jdbcTemplate.queryForRowSet(SQL_GET_PROCESSBYDATE,new Object[]{id_depart,date});
        while (result.next()){
            processOne.setId_depart(result.getInt("DEP"));
            processOne.setSumm(result.getInt("SUMM"));
        }
        if (processOne.isEmpty()) {
            processOne.setId_depart(id_depart);
            processOne.setSumm(0);
        }
        return processOne;
    }

    @Override
    public ProcessOne getProcessNew(int id_depart, Date date) {
        ProcessOne processOne = new ProcessOne();
        SqlRowSet result = jdbcTemplate.queryForRowSet(SQL_GET_NEWPROCESSBYDATE, new Object[]{id_depart, date});
        while (result.next()) {
            processOne.setId_depart(result.getInt("DEP"));
            processOne.setSumm(result.getInt("SUMM"));
        }
        if (processOne.isEmpty()) {
            processOne.setId_depart(id_depart);
            processOne.setSumm(0);
        }
        return processOne;
    }

    @Override
    public ProcessOne getOstatok(int id_depart, Date d) {
        ProcessOne processOne = new ProcessOne();
        SqlRowSet result = jdbcTemplate.queryForRowSet(SQL_GET_OSTATBYDATE, new Object[]{id_depart, d});
        while (result.next()) {
            processOne.setId_depart(result.getInt("DEP"));
            processOne.setSumm(result.getInt("SUMM"));
        }
        if (processOne.isEmpty()) {
            processOne.setId_depart(id_depart);
            processOne.setSumm(0);
        }
        return processOne;
    }


}
