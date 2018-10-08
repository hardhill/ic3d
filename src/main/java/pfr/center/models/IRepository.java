package pfr.center.models;

import java.util.List;

public interface IRepository {
    List<Staff> getAllStaff();
    Staff getStaffbyId(Long id);
    List<Department> getAllDepartment();

}
