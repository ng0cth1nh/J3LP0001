/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Vu Ngoc Thinh
 */
public class DBContext {

    InitialContext init;
    Context context;
    String dbName, serverName, portNumber, username, password;
    
    public Connection getConnection() {
        Connection con = null;
        try {
            init = new InitialContext();
            context = (Context) init.lookup("java:comp/env");

            serverName = context.lookup("serverName").toString();
            dbName = context.lookup("dbName").toString();
            portNumber = context.lookup("portNumber").toString();
            username = context.lookup("username").toString();
            password = context.lookup("password").toString();

            StringBuilder url = new StringBuilder("jdbc:sqlserver://");
            url.append(serverName);
            url.append(":");
            url.append(portNumber);
            url.append(";databaseName=");
            url.append(dbName);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url.toString(), username, password);
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return con;
    }

}
