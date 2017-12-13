package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.MemberDao;
import test.dto.MemberDto;
/*
 * 	수정할 회원정보를 출력해 주는 서블릿
 */
@WebServlet("/member/updateform")
public class MemberUpdateFormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 수정할 회원의 번호를 읽어온다.
		int num = Integer.parseInt(request.getParameter("num"));
		//2. MemberDao 객체를 이용해서 해당 번호의 회원정보를 얻어온다.
		MemberDto dto = MemberDao.getInstance().getData(num); 
		//3. 수정할 회원의 정보를 form 에 출력해서 응답한다.
		//응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		//응답 컨텐츠 설정
		response.setContentType("text/html;charset=utf-8");

		//클라이언트에게 문자열을 출력할 수 있는 객체 얻어오기
		PrintWriter pw = response.getWriter();
		pw.println("<!doctype hmtl>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8' />");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		/*
		 * 	<form action ="update" method="post">
		 * 		<input type='hidden' name='num' value='' />
		 * 		<input type='text' name='name' value='' />
		 * 		<input type='text' name='addr' value='' />
		 *  </form>
		 */
		pw.println("<h3>회원정보 수정폼</h3>");
		pw.println("<form action='update' method='post'>");
		pw.println("<input type='hidden' name='num' value='"+dto.getNum()+"' />");
		pw.println("<input type='text' name='name' value='"+dto.getName()+"' />");
		pw.println("<input type='text' name='addr' value='"+dto.getAddr()+"' />");
		pw.println("<button type='submit'>수정확인</button>");
		pw.println("<button type='reset'>취소</button>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
