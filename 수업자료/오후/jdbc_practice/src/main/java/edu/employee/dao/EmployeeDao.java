package edu.employee.dao;

import edu.employee.vo.EmployeeVO;

import java.util.List;

public interface EmployeeDao {

    // 1. 부서 직원 조회
    List<EmployeeVO> getDepartmentEmployees(String deptTitle);

    // 2. 부서·직급별 평균 급여 조회
    List<EmployeeVO> getDepartmentAvgSalary();

    // 3. 재직 중인 직원 목록 조회
    List<EmployeeVO> getWorkingEmployees();

    // 4. 부서 급여 10% 인상
    int increaseSalary(String deptCode);

    // 5. 휴대폰 번호 없는 직원 조회
    List<EmployeeVO> getEmployeesWithoutPhone();
}