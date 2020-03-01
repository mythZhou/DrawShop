package com.imooc.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.Category;
import com.imooc.service.CategoryService;
import com.imooc.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����·��
		String methodName=request.getParameter("method");
		if("findAll".equals(methodName)) {
			//��ѯ���з���
			findAll(request,response);
		}else if("saveUI".equals(methodName)) {
			//��ת�����ҳ��
			saveUI(request,response);
		}else if("save".equals(methodName)) {
			//�������ķ���
			save(request,response);
		}else if("edit".equals(methodName)) {
			//�༭����ķ���
			edit(request,response);
		}else if("update".equals(methodName)) {
			update(request,response);
		}else if("delete".equals(methodName)) {
			//ɾ������ķ���
			delete(request,response);
		}
	}
	/**
	 * ��̨�������ɾ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//��������
		Integer cid=Integer.parseInt(request.getParameter("cid"));
		//��������
		CategoryService categoryService=new CategoryServiceImpl();
		categoryService.delete(cid);
		response.sendRedirect("/shop/CategoryServlet?method=findAll");
	}

	/**
	 * ��̨��������޸ķ��෽��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//1.��������
		Integer cid=Integer.parseInt(request.getParameter("cid"));
		String cname=request.getParameter("cname");
		String cdesc=request.getParameter("cdesc");
		Category category=new Category();
		category.setCid(cid);
		category.setCname(cname);
		category.setCdesc(cdesc);
		//����ҵ��㴦������
		CategoryService categoryService=new CategoryServiceImpl();
		categoryService.update(category);
		//ҳ����ת
		response.sendRedirect("/shop/CategoryServlet?method=findAll");
	}

	/**
	 * ��̨�������༭����ķ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.��������
		Integer cid=Integer.parseInt(request.getParameter("cid"));
		//2.����ҵ��㴦������
		CategoryService categoryService=new CategoryServiceImpl();
		Category category= categoryService.findOne(cid);
		//3.ҳ����ת
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/category_update.jsp").forward(request, response);
	}

	/**
	 * ��̨������������ķ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//1.��������
		String cname=request.getParameter("cname");
		String cdesc=request.getParameter("cdesc");
		System.out.println(cname+" "+cdesc);
		//2.��װ����
		Category category=new Category();
		category.setCname(cname);
		category.setCdesc(cdesc);
		//3.����ҵ��㴦������
		CategoryService categoryService=new CategoryServiceImpl();
		categoryService.save(category);
		//4.ҳ����ת
		response.sendRedirect("/shop/CategoryServlet?method=findAll");
	}

	/**
	 * ��̨���������ת�����ҳ��
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/admin/category_add.jsp").forward(request, response);
	}

	/**
	 * ��̨������ѯ���з���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���ղ���
		//��װ����
		//����ҵ��㴦������
		System.out.println("CategoryServlet��findAll����ִ���ˡ�����");
		CategoryService categoryService=new CategoryServiceImpl();
		List<Category> list=categoryService.findAll();
		//ҳ����ת
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/category_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
