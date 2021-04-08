/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.restaurante.servidor.acces;

import co.unicauca.restaurante.comunicacion.domain.DayMenu;
import co.unicauca.restaurante.comunicacion.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class DayMenuRepositoryImplMysql implements IDayMenuRepository{
    
    public Connection conn;
    
    //<editor-fold defaultstate="collapsed" desc="Metodos de conexion">
    /**
     * Método encargado de realizar la conexión a la base de datos
     * @return 1 si la conexión fue exitosa, -1 de lo contrario
     */
    public int connect() {
        try {
            Class.forName(Utilities.loadProperty("server.db.driver"));
            //crea una instancia de la controlador de la base de datos
            String url = Utilities.loadProperty("server.db.url");
            String username = Utilities.loadProperty("server.db.username");
            String pwd = Utilities.loadProperty("server.db.password");
            conn = DriverManager.getConnection(url, username, pwd);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DayMenuRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar la tabla Dish de la base de datos", ex);
        }
        return -1;
    }
    
    /**
     * Metodo encargado de desconectar la aplicacion de la base de datos.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DayMenuRepositoryImplMysql.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }
//</editor-fold>

    @Override
    public String createDayMenu(DayMenu prmObjMenuDay) {
        try {
            this.connect();
            String sql = "INSERT INTO DayMenu (dmenID, resID, dmenDay) "
                    + "VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjMenuDay.getDmenID());
            pstmt.setString(2, prmObjMenuDay.getResID());
            pstmt.setString(3, prmObjMenuDay.getDmenDay());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IDayMenuRepository.class.getName()).log(Level.SEVERE, "Error al insertar el Menu", ex);
        }
        return Integer.toString(prmObjMenuDay.getDmenID());
    }

    @Override
    public DayMenu findDayMenu(int prmdmenID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateDayMenu(int prmdmenID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteDayMenu(int prmdemID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
