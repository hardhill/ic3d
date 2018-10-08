package pfr.center.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pfr.center.MainUI;

import java.sql.Date;
import java.util.List;

public class InfocenterDAO implements IRepository{

    private static final String SQL_GET_PROCESSBYDATE = "SELECT ID_DEPART, DATEOFCOMP ,SUM(process_end.SUMM) as SUMM FROM process_end WHERE (ID_DEPART=?)AND(DATEOFCOMP=?)";
    @Autowired
    JdbcTemplate jdbcTemplate;

    final private String SQL_GET_ALL = "SELECT * FROM staff";
    final private String SQL_GET_STAFF = "SELECT * FROM staff WHERE staff.ID_STAFF = ?";
    final private String SQL_GET_ALLDEPART = "SELECT * FROM department ORDER BY department.NAME_REG";

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
    public ProcessEnd getProcesses(int id_depart, Date date) {
        ProcessEnd processEnd = new ProcessEnd();
        jdbcTemplate.query(SQL_GET_PROCESSBYDATE, new Object[]{id_depart,date},(result)->{
            if(result.first()) {
                processEnd.setId_depart(result.getInt("ID_DEPART"));
                processEnd.setDateofcomp(result.getDate("DATEOFCOMP"));
                processEnd.setSumm(result.getInt("SUMM"));
            }
        });
        return processEnd;
    }


}
