package cs;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServlet", value = "/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        response.setContentType("text/html;charset=utf-8");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        File f=new File("D:\\IDEA-WJ\\TempFolder");
        if(!f.exists()){
            f.mkdirs();
        }
        factory.setRepository(f);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("utf-8");
        List<FileItem> fileItems = fileUpload.parseRequest(request);

        PrintWriter writer = response.getWriter();

        for (FileItem fileItem:fileItems) {
            if(fileItem.isFormField()){
                String name = fileItem.getFieldName();
                if(name.equals("name")){
                    if(!fileItem.getString().equals("")){
                        String value = fileItem.getString("utf-8");
                        writer.println("上传者："+ value +"<br />");
                    }
                }
            }
            else{
                String filename = fileItem.getName();
                if(filename != null && !filename.equals("")){
                    writer.println("上传文件名是：" + filename);
                    filename = filename.substring(filename.lastIndexOf("\\")+ 1);
                    filename = UUID.randomUUID().toString()+"_"+filename;
                    String webPath = "/upload/";
                    String filepath = getServletContext().getRealPath(webPath+filename);
                    File file = new File(filepath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    InputStream in = fileItem.getInputStream();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = in.read(buffer)) > 0){
                        out.write(buffer,0,len);
                        in.close();
                        out.close();
                        fileItem.delete();
                        writer.println("上传文件成功！");
                    }
                }
            }
        }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
