package ru.sbt.bit.ood.solid.homework;

import ru.sbt.bit.ood.solid.homework.builder.ReportBuilder;
import ru.sbt.bit.ood.solid.homework.dao.SalaryPaymentDao;
import ru.sbt.bit.ood.solid.homework.model.SalaryPayment;
import ru.sbt.bit.ood.solid.homework.sender.Sender;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SalaryHtmlReportNotifier {

    private SalaryPaymentDao salaryPaymentDao;
    private ReportBuilder reportBuilder;
    private Sender sender;

    public SalaryHtmlReportNotifier(Connection databaseConnection, SalaryPaymentDao salaryPaymentDao, ReportBuilder reportBuilder, Sender sender) {
        this.salaryPaymentDao = salaryPaymentDao;
        this.reportBuilder = reportBuilder;
        this.sender = sender;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, Date dateFrom, Date dateTo, String recipients) {
        List<SalaryPayment> salaryPayments = salaryPaymentDao.salaryPaymentGetByParam(departmentId, dateFrom, dateTo);
        StringBuilder report = reportBuilder.build(salaryPayments);
        sender.send(recipients, report);

    }

}
