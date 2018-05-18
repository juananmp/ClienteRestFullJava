/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Objetos.MostrarAgenda;
import Servicios.MostrarAgendaServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author janto
 */
public class Menu extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession cliente = request.getSession();
            
         
           // mas.getXml(MostrarAgendaServicio.class);
            
                     MostrarAgenda ma = new MostrarAgenda();
            MostrarAgendaServicio mas = new MostrarAgendaServicio();
            //le paso la clase y el token 
            ma = mas.getXml(MostrarAgenda.class, (String)cliente.getAttribute("Token"));
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Menu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Prueba del LOGIN</h1>");

              
            System.out.println("------------------>"+ma);
            System.out.println(mas);
            System.out.println(ma.getAgenda().toString());
            out.println("<h1>Listado de las agendas</h1>");
            Iterator<Map.Entry<String, Integer>> entries = ma.getAgenda().entrySet().iterator();
                while (entries.hasNext()) {
                    
                    Map.Entry<String, Integer> entry = entries.next();
                    out.println("<form method=\"post\" action=\"/ClienteRestFullWeb/MenuAgenda\">\n"
                            + "<input type=\"hidden\" name=\"idAgenda\" value=\"" + entry.getValue() + "\">"
                            + "<input type=\"submit\" value=\"" + entry.getKey() + "\" >\n"
                            + "</form><br>");
                    System.out.println(entry.getValue() + "entry value---------");
                }
                 out.println("<form action=\"/ClienteRestFullWeb/CrearAgenda\" method=\"get\">\n"
                    + "  Nombre nueva agenda: <input type=\"text\" name=\"nuevaAgenda\"><br>\n"
                    + "  <input type=\"submit\" value=\"Crear\">\n"
                    + "</form>");
            out.println("<h1>Servlet Menu at " + request.getContextPath() + "</h1>");
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
