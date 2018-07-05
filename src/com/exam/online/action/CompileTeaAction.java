package com.exam.online.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Teacher;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CompileTeaAction extends ActionSupport{
	private static final long serialVersionUID = -8890508519282794267L;
	private String code;
	private String result = "";
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private String classStr;
	private String className;
	@SuppressWarnings("resource")
	public void compileTeaAjax() throws IOException {
		// 判断用户是否为临时用户还是登陆用户
		String name = null;
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		if (teacher != null || !"".equals(teacher)) {
			name = teacher.getName();
		}
		String random = UUID.randomUUID().toString();
		// 确定main方法所在的类名来存储和运行
		if (name == null) {
			System.out.println(code.toString());
			if (code.indexOf("public class") != -1) {
				classStr = code.substring(code.indexOf("public class"),
						code.indexOf("{"));// 获取类名字符串
				String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
				className = classStrArray[classStrArray.length - 1];
			} else {
				classStr = code.substring(code.indexOf("class") + 5,
						code.indexOf("main")).toString();// 获取类名字符串
				while (classStr.indexOf("class") != -1) {
					classStr = classStr.substring(code.indexOf("class") + 5);
				}
				String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
				/*
				 * for (String string : classStrArray) {
				 * System.out.println(".."+string); }
				 */
				className = classStrArray[1];
				// System.out.println("@@"+className);
				if (className.indexOf("{") != -1) {
					className = className.substring(0, className.length() - 1);
				}
				// System.out.println("##"+className);
			}

			// 文件存储地址
			String address = "f:/compile/java/comment/" + random;

			File file = new File(address);
			if (!file.exists()) {
				file.mkdirs();// 目录不存在的情况下，创建目录。
			}
			String address1 = "f:/compile/java/comment/" + random + "/"
					+ className + ".java";
			File file1 = new File(address1);
			InputStream inputStream = null;
			InputStream errorStream = null;
			try {
				FileOutputStream os = new FileOutputStream(file1);
				byte[] b = code.getBytes();
				try {
					os.write(b);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					os.flush();
					os.close();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Runtime runtime = Runtime.getRuntime();
			try {

				Process exec = runtime
						.exec("cmd /c f:&&cd compile/java/comment/" + random
								+ "&&javac " + className + ".java&&java "
								+ className);
				inputStream = exec.getInputStream();
				errorStream = exec.getErrorStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						inputStream));

				String str;
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					result = result + str + "\n";
				}
				BufferedReader br2 = new BufferedReader(new InputStreamReader(
						errorStream));
				while ((str = br2.readLine()) != null) {
					System.out.println(str);
					result = result + str + "\n";
				}
				br.close();
				br2.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
				errorStream.close();
				//Runtime.getRuntime().exec("cmd /c rd /s /q f:\\compile\\java\\comment\\"+ random);
			}
		} else {
			if (code.indexOf("public class") != -1) {
				classStr = code.substring(code.indexOf("public class"),
						code.indexOf("{"));// 获取类名字符串
				String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
				className = classStrArray[classStrArray.length - 1];
			} else {
				classStr = code.substring(code.indexOf("class") + 5,
						code.indexOf("main")).toString();// 获取类名字符串
				while (classStr.indexOf("class") != -1) {
					classStr = classStr.substring(code.indexOf("class") + 5);
				}
				String[] classStrArray = classStr.split("\\s{1,}");// 按空格分开
				/*
				 * for (String string : classStrArray) {
				 * System.out.println(".."+string); }
				 */
				className = classStrArray[1];
				// System.out.println("@@"+className);
				if (className.indexOf("{") != -1) {
					className = className.substring(0, className.length() - 1);
				}
				// System.out.println("##"+className);
			}

			// 文件存储地址
			String address = "f:/compile/java/" + name;

			File file = new File(address);
			if (!file.exists()) {
				file.mkdirs();// 目录不存在的情况下，创建目录。
			}
			String address1 = "f:/compile/java/" + name + "/" + className
					+ ".java";
			File file1 = new File(address1);
			InputStream inputStream = null;
			InputStream errorStream = null;
			try {
				FileOutputStream os = new FileOutputStream(file1);
				byte[] b = code.getBytes();
				try {
					os.write(b);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					os.flush();
					os.close();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Runtime runtime = Runtime.getRuntime();
			try {
				Process exec = runtime.exec("cmd /c f:&&cd compile/java/"
						+ name + "&&javac " + className + ".java&&java "
						+ className);
				inputStream = exec.getInputStream();
				errorStream = exec.getErrorStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						inputStream));

				String str;
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					result = result + str + "\n";
				}
				BufferedReader br2 = new BufferedReader(new InputStreamReader(
						errorStream));
				while ((str = br2.readLine()) != null) {
					System.out.println(str);
					result = result + str + "\n";
				}
				br.close();
				br2.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
				errorStream.close();
				//Runtime.getRuntime().exec("cmd /c rd /s /q f:\\compile\\java\\"+name);
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter(); 
        writer.print(result);  
        writer.flush();  
        writer.close(); 
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
