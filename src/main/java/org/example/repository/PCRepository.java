package org.example.repository;

import org.example.entity.PC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PCRepository {
    private static final String GET_PC_BY_EMPLOYEE_ID_QUERY = "SELECT * FROM pc WHERE employee_id = ?";
    private static final String GET_ALL_PC_QUERY = "SELECT * FROM pc";
    private static final String ADD_PC_QUERY = "INSERT INTO pc (model, employee_id) VALUES (?, ?)";
    private static final String DELETE_PC_QUERY = "DELETE FROM pc WHERE id = ?";
    private static final String UPDATE_PC_QUERY = "UPDATE pc SET model = ?, employee_id = ? WHERE id = ?";


    private final Connection connection;

    public PCRepository(Connection connection) {
        this.connection = connection;
    }

    public PC getPCByEmployeeId(long employeeId) throws SQLException {
        PC pc = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_PC_BY_EMPLOYEE_ID_QUERY)) {
            ps.setLong(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pc = new PC();
                    pc.setId(rs.getLong("id"));
                    pc.setModel(rs.getString("model"));
                    pc.setEmployeeId(rs.getLong("employee_id"));
                }
            }
        }
        return pc;
    }

    public List<PC> getAllPCs() throws SQLException {
        List<PC> pcs = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(GET_ALL_PC_QUERY);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                PC pc = new PC();
                pc.setId(rs.getLong("id"));
                pc.setModel(rs.getString("model"));
                pc.setEmployeeId(rs.getLong("employee_id"));
                pcs.add(pc);
            }
        }
        return pcs;
    }

    public void addPC(PC pc) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(ADD_PC_QUERY)) {
            ps.setString(1, pc.getModel());
            ps.setLong(2, pc.getEmployeeId());
            ps.executeUpdate();
        }
    }

    public void deletePC(long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PC_QUERY)) {
            ps.setLong(1, id);
            int updatedStrings = ps.executeUpdate();
            if (updatedStrings < 1){
                throw new SQLException();
            }
        }
    }

    public void updatePC(PC pc) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PC_QUERY)) {
            ps.setString(1, pc.getModel());
            ps.setLong(2, pc.getEmployeeId());
            ps.setLong(3, pc.getId());
            ps.executeUpdate();
        }
    }
}
