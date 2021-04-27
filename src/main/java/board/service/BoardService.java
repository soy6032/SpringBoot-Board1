package board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDto;

public interface BoardService {
	List<BoardDto> selectBoardList(BoardDto params) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
//	public void insertBoard(BoardDto board) throws Exception;
//	파일 업로드 부분을 처리하기 위한 매개변수 추가
	public void insertBoard(BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception;
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
}
