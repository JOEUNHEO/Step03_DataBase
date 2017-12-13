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

@WebServlet("/member/insert")
public class MemberInsertServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//폼 전송되는 회원정보를 읽어와서
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		//MemberDao 를 이용해서 DB 에 저장하고
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		boolean flag = MemberDao.getInstance().insert(dto);
		//응답한다.
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
		if(flag) {
			pw.println("<p>회원정보가 추가되었습니다.</p>");
		}else {
			pw.println("<p>회원정보 추가가 실패했습니다.</p>");
		}
		pw.println("<a href='list'>회원 목록 보기</a>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
