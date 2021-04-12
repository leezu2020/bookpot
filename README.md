# bookpot-Frontend-project
독후감을 쓰고 공유할 수 있는 사이트
# Bookpot
책단지 협업 프로젝트
*프론트엔드 개발자들과 협업하는 프로젝트*  

프로젝트 기간 : 21.03 ~ 현재 진행중  
사용 언어 : Java  
사용 프레임워크 : Spring Framework  
특이사항 : 페이지 이동을 제외한 가능한 부분에는 REST를 따르려고 시도

<br/><br/>
___
**업데이트**
___
##### 21.04.08
 - 회원 탈퇴시 작성글, 댓글, 스크랩, 좋아요 정보 삭제
##### 21.04.07
 - Swagger 적용(writing 패키지에만 적용)
##### 21.04.05
 - 검색 결과에 인덱싱 번호 추가
##### 21.04.03
 - 글 등록시 태그 기능 추가(없는 태그는 새로 추가)
 - 글 등록시 분야 기능 추가(없는 분야는 에러메세지 반환 예정)
##### 21.04.02
 - 검색 결과에 페이징 관련 값들 추가
 (글 정보, 페이지 번호, 검색조건이 저장된 url, 이전/다음 버튼 활성화 여부)
##### 21.04.01
 - 글 등록시 유효성 검사 (책제목, 분류, 한국/외국, 제목 필수)
##### 21.03.30
 - 댓글 기능 추가(등록, 수정, 삭제)
 - 좋아요 -1 기능 추가
 - 스크랩 기능 추가(저장, 취소)
##### 21.03.26
 - 좋아요 up 기능 추가(임시구현)
 - DB에 있는 책 목록 실시간 검색(책제목)
 - 로그인, 로그아웃 ajax로 구현
 - 중복로그인 방지(중복으로 로그인시, 기존 로그인은 자동 로그아웃 처리)
##### 21.03.14
 - 글쓰기 도서명 실시간 검색(책제목, 저자, 사진 출력)
##### 21.03.10
 - jsp의 id를 id->email로 수정
 - 회원가입 닉네임 중복 확인에 정규식 추가(한글 영문 숫자)
 - 회원가입 성공시 성공페이지 이동
 - 회원가입 이메일 인증버튼 클릭시 팝업 알림창 대기시간 축소(Async 사용)
##### 21.03.09
 - 아이디 대신 이메일 사용
 - 회원가입시, 이메일 인증코드로 인증
##### 21.03.08
 - 회원가입 제출시 빈칸(혹은 띄어쓰기만 사용)이 있을때, 칸 아래에 오류 출력
 - 중복확인 기능 구현(script에서 ajax사용)
 - 비밀번호 정규식 적용(영어 대문자, 소문자, 숫자 혼용 8~16자리)
 - 로고 클릭시 메인화면 이동
 - 임시로 기본 로그인창 사용
 - 로그인 시, 우측 상단에 회원정보, 로그아웃, 글쓰기 출력
##### 21.03.06
 - 로그인 구현
 ----

**사용기술**
  #### validator
 - 회원가입시, 빈칸(및 띄어쓰기만 사용) 확인 및 조건 충족 여부 확인
 - 오류 메세지 출력
  #### ajax
 - 닉네임을 페이지 갱신없이 중복확인
 - 페이지 갱신 없이 이메일 인증, 도서 검색, 제목 검색, 로그인, 로그아웃 기능 구현
  #### MySQL
 - Database 설계 및 저장
  #### Spring Security
 - 권한 및 인증(로그인/로그아웃)
  #### JavaMailsender
 - 회원가입시, 이메일 인증 구현
  #### Async
 - 인증 이메일 비동기적 전송