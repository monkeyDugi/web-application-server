# 실습을 위한 개발 환경 세팅
* https://github.com/slipp/web-application-server 프로젝트를 자신의 계정으로 Fork한다. Github 우측 상단의 Fork 버튼을 클릭하면 자신의 계정으로 Fork된다.
* Fork한 프로젝트를 eclipse 또는 터미널에서 clone 한다.
* Fork한 프로젝트를 eclipse로 import한 후에 Maven 빌드 도구를 활용해 eclipse 프로젝트로 변환한다.(mvn eclipse:clean eclipse:eclipse)
* 빌드가 성공하면 반드시 refresh(fn + f5)를 실행해야 한다.

# 웹 서버 시작 및 테스트
* webserver.WebServer 는 사용자의 요청을 받아 RequestHandler에 작업을 위임하는 클래스이다.
* 사용자 요청에 대한 모든 처리는 RequestHandler 클래스의 run() 메서드가 담당한다.
* WebServer를 실행한 후 브라우저에서 http://localhost:8080으로 접속해 "Hello World" 메시지가 출력되는지 확인한다.

# 각 요구사항별 학습 내용 정리
* 구현 단계에서는 각 요구사항을 구현하는데 집중한다. 
* 구현을 완료한 후 구현 과정에서 새롭게 알게된 내용, 궁금한 내용을 기록한다.
* 각 요구사항을 구현하는 것이 중요한 것이 아니라 구현 과정을 통해 학습한 내용을 인식하는 것이 배움에 중요하다. 

### 요구사항 1 - http://localhost:8080/index.html로 접속시 응답
* 

### 요구사항 2 - get 방식으로 회원가입
* 

### 요구사항 3 - post 방식으로 회원가입
* 

### 요구사항 4 - redirect 방식으로 이동
* 

### 요구사항 5 - cookie로 로그인하기
- [x] 로그인 메뉴 클릭 시 `http://localhost:8080/user/login.html로` 이동해야 한다.
- [x] 성공 시 `/index.html`로 이동해야 한다.
- [x] 실패 시 `/user/login_failed.html`로 이동해야 한다.
- [x] 회원 가입한 사용자로 로그인 할 수 있어야 한다.
- [x] 로그인 성공 시 로그인 유지를 위해 Cookie 헤더에 `logined=true`, 실패 시 Cookie 헤더에 `logined=false`로 응답해야 한다.
- [ ] 쿠키 셋팅 후 요청마다 셋팅한 쿠키 오는지 확인
  - 302 응답으로 Set-Cookie가 확인 되었는데 이후 요청에서 Cookie Request가 안된다.  
    확인 해보니 Session Cookie이던데 브라우저를 닫지도 않았는데 페이지를 이동하면 사라진다.
  - 302 응답으로 Set-Cookie를 했는데 왜 해당 요청에 Request Header에 Set-Cookie의 값이 담겨 있지?
  - root 페이지는 cookie request가 안되고, 다른 곳들은 cookie request를 하네? cookie가 사라진다.
- [x] 사용자 목록 출력
  - [x] logined=true이면 /user/list로 요청 시 사용자 목록을 StringBuilder 직접 그려서 응답한다.
  - [x] 로그인하지 않은 상태라면 login.html로 이동하다.
### 요구사항 6 - stylesheet 적용
- [x] css 적용하기. accept 활용

### heroku 서버에 배포 후
* 
