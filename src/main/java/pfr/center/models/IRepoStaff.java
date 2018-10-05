package pfr.center.models;

import javax.sql.DataSource;
import java.util.List;

public interface IRepoStaff {

    List<Staff> getAllStaff();
    Staff getStaffbyId(Long id);
}
