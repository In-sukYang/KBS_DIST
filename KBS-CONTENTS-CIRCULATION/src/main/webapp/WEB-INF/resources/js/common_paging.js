/**
 * 
 */
$(document).ready(function() {
	var makrePaging = function makePaging(paging) {
	
		if (paging.finalPageNo == 1) {
			$('#paging').hide();
		} else {
			$('#paging').show();
	
			$('.paging').empty();
	
			if (paging.finalPageNo <= 10) {
				for (var k = 1; k <= paging.finalPageNo; k++) {
					if (k == paging.currentPageNo) {
						$('.paging').append("  <strong> " + k + "   </strong>  ");
					} else {
						$('.paging').append("  <a href='#' > " + k + "  </a>  ");
					}
				}
			}
	
			// <a href="#" class="first">처음</a>
			// <a href="#" class="prev">이전</a>
			// <strong>1</strong>
			// <a href="#">2</a>
			// <a href="#">3</a>
			// <a href="#" class="next">다음</a>
			// <a href="#" class="end">맨끝</a>
	
		}
	}
});