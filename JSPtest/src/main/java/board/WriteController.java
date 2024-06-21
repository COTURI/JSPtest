package board;

import java.io.IOException;

import board.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import board.JSFunction;

/**
 * Servlet implementation class WriteController
 */
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/Board/Write.jsp").forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 파일 업로드 처리====================================================
		// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		
		// 파일 업로드
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}
		catch(Exception e) {
			//파일 업로드 실패
			e.printStackTrace();
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다", "../mvcboard/write.do");
			return;
		}
		
		// 2. 파일 업로드 외 처리 ===========================================
		// 폼값을 DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setWriterId(req.getParameter("id"));
		dto.setTitle(req.getParameter("title"));
		dto.setDetail(req.getParameter("detail"));
		dto.setPass(req.getParameter("pass"));
		
		if(originalFileName != "") {
			String savedFileName = FileUtil.renameFile(originalFileName);
			
			dto.setImgUrl_1(originalFileName)	//원래 파일 이름
			
		}
		
		//DAO를 통해 DB에 게시 내용 저장
		BoardDAO dao = new BoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		//성공 or 실패?
		if(result ==1) { //글쓰기 성공
			resp.sendRedirect("../mvcboard/list.do");
			
		}
		else {//글쓰기 실패
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다", "../mvcboard/write.do");
		}
		
		
//		request.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
}