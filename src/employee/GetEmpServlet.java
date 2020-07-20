package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({ "/GetEmpServlet", "/GetEmpListServlet" })
public class GetEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public GetEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equals("list")) {
		response.setCharacterEncoding("UTF-8"); // 한글 사용
		response.setContentType("text/html;charset=utf-8"); // 한글 사용
//		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" 러러");
		// {"id":"user1","first_name":"HONG","age":"30}
		PrintWriter out = response.getWriter();
		// hr계정 employee컬럼 가져오기 (employee_id, first_name, email, salary)
//		out.write("[{\"id\":\"user1\",\"first_name\":\"HONG\",\"age\":\"30\"},");
//		out.write("{\"id\":\"user2\",\"first_name\":\"HHH\",\"age\":\"20\"}]");
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		int cnt = 0;
		int rowCnt = list.size();
		out.write("[");
		for (Employee emp : list) {
			// {"id":"data1","first_name":"data2","email":"data3","salary":"data4"};
			out.write("{\"id\":\"" + emp.getEmployeeId() 
					+ "\",\"first_name\":\"" + emp.getFirstName()
					+ "\",\"email\":\"" + emp.getEmail() 
					+ "\",\"salary\":\"" + emp.getSalary() + "\"}");
			if(++cnt != rowCnt)
				out.write(",");
		}
		out.write("]");		
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
