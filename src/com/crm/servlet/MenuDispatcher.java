/**
 * 
 */
package com.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crm.model.Employee;
import com.crm.service.ILoginService;
import com.crm.service.impl.LoginService;

/**
 * @author lenovo
 * 
 */
public class MenuDispatcher extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ILoginService loginService;

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
	super.destroy(); // Just puts "destroy" string in log
	loginService = null;
    }

    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	Employee user = (Employee) request.getSession().getAttribute(
		"currentUser");
	String contentURL = request.getParameter("contentURL");
	if (contentURL != null) {
	    String result = loginService.getRole(user.getEmployeeId());

	    System.out.println(result); // xx部门&false(true)

	    if (result.equals("SaleDepartment&false")) { // 用户为销售部门员工
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_sale.jsp").forward(request,
			response);
	    } else if (result.equals("SaleDepartment&true")) { // 用户为销售部门主管
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_sale_m.jsp").forward(
			request, response);
	    } else if (result.equals("MarketDepartment&false")) {// 用户为市场部门员工
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_market.jsp").forward(
			request, response);
	    } else if (result.equals("MarketDepartment&true")) {// 用户为市场部门主管
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_market_m.jsp").forward(
			request, response);
	    } else if (result.equals("ServiceDepartment&false")) {// 用户为售后部门员工
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_service.jsp").forward(
			request, response);
	    } else if (result.equals("ServiceDepartment&true")) {// 用户为售后部门主管
		request.setAttribute("content", contentURL);
		request.getRequestDispatcher("index_service_m.jsp").forward(
			request, response);
	    } else {
		request.setAttribute("errorMsg", "身份异常，联系管理员");
		request.getRequestDispatcher("login.jsp").forward(request,
			response);
	    }
	}
    }

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException
     *             if an error occurs
     */
    public void init() throws ServletException {
	super.init();
	loginService = new LoginService();
    }

}
