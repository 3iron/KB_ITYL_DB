package edu.employee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeVO {

    // 사원 정보
    private String empName;
    private String empNo;
    private String phone;
    private int salary;
    private String entYn;

    // 부서 정보
    private String deptCode;
    private String deptTitle;

    // 직급 정보
    private String jobName;

    // 보너스율
    private Double bonus;

    // 통계 조회용
    private int employeeCount;
    private double avgSalary;
}