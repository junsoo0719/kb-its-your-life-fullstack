# ✨ File Upload, Download

## 1. 파일 업로드와 다운로드 개념

게시판에서 파일 업로드와 다운로드 기능은 게시글에 첨부파일을 연결하고, 사용자가 첨부파일을 내려받을 수 있게 하는 기능이다.

게시글 정보는 `board` 테이블에 저장하고, 첨부파일 정보는 별도의 첨부파일 테이블에 저장한다.

즉, 게시글과 첨부파일은 1:N 관계로 관리할 수 있다. 📌

- 게시글 1개 → 첨부파일 여러 개 가능
- 첨부파일 1개 → 특정 게시글 1개에 연결

## 2. 첨부파일 테이블 생성

첨부파일 정보를 저장하기 위해 `tbl_board_attachment` 테이블을 생성한다.

이 테이블에는 보통 다음 정보가 들어간다.

- 첨부파일 번호
- 게시글 번호
- 원본 파일명
- 저장 파일명 또는 저장 경로
- 파일 크기
- 파일 타입
- 등록일

첨부파일은 특정 게시글에 속해야 하므로 `board`의 `bno`에 대한 외래 키를 설정한다.

즉, 첨부파일 테이블의 게시글 번호는 게시글 테이블의 기본 키를 참조한다. ✅

## 3. FK 설정

첨부파일 테이블은 게시글 테이블과 외래 키 관계를 가진다.

개념적으로는 다음과 같다.

    tbl_board.bno           ← 기준 테이블의 PK
    tbl_board_attachment.bno ← 첨부파일 테이블의 FK

즉, 첨부파일은 존재하는 게시글에만 연결될 수 있다.

게시글이 삭제될 때 첨부파일도 함께 삭제해야 한다면 `ON DELETE CASCADE` 같은 옵션을 고려할 수 있다. 📌

## 4. 파일명 중복 문제

업로드된 파일을 서버에 저장할 때 가장 주의해야 하는 부분은 파일명 중복이다.

예를 들어 서로 다른 사용자가 같은 이름의 파일을 업로드할 수 있다.

    image.png
    image.png

이 파일들을 같은 폴더에 그대로 저장하면 기존 파일이 덮어써질 수 있다. ⚠️

따라서 서버에 저장할 때는 유일한 파일명이 되도록 처리해야 한다.

## 5. 유일한 파일명 만들기

파일명 중복을 방지하는 대표적인 방법은 다음과 같다.

- UUID 사용
- 기존 파일명에 timestamp 추가

예를 들어 UUID를 사용하면 다음처럼 저장 파일명을 만들 수 있다.

    550e8400-e29b-41d4-a716-446655440000_image.png

timestamp를 붙이면 다음처럼 만들 수 있다.

    20260622103015_image.png

즉, 실제 저장 파일명은 유일하게 만들고, 원본 파일명은 별도로 보관해야 한다. ✅

## 6. 원본 파일명 저장

서버에 저장할 때는 파일명 중복을 막기 위해 저장 파일명을 변경할 수 있다.

하지만 사용자가 다운로드할 때는 원래 파일명으로 내려받는 것이 자연스럽다.

따라서 데이터베이스에는 다음 정보를 구분해서 저장하는 것이 좋다.

- 원본 파일명
- 서버 저장 파일명
- 저장 경로

예를 들어 사용자가 `report.pdf`를 업로드했지만 서버에는 UUID가 붙은 파일명으로 저장할 수 있다.

    원본 파일명: report.pdf
    저장 파일명: 550e8400-report.pdf

다운로드 시에는 응답 헤더에 원본 파일명을 설정해 사용자가 원래 이름으로 받을 수 있게 한다. 📌

## 7. BoardVO와 BoardAttachmentVO 연결

게시글 하나에 첨부파일 여러 개가 연결될 수 있으므로 `BoardVO`에 `BoardAttachmentVO` 목록을 연결할 수 있다.

예시는 다음과 같다.

    public class BoardVO {
        private Long bno;
        private String title;
        private String content;
        private List<BoardAttachmentVO> attaches;
    }

즉, 게시글 객체가 첨부파일 목록을 함께 가질 수 있다.

