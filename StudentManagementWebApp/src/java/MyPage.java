
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentmanagementappdb.Student;
import studentmanagementappdb.StudentDatabase;

/**
 *
 * @author Hp
 */
@WebServlet(urlPatterns = {"/MyPage"})
public class MyPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        name = name == null ? "" : name;
        String surname = request.getParameter("surname");
        surname = surname == null ? "" : surname;
        String ageStr = request.getParameter("age");
        ageStr = ageStr == null ? "" : ageStr;
        Integer age = null;
        if (ageStr != null && !ageStr.isEmpty()) {
            age = Integer.parseInt(ageStr);
        }
        List<Student> students = StudentDatabase.getAll(name, surname, age);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(students);
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

}
