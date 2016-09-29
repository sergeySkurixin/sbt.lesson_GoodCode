package ru.sbt.bit.ood.solid.homework.dao;

import ru.sbt.bit.ood.solid.homework.model.SalaryPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SBTJavastudent on 29.09.2016.
 */
public class SalaryPaymentJdbcDao implements SalaryPaymentDao {


    private Connection connection;

    public SalaryPaymentJdbcDao(Connection connection) {
        this.connection = connection;
    }

    public List<SalaryPayment> salaryPaymentGetByParam(String departmentId, Date dateFrom, Date dateTo) {
       // PreparedStatement ps = null;
        try(PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name")){

            List<SalaryPayment> salaryPayments = new ArrayList<SalaryPayment>();
            // inject parameters to sql
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                salaryPayments.add(new SalaryPayment(results))
            }
            return salaryPayments;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
