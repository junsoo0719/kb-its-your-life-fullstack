// 입력값
const userId = document.getElementById('userId');
const userPw = document.getElementById('userPw');

// 버튼
const loginBtn = document.getElementById('loginBtn');

// 메시지 영역
const errorBox = document.getElementById('errorBox'); //  에러 메시지
const successBox = document.getElementById('successBox'); // 성공 메시지
const helperLink = document.getElementById('helperLink'); // 비밀번호 찾기 링크

const validId = 'admin';
const validPw = '1234';

// 메시지 초기화 함수
// 로그인 시도 전에 기존 메시지를 모두 숨기고 초기화
function resetMessages() {
  // 메시지 영역 숨기기
  errorBox.classList.add('hidden');
  successBox.classList.add('hidden');
  helperLink.classList.add('hidden');

  // 메시지 내용 초기화
  errorBox.textContent = '';
  successBox.textContent = '';
}

// 로그인 버튼 클릭 시
loginBtn.addEventListener('click', () => {
  const id = userId.value.trim();
  const pw = userPw.value.trim();

  resetMessages();

  // 1. 아무것도 입력하지 않은 경우
  if (!id && !pw) {
    errorBox.textContent = '아이디와 비밀번호를 입력해주세요.';
    errorBox.classList.remove('hidden');
    helperLink.classList.remove('hidden'); // 비밀번호 찾기 표시
    return;
  }

  // 2. 아이디만 입력 안 한 경우
  if (!id) {
    errorBox.textContent = '아이디를 입력해주세요.';
    errorBox.classList.remove('hidden');
    return;
  }

  // 3. 비밀번호만 입력 안 한 경우
  if (!pw) {
    errorBox.textContent = '비밀번호를 입력해주세요.';
    errorBox.classList.remove('hidden');
    helperLink.classList.remove('hidden');
    return;
  }

  // 4. 아이디가 틀린 경우
  if (id !== validId) {
    errorBox.textContent = '아이디가 올바르지 않습니다.';
    errorBox.classList.remove('hidden');
    return;
  }

  // 5. 비밀번호가 틀린 경우
  if (pw !== validPw) {
    errorBox.textContent = '비밀번호가 올바르지 않습니다.';
    errorBox.classList.remove('hidden');
    helperLink.classList.remove('hidden'); // 비밀번호 찾기 표시
    return;
  }

  successBox.textContent = '로그인에 성공했습니다.';
  successBox.classList.remove('hidden');
});

// 아이디만 비었을 때 메시지 출력
// 비밀번호만 비었을 때 메시지 출력
// admin / 1234이면 성공 메시지 출력
// 실패했을 때만 비밀번호 찾기 링크 표시
