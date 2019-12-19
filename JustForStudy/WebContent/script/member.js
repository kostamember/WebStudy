function loginCheck() {
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 써주세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value == "") {
		alert("암호를 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	return true;
}

function idCheck() {
	if(document.frm.userid.value == "") {  // 회원 가입 폼에서 아이디를 입력받았는지 확인함
		alert('아이디를 입력하여 주십시오. ');
		document.frm.userid.focus();
		return;
	}
	var url = "idCheck.do?userid=" + document.frm.userid.value;   // idcheck.jsp 페이지는 idCheck.do로 요청함. idCheck.do를 요청하면서 입력받은 회원 아이디를 서블릿에 보내 데이터베이스에 이 아이디가 저장되어 있는지 확인할 것.
	window.open(url, "_blank_1",
	"toolbar=no, menubar=no, scrollbars=yes, resizeable=no, width=450, height=200");  // 아이디 중복 체크를 위해서는 아이디를 입력 받아야 함. 아이디 중복 체크를 위한 idcheck.jsp 페이지는 현재 페이지가 아닌 새로운 창에 출력. 자바스크립트에서는 새로운 창을 띄우기 위해서 window 객체의 open 메소드를 제공.
	
}

function idok() {
	opener.frm.userid.value=document.frm.userid.value;  // 자바스크립트에서 opener란 이 창을 열어준 부모 창을 말한다. 여기서는 회원 가입 폼이 된다. 회원 가입 폼의 아이디를 입력받는 폼에 아이디 중복 체크가 끝난 아이디 값을 준다
	opener.frm.reid.value=document.frm.userid.value;	// reid는 아이디 중복 체크 과정을 거쳤는지를 확인하기 위해서 회원 가입 폼에 만들어둔 히든 태그. 
	self.close();
}

function joinCheck() {
	if(document.frm.name.value.length == 0) {
		alert("이름을 써 주세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 써 주세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.userid.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value.length == 0) {
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwd_check.value) {
		alert("암호가 일치하지 않습니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.reid.value.length == 0) {
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
}

