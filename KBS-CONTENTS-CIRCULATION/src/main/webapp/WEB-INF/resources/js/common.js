$(function() {
	$("a[href='#']").click(function(event) {
		event.preventDefault();      
	}); 
	
	$(".gnb > ul > li > a").click(function(){
		$(".gnb > ul > li > a").removeClass("active");
		$(this).addClass("active");
		$(".gnb > ul > li > ul").removeClass("active");
		$(this).parent("li").children("ul").addClass("active");
	});
	$(".gnb > ul > li > ul > li > a").click(function(){
		$(".gnb > ul > li > ul > li > a").removeClass("active");
		$(this).addClass("active");
	});
	
	var select = $(".select_box select");
    select.change(function(){
        var select_name = $(this).children("option:selected").text();
        $(this).siblings("label").text(select_name);
    });
	
	var fileTarget = $('.filebox .upload-hidden');

    fileTarget.on('change', function(){
        if(window.FileReader){
            var filename = $(this)[0].files[0].name;
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload-name').val(filename);
    });
	
	/* 171210 add */
	var awidth = $(".tbl-bxn thead").width();
	var bwidth = $(".tbl-bxn thead tr th").last().width() - "18";
	$(".tbl-bxn tbody").css("width", awidth);
	$(".tbl-bxn tbody tr td").last().css("width", bwidth);
	/* 171210 end */
});

function formatRange(){
	/* 171210 add */
	var awidth = $(".tbl-bxn thead").width();
	var bwidth = $(".tbl-bxn thead tr th").last().width() - "18";
	if(awidth==0) {
		awidth="964";
	}
	$(".tbl-bxn tbody").css("width", awidth);
	$(".tbl-bxn tbody tr td").last().css("width", bwidth);
	/* 171210 end */
}

function humanReadable(seconds) {
  var pad = function(x) { return (x < 10) ? "0"+x : x; }
  return AddComma(pad(parseInt(seconds / (60*60)))) + ":" +
         pad(parseInt(seconds / 60 % 60)) + ":" +
         pad((seconds % 60).toFixed())
}


function AddComma(data_value) {
	 
    var txtNumber = '' + data_value;    // 입력된 값을 문자열 변수에 저장합니다.
 
    if (isNaN(txtNumber) || txtNumber == "") {    // 숫자 형태의 값이 정상적으로 입력되었는지 확인합니다.
        alert("숫자만 입력 하세요");
        return;
    }
    else {
        var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');    // 정규식 형태 생성
        var arrNumber = txtNumber.split('.');    // 입력받은 숫자를 . 기준으로 나눔. (정수부와 소수부분으로 분리)
        arrNumber[0] += '.'; // 정수부 끝에 소수점 추가
 
        do {
            arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2'); // 정수부에서 rxSplit 패턴과 일치하는 부분을 찾아 replace 처리
        } while (rxSplit.test(arrNumber[0])); // 정규식 패턴 rxSplit 가 정수부 내에 있는지 확인하고 있다면 true 반환. 루프 반복.
 
        if (arrNumber.length > 1) { // txtNumber를 마침표(.)로 분리한 부분이 2개 이상이라면 (즉 소수점 부분도 있다면)
            return arrNumber.join(''); // 배열을 그대로 합칩. (join 함수에 인자가 있으면 인자를 구분값으로 두고 합침)
        }
        else { // txtNumber 길이가 1이라면 정수부만 있다는 의미.
            return arrNumber[0].split('.')[0]; // 위에서 정수부 끝에 붙여준 마침표(.)를 그대로 제거함.
        }
    }
}
