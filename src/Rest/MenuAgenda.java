/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author janto
 */
public class MenuAgenda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               response.setContentType("text/html;charset=UTF-8");
        String idagenda = request.getParameter("idAgenda");
        HttpSession cliente = request.getSession();
        
        if(idagenda!=null){
             System.out.println("-----------------dentro if  "+idagenda);
           cliente.setAttribute("idAgenda", idagenda); 
        }
        System.out.println("----------------- ID AG en MenuAgenda"+idagenda);
       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Gestion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu:</h1>\n"
                    + "        <form action=\"/ClienteRestFullWeb/InsertarContacto\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"1. Insertar Contacto\">\n"
                    + "        </form>\n"
                    + "        <br>\n"
                    + "        <form action=\"/ClienteRestFullWeb/ValidarAgenda\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"2. Validar Agenda\">\n"
                    + "        </form>\n"
                    + "        <br>\n"
                    + "        <form action=\"/ClienteRestFullWeb/RecogerContacto\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"3. Validar Persona \">\n"
                    + "        </form>\n"
                    + "        <br>\n"
                    + "        <form action=\"/ClienteRestFullWeb/DevolverAgenda\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"4. Devolver Agenda\">\n"
                    + "        </form>\n"
                    + "        <br>\n"
                    + "        <form action=\"/ClienteRestFullWeb/PedirContacto\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"5. Devolver Contacto\">\n"
                    + "        </form>"
                    + "        <form action=\"/ClienteRestFullWeb/PasarNombre\" method=\"post\">\n"
                    + "            <input type=\"submit\" value=\"6. Borrar Contacto\">\n"
                    + "        </form>");
            out.print("<br>\n"
                    +
"        <form action=\"/ClienteRestFullWeb/Menu\" method=\"post\">\n" +
"            <input type=\"submit\" value=\"Volver a lista Agendas\">\n" + 
"        </form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
