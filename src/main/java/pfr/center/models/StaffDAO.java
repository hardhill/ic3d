package pfr.center.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pfr.center.MainUI;


import javax.sql.DataSource;
import java.util.List;

@Transactional
@Repository
public class StaffDAO implements IRepoStaff {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final private String SQL_GET_ALL = "SELECT * FROM staff";
    final private String SQL_GET_STAFF = "SELECT * FROM staff WHERE staff.ID_STAFF = ?";


    public StaffDAO() {
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
}
