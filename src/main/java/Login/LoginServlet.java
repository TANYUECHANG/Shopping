package Login;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.Publisher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String name=request.getParameter("username");
        String pass=request.getParameter("password");
        Publisher publisher=new Publisher();
        String send="登入用户名："+name;
        try {
            publisher.testSendMessage(send);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ConnectionDemo connectionDemo=new ConnectionDemo();
        PrintWriter out=response.getWriter();
        try {
            System.out.println(name+" "+pass);
            if(connectionDemo.select(name,pass)){
                response.sendRedirect("http://localhost:81/Home/");
            }
            else{
                out.println("<html><body>");
                out.println("<script>"+"alert('你好，我是一个警告框！')"+"</script>");
                out.println("</body></html>");
                response.sendRedirect("http://localhost:81/Login/");
            }
        } catch (Exception e) {
            out.println("登录错误！");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
