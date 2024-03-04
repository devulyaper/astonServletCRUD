package org.example.repository;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseConnectionManagerTest {
    @Test
    public void testGetInstance() {
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
        assertNotNull("Instance should not be null", instance1);
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
        assertSame( "Instances should be the same", instance1, instance2);
    }

    @Test
    public void testGetConnection() {
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        assertNotNull("Connection should not be null", connection);
        assertNotNull("Connection should be an instance of Connection", connection);
    }

    @Test
    public void testCloseConnection() {
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        assertNotNull("Connection should not be null", connection);

        try {
            assertTrue("Connection should be closed", connection.isClosed());
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

}
