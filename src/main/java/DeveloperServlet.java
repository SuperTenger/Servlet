import com.sun.javafx.iio.ios.IosDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeveloperServlet", urlPatterns = {ConstantUtil.TEST,ConstantUtil.ALL_DEVELOPERS_URL,
        ConstantUtil.QUERY_DEVELOPER_URL, ConstantUtil.ADD_DEVELOPER_URL, ConstantUtil.UPDATE_DEVELOPER_URL, ConstantUtil.DELETE_DEVELOPER_URL})
public class DeveloperServlet extends HttpServlet {

    DeveloperBussiness developerBussiness = new DeveloperBussiness();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //设置响应内容类型
        resp.setContentType("text/json;charset=UTF-8");
        String uri = req.getRequestURI();
        if (uri.equals(ConstantUtil.WAR_NAME+ConstantUtil.ALL_DEVELOPERS_URL)) {
            getAllDevelopers(req, resp);
        } else if (uri.equals(ConstantUtil.WAR_NAME+ConstantUtil.QUERY_DEVELOPER_URL)) {
            getDeveloper(req, resp);
        } else if (uri.equals(ConstantUtil.WAR_NAME+ConstantUtil.ADD_DEVELOPER_URL)) {
            addDeveloper(req,resp);
        } else if (uri.equals(ConstantUtil.WAR_NAME+ConstantUtil.UPDATE_DEVELOPER_URL)) {
            updateDeveloper(req,resp);
        } else if (uri.equals(ConstantUtil.WAR_NAME+ConstantUtil.DELETE_DEVELOPER_URL)) {
            deleteDeveloper(req,resp);
        }
    }

    private void getAllDevelopers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        List<DeveloperModel> developerList = developerBussiness.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (developerList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerList);
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void getDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String id = request.getParameter("id");
        DeveloperModel developerModel = developerBussiness.getDeveloper(id);
        CommonModel commonModel = new CommonModel();
        if (developerModel == null) {
            commonModel.setFail();
        } else {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void addDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String name = request.getParameter("name");
        String site = request.getParameter("site");
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);

        CommonModel commonModel = new CommonModel();
        if(developerBussiness.addDeveloper(developerModel)){
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void updateDeveloper(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        PrintWriter printWriter = response.getWriter();
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        CommonModel commonModel = new CommonModel();
        if(developerBussiness.updateDeveloper(id,name)){
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void deleteDeveloper(HttpServletRequest request,HttpServletResponse  response)throws ServletException,IOException{
        PrintWriter printWriter = response.getWriter();
        String id = request.getParameter("id");

        CommonModel commonModel = new CommonModel();
        if(developerBussiness.deleteDeveloper(id)){
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }

        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

}
