/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Objetos.UsuarioObj;
import Servicios.GetIdUser;
import Servicios.TokenLogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author janto
 */
public class CheckUserPassword extends HttpServlet {

    DataSource datasource;
    
    //Abre conexión con la base de datos 
    @Override
    public void init() {

        try {
            InitialContext initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("jdbc/restfull");
        } catch (NamingException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        Statement statement = null;
        Connection connection = null;
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        UsuarioObj uo = new UsuarioObj();
        uo.setUser(user);
        uo.setPassword(password);
        GetIdUser id = new GetIdUser();
        TokenLogin tk = new TokenLogin();
        String token = tk.putXml(uo);

        //creo sesion y guardo el dato user para su uso en los servlets posteriores
        HttpSession cliente = request.getSession();
        cliente.setAttribute("user", user);
        cliente.setAttribute("password", password);

        if(!token.isEmpty() && token!=""){
            String idu = id.postXml(uo);
            cliente.setAttribute("idUsuario", idu);
            //meto el token en la sesion para posteriormente usarlo en el menu y pasarselo a mostraragendaservicio
            cliente.setAttribute("Token", token);
            cliente.setAttribute("NombreUser", uo.getUser());
           response.sendRedirect("/ClienteRestFullWeb/Menu");
        }else{
                 response.sendRedirect("/ClienteRestFullWeb/errorUser.html");
            }
    }

    //Cierra conexion con la Base de datos
    @Override
    public void destroy() {
        Statement statement = null;
        Connection connection = null;
        try {

            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
