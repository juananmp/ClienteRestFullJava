/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Objetos.Contacto;
import Objetos.PersonaObj;
import Servicios.ContactoServicio;
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
public class CreateContacto extends HttpServlet {

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
       
            /* TODO output your page here. You may use following sample code. */
           HttpSession cliente = request.getSession();
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        
        System.out.println("nombre--->"+nombre);
        System.out.println("correoe--->"+correo);
        System.out.println("telefono--->"+telefono);
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateContacto</title>");            
            out.println("</head>");
            out.println("<body>");
            if(!nombre.isEmpty() && !correo.isEmpty() && !telefono.isEmpty()){
            PersonaObj p = new PersonaObj();
            p.setName(nombre);
            p.setEmail(correo);
            int tel = Integer.parseInt(telefono);
            p.setTelephone(tel);
            Contacto c = new Contacto();
            c.setPo(p);
            System.out.println("En pA name-->"+c.getPo().getName());
            System.out.println("idAgenda-->"+cliente.getAttribute("idAgenda"));
            int idAgenda = Integer.parseInt((String)cliente.getAttribute("idAgenda"));
            c.setId_agenda(idAgenda);
            ContactoServicio cs = new ContactoServicio();
            String token = (String) cliente.getAttribute("Token");
            System.out.println("Token-->"+cliente.getAttribute("Token"));
            cs.insertarPersona(c,token);
            out.print("Contacto Creado");
            }else{
                out.print("No guardado");
            }
            out.print("<br>\n" +
"        <form action=\"/ClienteRestFullWeb/MenuAgenda\" method=\"post\">\n" +
"            <input type=\"submit\" value=\"Volver a gestion\">\n" + 
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
