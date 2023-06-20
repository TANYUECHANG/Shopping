package cs;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */

@WebFilter(filterName = "SensitiveFilter" , urlPatterns="/TestServlet")//过滤对象
public class SensitiveWordsFilter implements Filter {
    private List<String> list = new ArrayList<String>();//存放敏感词汇的集合
    public void init(FilterConfig config) throws ServletException {
        try {
//        1.获取文件路径
            ServletContext servletContext = config.getServletContext();
//        将敏感词汇放在src下 和 target的classes下
// 设置为gbk编码，后面的本地生成的流也是GBK，防止乱码
            String realPath = servletContext.getRealPath("/WEB-INF/敏感词汇.txt");
//        2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
//        将文件的每一行数据添加到list中
            String line = null;
            while ((line= br.readLine())!=null){
                list.add(line);
            }
//            关闭
            br.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//      1.创建 (request) 的代理对象，增强getParameter方法
//        request 为ServletRequest类型 ，将代理对象强转为ServletRequest类型
        ServletRequest proxy_req= (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                增强getParameter
//                判断是否为 getParameter 方法
//                在方法中判断是否是getParameter方法后，如果是则获取method方法中反射过来的值并遍历处理，将获取值中与敏感词汇相同的字符替换成“***”，然后将增强后的值返回出来。
                if (method.getName().equals("getParameter")){
//                    增强返回值
//                    获取返回值，使用真实对象调用
                    String value = (String) method.invoke(request, args);
                    if (value != null ){
                        for (String str : list) {
                            if (value.contains(str)){
//                                value = 为替换后的
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }

                return method.invoke(request,args);
            }
        });
        //        放行
        chain.doFilter(proxy_req, response);
    }
}
