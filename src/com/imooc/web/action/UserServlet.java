package com.imooc.web.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.User;
import com.imooc.service.UserService;
import com.imooc.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ܲ���
		String methodName=request.getParameter("method");
		//�ж�
		if("login".equals(methodName)) {
			login(request,response);
		}else if("logout".equals(methodName)) {
			logout(request,response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//1.��session����
		request.getSession().invalidate();
		//2.ҳ�������ת����ת����¼ҳ��
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����û���������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//System.out.println(username+" "+password);
		//���ݵķ�װ��
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		//��������
		UserService userService=new UserServiceImpl();
		User existUser=userService.login(user);
		//���ݴ����������ҳ����ת
		if(existUser==null) {
			//��¼ʧ��
			//���ص���¼ҳ
			request.setAttribute("msg", "�û������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			//��¼�ɹ�
			//���û���Ϣ���б��棬����ҳ����ת
			request.getSession().setAttribute("existUser", existUser);
			response.sendRedirect("/shop/CategoryServlet?method=findAll");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
