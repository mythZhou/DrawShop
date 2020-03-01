package com.imooc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadUtils {

	/**
	 * ����Ψһ���ļ���:
	 */
	public static String getUUIDFileName(String fileName){
		//���ļ�����ǰ�沿�ֽ��н�ȡ��xx.jpg---> .jpg
		int idx = fileName.lastIndexOf(".");
		String extention = fileName.substring(idx);
		String uuidFileName = UUID.randomUUID().toString().replace("-", "")+extention;
		return uuidFileName;
	}
	
	public static Map uploadFile(HttpServletRequest request) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		// 1.����һ�������ļ��������
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.����һ�����Ľ�����
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3.����request���󣬷��ص���List���ϣ�List�����д�ŵ���FileItem����
		String url = null;
		String uuidFileName= null;
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					// �����ļ��ϴ�
					// ���ձ��������ֵ:
					String name = fileItem.getFieldName(); // ��ñ����name���Ե�ֵ
					String value = fileItem.getString("UTF-8");// ��ñ����ֵ
					// ���뼯��
					map.put(name, value);
				}else{
					// �ļ��ϴ�
					// �ļ��ϴ���:
					// �ļ��ϴ�����
					// ����ļ��ϴ������ƣ�
					String fileName = fileItem.getName();
					System.out.println("filename="+fileName);
					if(fileName !=null && !"".equals(fileName)){
						//ͨ����������Ψһ�ļ���:
						uuidFileName = UploadUtils.getUUIDFileName(fileName);
						//����ļ��ϴ������ݣ�
						InputStream is = fileItem.getInputStream();
						//����ļ��ϴ���·��:
						String path = request.getServletContext().getRealPath("/upload");
						// ���������Խӵ�������Ϳ�����:
						url = path+"\\"+uuidFileName;
						OutputStream os = new FileOutputStream(url);
						int len = 0;
						byte[] b = new byte[1024];
						while((len = is.read(b))!=-1){
							os.write(b, 0, len);
						}
						is.close();
						os.close();
						
						map.put("path", request.getContextPath()+"/upload/"+uuidFileName);
						map.put("filename", fileName);
					}
					System.out.println(map);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUIDFileName("1.jpg"));
	}
}
