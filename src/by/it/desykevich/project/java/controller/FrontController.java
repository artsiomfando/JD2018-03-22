package by.it.desykevich.project.java.controller;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController  extends HttpServlet{

    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String viewPage;
        try {
            CmdAbstract cmd = actionFactory.defineCmd(req);
            CmdAbstract next = cmd.execute(req);
            if (next == null) {
                viewPage = cmd.getJsp();
                getServletContext().getRequestDispatcher(viewPage).forward(req, resp);
            } else {
                resp.sendRedirect("do?command="+next.toString());
            }
        } catch (Exception e) {
            req.setAttribute("err",e.getMessage());
            req.setAttribute("log",e);
            getServletContext().getRequestDispatcher(Actions.ERROR.command.getJsp()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String viewPage;
        try {
            CmdAbstract cmd = actionFactory.defineCmd(req);
            CmdAbstract next = cmd.execute(req);
            if (next == null) {
                viewPage = cmd.getJsp();
                getServletContext().getRequestDispatcher(viewPage).forward(req, resp);
            } else {
                resp.sendRedirect("do?command="+next.toString());
            }
        } catch (Exception e) {
            req.setAttribute("err",e.getMessage());
            req.setAttribute("log",e);
            getServletContext().getRequestDispatcher(Actions.ERROR.command.getJsp()).forward(req, resp);
        }
    }

}