## 8. BoardVO에 첨부파일 연결하는 방법

`BoardVO`와 `BoardAttachmentVO`를 연결하는 방법은 크게 두 가지가 있다.

1. join 처리 후 `resultMap` 구성
2. `setAttaches()` 호출로 처리

즉, MyBatis 매핑 단계에서 한 번에 연결할 수도 있고, 서비스 계층에서 별도로 첨부파일 목록을 조회해 세팅할 수도 있다. ✅

## 9. join 처리와 resultMap

게시글과 첨부파일을 조인해서 한 번에 조회하려면 MyBatis의 `resultMap`을 구성할 수 있다.

개념적으로는 다음과 같다.

    SELECT b.*, a.*
    FROM tbl_board b
    LEFT OUTER JOIN tbl_board_attachment a
        ON b.bno = a.bno
    WHERE b.bno = #{bno}

첨부파일이 없을 수도 있으므로 `LEFT OUTER JOIN`을 사용하는 것이 안전하다.

`resultMap`을 사용하면 조인 결과를 `BoardVO`와 그 안의 첨부파일 목록으로 매핑할 수 있다. 📌

## 10. setAttaches() 호출 방식

다른 방법은 게시글을 먼저 조회하고, 첨부파일 목록을 별도로 조회한 뒤 `setAttaches()`로 연결하는 방식이다.

흐름은 다음과 같다.

1. 게시글 정보 조회
2. 게시글 번호로 첨부파일 목록 조회
3. `board.setAttaches(첨부파일목록)` 호출
4. 게시글 DTO 또는 VO 반환

이 방식은 조인 매핑이 복잡할 때 이해하기 쉽다.

## 11. Transaction 처리

게시글과 첨부파일을 함께 저장할 때는 트랜잭션 처리가 필요하다.

예를 들어 게시글 등록은 성공했는데 첨부파일 저장 중 오류가 발생하면 데이터가 불일치할 수 있다.

따라서 게시글 등록과 첨부파일 등록을 하나의 작업 단위로 묶어야 한다.

즉, 모두 성공하면 commit하고, 중간에 예외가 발생하면 rollback해야 한다. ✅

## 12. @EnableTransactionManagement

스프링에서 트랜잭션 처리를 활성화하려면 설정 클래스에 `@EnableTransactionManagement`를 추가한다.

예시는 다음과 같다.

    @Configuration
    @EnableTransactionManagement
    public class RootConfig {
    }

이 어노테이션을 추가하면 스프링의 어노테이션 기반 트랜잭션 처리를 사용할 수 있다. 📌

## 13. @Transactional

`@Transactional`은 트랜잭션을 적용할 때 사용하는 어노테이션이다.

적용 위치는 다음과 같다.

- 클래스 레벨
- 메서드 레벨

클래스 레벨에 붙이면 해당 클래스의 모든 메서드에 트랜잭션 처리가 적용된다.

    @Transactional
    public class BoardServiceImpl {
    }

메서드 레벨에 붙이면 지정한 메서드에만 트랜잭션 처리가 적용된다.

    @Transactional
    public void create(BoardDTO board) {
        ...
    }

## 14. 트랜잭션 동작

트랜잭션은 하나의 작업 묶음이 성공했는지 실패했는지에 따라 처리된다.

기본 동작은 다음과 같다.

- 에러가 없으면 commit
- 예외가 발생하면 rollback

필기 기준으로는 `RuntimeException` 발생 시 rollback된다.

즉, 게시글 등록과 첨부파일 등록 중 하나라도 실패하면 전체 작업을 취소할 수 있다. ⚠️

## 15. 파일 업로드 form

파일 업로드를 하려면 form 태그에 반드시 `enctype="multipart/form-data"`를 지정해야 한다.

기본 형식은 다음과 같다.

    <form method="post" enctype="multipart/form-data">
        ...
        <input type="file" name="files" multiple>
        ...
    </form>

`multiple` 속성을 사용하면 여러 파일을 선택할 수 있다.

즉, 파일 업로드 form은 일반 form 전송과 다르게 multipart 형식으로 전송해야 한다. 📌

## 16. input type="file"

파일 선택은 다음 input으로 처리한다.

    <input type="file" name="files" multiple>

