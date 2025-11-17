package service;

import dao.ReportDAO;

public class ReportService {
    private final ReportDAO reportDAO;

    public ReportService(ReportDAO reportDAO) { this.reportDAO = reportDAO; }

    public double getRevenue(String from, String to) { return reportDAO.sumRevenue(from, to); }
    public double getUtilization(String from, String to) { return reportDAO.utilization(from, to); }
    public double getMaintenanceCost(String from, String to) { return reportDAO.sumMaintenance(from, to); }
}
