package board.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

// 롬복 라이브러리를 사용하여 기본적으로 필요한 getter/setter를 자동으로 생성함
@Setter
@Getter
public class BoardDto extends CommonDTO{
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdId;
	private String createdDatetime;
	private String updaterId;
	private String updatedDatetime;
	private String deletedYn;
	
//	기존의 BoardDto 클래스는 파일 정보에 대한 멤버 변수가 없음으로 추가함
	private List<BoardFileDto> fileList;
}
