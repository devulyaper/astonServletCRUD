package org.example.service;

import org.example.entity.PC;
import org.example.repository.PCRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PCService {

    private final PCRepository pcRepository;

    public PCService(Connection connection) {
        this.pcRepository = new PCRepository(connection);
    }

    public PC getPCByEmployeeId(long employeeId) throws SQLException {
        return pcRepository.getPCByEmployeeId(employeeId);
    }

    public List<PC> getAllPCs() throws SQLException {
        return pcRepository.getAllPCs();
    }

    public void addPC(PC pc) throws SQLException {
        pcRepository.addPC(pc);
    }

    public void updatePC(PC pc) throws SQLException {
        pcRepository.updatePC(pc);
    }

    public void deletePC(long id) throws SQLException {
        pcRepository.deletePC(id);
    }
}

