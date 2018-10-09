package pfr.center.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import pfr.center.MainUI;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InfocenterDAO implements IRepository{

    private static final String SQL_GET_PROCESSBYDATE = "SELECT ID_DEPART, COUNT(process_end.ID_DEPART) as SUMM " +
            "FROM process_end WHERE (ID_DEPART=?)AND(DATE(DATEOFCOMP)=?) GROUP BY ID_DEPART";
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

//    @Override
//    public ProcessEnd getProcesses(int id_depart, Date date) {
//        ProcessEnd processEnd = new ProcessEnd();
//        jdbcTemplate.query(SQL_GET_PROCESSBYDATE, new Object[]{id_depart,date.toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE)},
//                (result)->{
//            if(result.first()) {
//                processEnd.setId_depart(result.getInt("ID_DEPART"));
//                processEnd.setSumm(result.getInt("SUMM"));
//            }
//        });
//        return processEnd;
//    }

    @Override
    public ProcessEnd getProcesses(int id_depart, Date date) {
        ProcessEnd processEnd = new ProcessEnd();
        SqlRowSet result=jdbcTemplate.queryForRowSet(SQL_GET_PROCESSBYDATE,new Object[]{id_depart,date});
        while (result.next()){
            processEnd.setId_depart(result.getInt("ID_DEPART"));
            processEnd.setSumm(result.getInt("SUMM"));
        }
        return processEnd;
    }


}
