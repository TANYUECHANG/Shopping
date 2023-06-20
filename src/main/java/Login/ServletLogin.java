package Login;


import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String name=request.getParameter("username");
        String pass=request.getParameter("password");
        ConnectionDemo connectionDemo=new ConnectionDemo();
        try {
            if(connectionDemo.select(name,pass)){
                response.sendRedirect("ListCakeServlet");
            }
            else{
                response.sendRedirect("ServletCs");
            }
        } catch (Exception e) {
            System.out.println("登录错误！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }
}
