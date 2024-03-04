package org.example.servlet;

import org.example.entity.PC;
import org.example.mapper.PCMapper;
import org.example.service.PCService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee_pc/*")
public class EmployeePCServlet extends HttpServlet {
    PCService pcService;

    @Override
    public void init() {
        pcService = (PCService) getServletContext().getAttribute("pcService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
            PC pc = pcService.getPCByEmployeeId(employeeId);
            resp.setContentType("application/json");
            PCMapper.writeJsonResponse(resp, pc);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
            PC pc = PCMapper.readJsonRequest(req);
            pc.setEmployeeId(employeeId);
            pcService.addPC(pc);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
            PC pc = PCMapper.readJsonRequest(req);
            pc.setEmployeeId(employeeId);
            pc.setId(pcService.getPCByEmployeeId(employeeId).getId());
            pcService.updatePC(pc);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException | IOException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
            long pcId = pcService.getPCByEmployeeId(employeeId).getId();
            pcService.deletePC(pcId);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
