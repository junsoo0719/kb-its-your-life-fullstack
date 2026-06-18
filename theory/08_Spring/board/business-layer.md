# ✨ 비즈니스 계층

## 1. 비즈니스 계층의 역할

비즈니스 계층은 애플리케이션의 주요 로직을 처리하는 계층이다.

게시판 기능에서는 글 등록, 글 조회, 글 수정, 글 삭제 같은 기능이 단순히 SQL 실행만으로 끝나지 않을 수 있다.  
예를 들어 첨부파일 처리, 권한 검사, 데이터 변환 같은 작업이 함께 필요할 수 있다.

즉, 비즈니스 계층은 컨트롤러와 영속 계층 사이에서 실제 서비스 로직을 담당한다. 📌

## 2. VO와 DTO

게시판 프로젝트에서는 데이터를 표현하는 객체로 VO와 DTO를 구분해서 사용할 수 있다.

대표적인 객체는 다음과 같다.

- VO
- DTO

VO와 DTO는 둘 다 데이터를 담는 객체이지만, 사용하는 계층과 목적이 다르다.

## 3. VO

VO는 Value Object의 약자이다.

필기 기준에서 VO는 데이터베이스 테이블 모양을 그대로 반영한 객체이다.

즉, 테이블 컬럼 구조에 맞춰 필드를 구성한다.

예를 들어 게시판 테이블에 다음 컬럼이 있다면

    no
    title
    content
    writer
    reg_date
    update_date

VO 객체는 이 컬럼 구조를 반영해서 작성한다.

    public class BoardVO {
        private Long no;
        private String title;
        private String content;
        private String writer;
        private LocalDateTime regDate;
        private LocalDateTime updateDate;
    }

즉, VO는 DB 테이블의 한 행과 대응되는 객체라고 볼 수 있다. ✅

## 4. VO 사용 위치

VO는 주로 DAO 또는 Mapper 같은 영속 계층에서 사용한다.

즉, 데이터베이스와 직접 데이터를 주고받는 계층에서 테이블 구조를 반영하기 위해 사용한다.

정리하면 다음과 같다.

- VO → 데이터베이스 테이블 구조 반영
- VO → DAO, Mapper에서 사용
- VO → SQL 결과와 매핑

즉, VO는 DB와 가까운 객체이다. 📌

## 5. DTO

DTO는 Data Transfer Object의 약자이다.

DTO는 계층 간 데이터를 전달하기 위한 객체이다.

필기 기준으로 DTO는 DAO를 제외한 나머지 계층에서 사용한다.

예를 들어 다음 계층에서 사용될 수 있다.

- Service
- Controller
- View

즉, DTO는 화면이나 서비스 로직에서 필요한 데이터를 전달하기 위한 객체이다. ✅

## 6. DTO의 특징

DTO는 반드시 데이터베이스 테이블 구조와 똑같을 필요가 없다.

테이블에는 없지만 비즈니스 로직에 필요한 추가 정보를 포함할 수도 있다.

예를 들어 게시글 DTO에는 다음과 같은 정보가 추가될 수 있다.

- 댓글 수
- 첨부파일 목록
- 작성자 닉네임
- 조회 권한 여부
- 화면 표시용 날짜 문자열

이런 값들은 테이블 컬럼에 직접 없을 수 있지만, 서비스나 화면에서는 필요할 수 있다.

즉, DTO는 비즈니스 요구사항과 화면 요구사항을 반영할 수 있다. 📌

## 7. VO와 DTO의 차이

VO와 DTO는 다음처럼 구분할 수 있다.

| 구분      | VO               | DTO                                    |
| --------- | ---------------- | -------------------------------------- |
| 기준      | DB 테이블 구조   | 계층 간 데이터 전달                    |
| 사용 위치 | DAO, Mapper      | Service, Controller, View              |
| 필드 구성 | 테이블 컬럼 중심 | 비즈니스 로직, 화면 요구사항 포함 가능 |
| 목적      | DB 결과 매핑     | 데이터 전달                            |

즉, VO는 DB 중심 객체이고 DTO는 서비스와 화면 중심 객체이다.

## 8. VO와 DTO를 구분하는 이유

VO와 DTO를 구분하면 계층별 역할이 명확해진다.

만약 DB 테이블 구조가 변경되어도 모든 계층이 영향을 받지 않도록 중간에서 변환할 수 있다.

또한 화면에 필요한 데이터가 DB 테이블 구조와 다를 때 DTO를 별도로 만들면 더 유연하게 처리할 수 있다.

즉, VO와 DTO 구분은 유지보수성과 계층 분리를 위해 필요하다. ✅

## 9. VO와 DTO 간 상호 변환

VO와 DTO를 구분해서 사용하면 서로 변환하는 기능이 필요하다.

필요한 변환 방향은 다음과 같다.

- VO → DTO
- DTO → VO

예를 들어 데이터베이스에서 조회한 결과는 VO로 받아오고, 서비스나 컨트롤러에서는 DTO로 변환해서 사용할 수 있다.

반대로 화면에서 전달받은 DTO를 저장할 때는 VO로 변환해 Mapper에 전달할 수 있다. 📌

## 10. VO에서 DTO로 변환

VO는 이미 존재하지만 DTO는 아직 없는 상황을 생각할 수 있다.

이때 VO 데이터를 바탕으로 DTO 객체를 만들어야 한다.