여기서 `multiple`은 여러 개의 파일 선택을 허용한다.

컨트롤러에서는 이 파일들을 `MultipartFile` 또는 `MultipartFile[]` 형태로 받을 수 있다.

예시는 다음과 같다.

    public String create(BoardDTO board, MultipartFile[] files) {
        ...
    }

## 17. 상세 보기에서 첨부파일 목록 보여주기

게시글 상세 보기 화면에서는 해당 게시글에 연결된 첨부파일 목록을 보여줄 수 있다.

예를 들어 다음 정보를 출력할 수 있다.

- 파일명
- 파일 크기
- 다운로드 링크

다운로드 링크는 첨부파일 번호를 이용해 만들 수 있다.

    /board/download/{no}

즉, 사용자는 상세 보기 화면에서 첨부파일 목록을 확인하고 원하는 파일을 다운로드할 수 있다. ✅

## 18. File download 개념

파일 다운로드는 단순히 파일 경로를 링크로 연결하는 방식으로 처리하기 어렵다.

예를 들어 서버에 파일이 다음 경로에 저장되어 있다고 하자.

    c:/upload/board

이 경로는 서버의 실제 파일 시스템 경로이다.  
웹 URL로 직접 표현할 수 없기 때문에 브라우저가 바로 접근할 수 없다. ⚠️

따라서 Controller가 다운로드 요청을 받아 직접 파일을 읽고 응답으로 내려줘야 한다.

## 19. 다운로드 요청 URL

다운로드 요청 URL은 다음과 같이 설계할 수 있다.

    /board/download/{no}

여기서 `{no}`는 다운로드할 첨부파일 번호이다.

이처럼 URL 경로 안에 포함된 변수를 경로 변수라고 한다.

스프링에서는 `@PathVariable`을 사용해 경로 변수를 추출할 수 있다.

    @GetMapping("/download/{no}")
    public void download(@PathVariable("no") Long no) {
        ...
    }

## 20. @PathVariable

`@PathVariable`은 URL 경로에 포함된 값을 메서드 파라미터로 받을 때 사용한다.

예를 들어 요청 URL이 다음과 같다면

    /board/download/10

컨트롤러에서는 다음처럼 받을 수 있다.

    @PathVariable("no") Long no

이 경우 `no` 값은 `10`이 된다.

즉, 경로 변수는 REST 방식 URL에서 특정 자원을 식별할 때 자주 사용한다. 📌

## 21. 응답 헤더 설정

파일을 다운로드하게 하려면 응답 헤더에 파일 첨부 정보를 설정해야 한다.

대표적으로 `Content-Disposition` 헤더를 사용한다.

개념적으로는 다음과 같다.

    Content-Disposition: attachment; filename="원본파일명"

이 헤더를 설정하면 브라우저는 응답을 화면에 직접 표시하기보다 첨부파일로 다운로드하려고 한다.

즉, 다운로드 시 원본 파일명으로 저장되도록 응답 헤더를 설정해야 한다. ✅

## 22. 바이너리 스트림으로 파일 전송

파일 다운로드는 텍스트 응답이 아니라 binary stream을 이용해 처리한다.

컨트롤러에서 서버의 실제 파일을 읽은 뒤 `HttpServletResponse`의 출력 스트림에 직접 write한다.

개념적인 흐름은 다음과 같다.

1. 첨부파일 번호로 DB에서 파일 정보 조회
2. 저장 경로와 저장 파일명 확인
3. 서버 파일 시스템에서 파일 읽기
4. 응답 헤더에 다운로드 정보 설정
5. `response.getOutputStream()`으로 binary stream 획득
6. 파일 바이트를 응답 스트림에 write

즉, 컨트롤러가 파일을 직접 읽어 HTTP 응답으로 내려주는 방식이다. 📌

## 23. 다운로드 처리 예시

다운로드 처리 코드는 개념적으로 다음과 같이 작성할 수 있다.

    @GetMapping("/download/{no}")
    public void download(@PathVariable("no") Long no,
                         HttpServletResponse response) throws IOException {

        BoardAttachmentVO attach = service.getAttachment(no);

        File file = new File(attach.getPath(), attach.getSavedName());

        response.setContentType(attach.getContentType());
        response.setHeader(
            "Content-Disposition",
            "attachment; filename=\"" + attach.getOriginalName() + "\""
        );

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            fis.transferTo(os);
            os.flush();
        }
    }

