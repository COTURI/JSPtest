package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boards/list.do")
public class ListController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO dao = new BoardDAO();
        Map<String, Object> map = new HashMap<String, Object>();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        if (searchWord != null) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        int totalCount = dao.selectCount(map);
        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
        int pageNum = 1;
        String pageTemp = req.getParameter("pageNum");

        if (pageTemp != null && !pageTemp.equals("")) {
            pageNum = Integer.parseInt(pageTemp);
        }

        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
        map.put("start", start);
        map.put("end", end);

        List<BoardDTO> boardLists = dao.selectListPage(map);
        dao.close();

        String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../boards/list.do");

        req.setAttribute("boardLists", boardLists);
        req.setAttribute("pagingImg", pagingImg);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("pageNum", pageNum);

        req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
    }
}