변환 메서드는 DTO에 선언할 수 있다.

예를 들면 다음과 같다.

    public class BoardDTO {
        private Long no;
        private String title;
        private String content;
        private String writer;

        public static BoardDTO of(BoardVO vo) {
            BoardDTO dto = new BoardDTO();

            dto.setNo(vo.getNo());
            dto.setTitle(vo.getTitle());
            dto.setContent(vo.getContent());
            dto.setWriter(vo.getWriter());

            return dto;
        }
    }

이렇게 하면 `BoardDTO.of(vo)` 형태로 VO를 DTO로 변환할 수 있다.

## 11. static 메서드가 필요한 이유

VO는 있는데 DTO는 없는 상황에서는 DTO 객체를 만들기 전이므로 일반 인스턴스 메서드를 호출할 수 없다.

따라서 변환 메서드를 DTO 클래스에 선언하려면 `static` 메서드로 작성해야 한다.

    BoardDTO dto = BoardDTO.of(vo);

이 방식은 DTO 인스턴스가 없어도 클래스명으로 변환 메서드를 호출할 수 있다.

즉, VO를 입력받아 새로운 DTO를 만들어 반환하는 정적 팩토리 메서드 역할을 한다. ✅

## 12. DTO에서 VO로 변환

화면이나 컨트롤러에서 전달받은 DTO를 데이터베이스에 저장하려면 VO로 변환해야 할 수 있다.

예를 들면 다음과 같다.

    public BoardVO toVO() {
        BoardVO vo = new BoardVO();

        vo.setNo(this.no);
        vo.setTitle(this.title);
        vo.setContent(this.content);
        vo.setWriter(this.writer);

        return vo;
    }

이 경우 DTO 객체가 이미 존재하므로 인스턴스 메서드로 작성할 수 있다.

    BoardVO vo = dto.toVO();

즉, DTO에서 VO로 변환할 때는 현재 DTO 객체의 값을 이용해 VO를 만들면 된다. 📌

## 13. 변환 메서드 작성 위치

변환 메서드는 보통 DTO에 작성하는 방식이 편리하다.

이유는 다음과 같다.

- DTO가 서비스, 컨트롤러, 뷰 계층에서 사용됨
- VO와 DTO 변환 책임을 DTO 쪽에 모을 수 있음
- 컨트롤러나 서비스 코드가 간단해짐
- `DTO.of(vo)`, `dto.toVO()`처럼 사용하기 쉬움

즉, 변환 로직을 한 곳에 모으면 코드 중복을 줄일 수 있다.

## 14. Service 계층에서의 흐름

비즈니스 계층에서는 보통 다음 흐름으로 데이터를 처리한다.

조회 흐름은 다음과 같다.

1. Controller가 Service를 호출한다.
2. Service가 Mapper 또는 DAO를 호출한다.
3. Mapper가 VO를 반환한다.
4. Service에서 VO를 DTO로 변환한다.
5. Controller나 View에 DTO를 전달한다.

저장 흐름은 다음과 같다.

1. Controller가 DTO를 전달한다.
2. Service에서 DTO를 VO로 변환한다.
3. Mapper 또는 DAO에 VO를 전달한다.
4. 데이터베이스에 저장한다.

즉, Service 계층은 VO와 DTO 사이를 연결하는 역할도 수행한다. ✅

## 15. 중요 포인트 📌

- DTO는 Data Transfer Object의 약자이다.
- VO는 Value Object의 약자이다.
- VO는 데이터베이스 테이블 모양을 그대로 반영한 객체이다.
- VO는 DAO 또는 Mapper 같은 영속 계층에서 사용한다.
- DTO는 DAO를 제외한 Service, Controller, View 계층에서 사용한다.
- DTO는 테이블에 없지만 비즈니스 로직에 필요한 추가 정보를 포함할 수 있다.
- VO와 DTO를 구분하면 계층별 역할이 명확해진다.
- VO와 DTO 사이에는 상호 변환 기능이 필요하다.
- VO → DTO 변환이 필요하다.
- DTO → VO 변환이 필요하다.
- VO는 있는데 DTO는 없는 상황에서는 DTO에 static 변환 메서드를 선언한다.
- `BoardDTO.of(vo)`처럼 정적 팩토리 메서드로 변환할 수 있다.
- DTO 객체가 이미 있는 경우 DTO → VO 변환은 인스턴스 메서드로 작성할 수 있다.

## 정리 ✅

비즈니스 계층에서는 VO와 DTO를 구분해서 사용하는 것이 중요하다.  
VO는 데이터베이스 테이블 모양을 그대로 반영한 객체로 DAO나 Mapper 같은 영속 계층에서 사용하고, DTO는 Service, Controller, View 등 데이터 전달이 필요한 계층에서 사용한다.  
DTO는 테이블에 없는 추가 정보도 포함할 수 있으므로 비즈니스 로직이나 화면 요구사항을 반영하기에 적합하다.  
VO와 DTO를 구분하면 상호 변환 기능이 필요하며, VO에서 DTO로 변환할 때는 DTO 객체가 아직 없으므로 DTO 클래스에 `static` 변환 메서드를 선언하는 방식이 적합하다.  
반대로 DTO에서 VO로 변환할 때는 이미 존재하는 DTO 인스턴스 값을 이용해 VO를 생성하면 된다.
