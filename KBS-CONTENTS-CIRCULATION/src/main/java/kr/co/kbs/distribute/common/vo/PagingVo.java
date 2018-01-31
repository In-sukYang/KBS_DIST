package kr.co.kbs.distribute.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingVo {
	private int recordsPerPage = 10;       // 페이지당 레코드 수
	private int firstPageNo;          // 첫번째 페이지 번호
	private int prevPageNo;           // 이전 페이지 번호
	private int currentPageNo;        // 페이지 번호
	private int nextPageNo;           // 다음 페이지 번호
	private int finalPageNo;          // 마지막 페이지 번호
	private int numberOfRecords;      // 전체 레코드 수
	
	private int sizeOfPage = 10;           // 보여지는 페이지 갯수 (1,2,3,4,5 갯수)
	
	private int startPageNo;          // 시작 페이지 (페이징 너비 기준)
	private int endPageNo;            // 끝 페이지 (페이징 너비 기준)
	
	private int offset;
	private int noOfRecords = 10;
	private String pagingYn = "Y";
	
	public void makePaging(int numberOfRecords) {
		
        if (numberOfRecords == 0) {       // 게시글 전체 수가 없는 경우
            return;
        }else {
        	 setNumberOfRecords(numberOfRecords);
        }
        if (currentPageNo == 0) {
            setCurrentPageNo(1);        // 기본 값 설정
        }
        if (recordsPerPage == 0) {
            setRecordsPerPage(10);        // 기본 값 설정
        }
        
        setOffset((getCurrentPageNo() - 1) * getRecordsPerPage());
        
        setNoOfRecords(sizeOfPage);
                                        // 마지막 페이지
        int finalPage = (numberOfRecords + (recordsPerPage - 1)) / recordsPerPage;
        
        if (currentPageNo > finalPage)
            setCurrentPageNo(finalPage);// 기본 값 설정
        
        if (currentPageNo < 0 || currentPageNo > finalPage) {
            currentPageNo = 1;            // 현재 페이지 유효성 체크
        }
        // 시작 페이지 (전체)
        boolean isNowFirst = currentPageNo == 1 ? true : false;
        boolean isNowFinal = currentPageNo == finalPage ? true : false;
        
        int startPage = ((currentPageNo - 1) / sizeOfPage) * sizeOfPage + 1;
        int endPage = startPage + sizeOfPage - 1;        
        
        if (endPage > finalPage) {
            endPage = finalPage;
        }
        
        setFirstPageNo(1);                    // 첫번째 페이지 번호
        
        if (isNowFirst){
            setPrevPageNo(1);                // 이전 페이지 번호
        }else{                                // 이전 페이지 번호
            setPrevPageNo(((currentPageNo - 1) < 1 ? 1 : (currentPageNo - 1)));
        }
        setStartPageNo(startPage);            // 시작페이지
        setEndPageNo(endPage);                // 끝 페이지
        
        if (isNowFinal){
            setNextPageNo(finalPage);        // 다음 페이지 번호
        }else{
            setNextPageNo(((currentPageNo + 1) > finalPage ? finalPage : (currentPageNo + 1)));
        }
        setFinalPageNo(finalPage);            // 마지막 페이지 번호
    }

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
		if(currentPageNo != 0) {
			setOffset((getCurrentPageNo() - 1) * getRecordsPerPage());
		}
		
	}
	
}
