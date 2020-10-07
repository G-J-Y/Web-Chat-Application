import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

    private ChatManagerImp chatManager = new ChatManagerImp();
    ArrayList<Message> chatlist;
    List<Message> filteredMessages;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //get username
        String name = request.getParameter("username");
        //get message
        String message = request.getParameter("message");
        //store message
        chatManager.postMessage(name, message);

        chatlist = chatManager.getMessagesStore();

        PrintWriter out = response.getWriter();

        out.println("<HTML>");
        out.println("<body>");
        if(chatlist!=null)
        {
            for(Message msgs:chatlist)
            {
                out.println(msgs.toString());
                out.println("<br>");
            }
        }
        out.println("</body>");
        out.println("</HTML>");

        //request.getServletContext().setAttribute("chatlist", chatlist);
        //response.getWriter().println(message);

        //response.sendRedirect("/chat.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filename = "Chat Record.txt";
        String filePath = getServletContext().getRealPath(filename);

        File file = new File(filePath);

        response.setContentType("text/plain");
        //response.setContentType("text/xml");
        response.setHeader("Content-disposition","attachment; filename=" + filename);

        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        filteredMessages = chatManager.listMessages(null,null);

        int bytes;
        while ((bytes = fileInputStream.read()) != -1) {
            outputStream.write(bytes);
        }

        fileInputStream.close();
        outputStream.close();

        /*int i = 0;
        while (!filteredMessages.isEmpty()) {
            outputStream.write(filteredMessages.indexOf(i));
        }

        outputStream.flush();
        outputStream.close();*/


        /*String format = request.getParameter("format");
        if (format.equals("XML")) {
            response.setContentType("text/xml");
        }*/
    }
}