파일명에 한글이나 공백이 포함될 수 있다면 인코딩 처리가 추가로 필요할 수 있다.

## 24. 업로드와 다운로드 전체 흐름

파일 업로드와 다운로드 흐름은 다음과 같다.

### 24-1. 업로드 흐름

1. 사용자가 multipart form으로 파일 전송
2. 컨트롤러가 `MultipartFile` 수신
3. 파일명 중복 방지를 위해 UUID 또는 timestamp 생성
4. 서버 파일 시스템에 파일 저장
5. 원본 파일명과 저장 파일명, 경로를 DB에 저장
6. 게시글과 첨부파일 관계를 FK로 연결
7. 게시글 등록과 첨부파일 등록을 트랜잭션으로 처리

### 24-2. 다운로드 흐름

1. 상세 보기에서 첨부파일 목록 출력
2. 사용자가 다운로드 링크 클릭
3. `/board/download/{no}` 요청
4. 컨트롤러가 `@PathVariable`로 첨부파일 번호 추출
5. DB에서 파일 정보 조회
6. 서버 파일을 읽어 응답 스트림에 write
7. 응답 헤더로 첨부파일 다운로드 정보 설정

## 25. 중요 포인트 📌

- 첨부파일 정보는 `tbl_board_attachment` 테이블에 저장한다.
- 첨부파일 테이블은 board의 `bno`를 FK로 참조한다.
- 업로드 파일명은 중복될 수 있으므로 유일한 저장 파일명을 만들어야 한다.
- 유일한 파일명은 UUID 또는 timestamp를 사용해 만들 수 있다.
- 다운로드를 위해 원본 파일명을 반드시 저장해야 한다.
- `BoardVO`와 `BoardAttachmentVO`는 1:N 관계로 연결될 수 있다.
- 첨부파일 연결은 join + resultMap 방식 또는 `setAttaches()` 방식으로 처리할 수 있다.
- 게시글과 첨부파일 저장은 트랜잭션으로 묶어야 한다.
- `@EnableTransactionManagement`로 트랜잭션 처리를 활성화한다.
- `@Transactional`은 클래스 또는 메서드에 적용할 수 있다.
- 에러가 없으면 commit, `RuntimeException` 발생 시 rollback된다.
- 파일 업로드 form에는 `enctype="multipart/form-data"`가 필요하다.
- 여러 파일 업로드는 `input type="file"`에 `multiple`을 사용한다.
- 서버 파일 시스템 경로는 웹 URL로 직접 접근할 수 없다.
- 다운로드는 컨트롤러가 파일을 직접 읽어 응답으로 내려줘야 한다.
- 경로 변수는 `@PathVariable`로 추출한다.
- 다운로드 응답에는 `Content-Disposition` 헤더를 설정한다.
- 파일 데이터는 binary stream으로 직접 write한다.

## 정리 ✅

파일 업로드와 다운로드에서는 첨부파일 테이블 설계, 파일명 중복 처리, 게시글과 첨부파일 관계 설정, 트랜잭션 처리, multipart form, 다운로드 응답 처리가 핵심이다.  
첨부파일은 `tbl_board_attachment` 같은 별도 테이블에 저장하고, 게시글 번호 `bno`를 FK로 연결한다.  
서버에 파일을 저장할 때는 UUID나 timestamp를 사용해 유일한 저장 파일명을 만들고, 다운로드를 위해 원본 파일명을 반드시 DB에 저장해야 한다.  
게시글 등록과 첨부파일 등록은 하나의 작업 단위이므로 `@EnableTransactionManagement`와 `@Transactional`을 이용해 트랜잭션으로 처리한다.  
다운로드는 `c:/upload/board` 같은 실제 파일 경로를 직접 링크할 수 없기 때문에 `/board/download/{no}` 요청을 컨트롤러가 받아 `@PathVariable`로 번호를 추출하고, 파일을 binary stream으로 읽어 응답에 직접 write해야 한다.
