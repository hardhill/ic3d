package pfr.center.models;

import java.sql.Date;
import java.util.List;

public interface IRepository {
    List<Staff> getAllStaff();
    Staff getStaffbyId(Long id);
    List<Department> getAllDepartment();
    ProcessEnd getProcesses(int id_depart, Date date);
    //ProcessEnd getListProcess(int id_depart, Date date);
}
