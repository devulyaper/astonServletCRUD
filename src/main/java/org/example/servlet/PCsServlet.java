package org.example.servlet;

import org.example.entity.PC;
import org.example.mapper.PCMapper;
import org.example.service.PCService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pcs")
public class PCsServlet extends HttpServlet {
    PCService pcService;
    @Override
    public void init() throws ServletException {
        pcService = (PCService) getServletContext().getAttribute("pcService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            resp.setContentType("application/json");
            List<PC> pcList = pcService.getAllPCs();
            PCMapper.writeJsonResponse(resp, pcList);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
