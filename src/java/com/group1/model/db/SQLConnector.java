package com.group1.model.db;

import com.group1.misc.Sout;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 *
 * @author Anh Vu Nguyen {@literal <nganhvu>}
 */
public class SQLConnector {
    public static Connection SQL;
    
    public static void establishConnection() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123456");
        ds.setDatabaseName("Javbook");
//        ds.setPortNumber(1433);
        try {
            SQL = ds.getConnection();
            DatabaseMetaData dm = SQL.getMetaData();
            Sout pt = new Sout("SQL Database");
            pt.add("Driver name:", dm.getDriverName());
            pt.add("Driver version:", dm.getDriverVersion());
            pt.add("Product name:", dm.getDatabaseProductName());
            pt.add("Product version:", dm.getDatabaseProductVersion());
            pt.addf("JDBC version: %d.%d", dm.getJDBCMajorVersion(), dm.getJDBCMinorVersion());
            pt.print();
        } catch (Exception ex) {
            System.out.println("SQLConnector connect error!");
            ex.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        try {
            SQL.close();
            Sout pt = new Sout("SQL Database");
            pt.add("SQLConnector closed!");
            pt.print();
        } catch (SQLException ex) {
            System.out.println("SQLConnector close error!");
            ex.printStackTrace();
        }
    }
}
