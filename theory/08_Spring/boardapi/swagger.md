# ✨ Swagger

## 1. Swagger란

Swagger는 개발한 REST API를 문서화하기 위한 도구이다.

API 서버를 만들면 어떤 URL로 요청해야 하는지, 어떤 HTTP 메서드를 사용하는지, 어떤 파라미터가 필요한지, 어떤 응답이 오는지 정리해야 한다.  
Swagger를 사용하면 이런 API 정보를 자동으로 문서화할 수 있다. 📌

즉, Swagger는 REST API 명세를 보기 좋게 정리하고 테스트할 수 있게 도와주는 도구이다.

## 2. Swagger의 역할

Swagger를 사용하면 문서화된 내용을 통해 API를 관리할 수 있고, 직접 API 호출 테스트도 할 수 있다.

즉, API 문서를 보는 것에서 끝나지 않고 화면에서 요청을 보내 응답을 확인할 수 있다.

Swagger는 API 테스트 도구인 Postman, Talend API Tester와 비슷한 역할도 한다. ✅

정리하면 Swagger의 역할은 다음과 같다.

- REST API 문서화
- API 엔드포인트 정리
- 요청 파라미터 설명
- 응답 코드와 응답 객체 설명
- API 호출 테스트

## 3. Swagger 라이브러리 종류

Spring 프로젝트에서 Swagger를 사용할 때 대표적인 라이브러리는 두 가지가 있다.

- Spring-Fox
- Spring-Doc

두 라이브러리 모두 Spring 기반 프로젝트에서 Swagger 문서를 생성하는 데 사용된다.

즉, 프로젝트 환경이나 Spring 버전에 따라 적절한 라이브러리를 선택해서 사용할 수 있다. 📌

## 4. Spring-Fox

Spring-Fox는 Spring MVC 기반 프로젝트에서 Swagger 문서를 생성할 때 많이 사용되던 라이브러리이다.

기존 Spring Legacy 프로젝트나 예전 Spring Boot 환경에서 자주 사용된다.

Spring-Fox를 사용할 때는 `@Api`, `@ApiOperation`, `@ApiParam`, `@ApiResponses`, `@ApiModel` 같은 어노테이션을 이용해 API 문서를 보강할 수 있다.

## 5. Spring-Doc

Spring-Doc은 OpenAPI 3 기반 문서화를 지원하는 라이브러리이다.

비교적 최신 Spring Boot 환경에서는 Spring-Doc을 많이 사용한다.

다만 현재 필기 내용은 `@Api`, `@ApiOperation` 같은 어노테이션을 기준으로 하고 있으므로 Spring-Fox 방식의 Swagger 어노테이션을 중심으로 정리한다. 📌

## 6. @RestController에 정보 설정하기

REST API 컨트롤러에 Swagger 정보를 설정할 때는 `@Api` 어노테이션을 사용할 수 있다.

형식은 다음과 같다.

    @Api(tags = "API 타이틀")

예시는 다음과 같다.

    @RestController
    @RequestMapping("/api/board")
    @Api(tags = "게시판 API")
    public class BoardApiController {
    }

`tags` 속성은 Swagger 문서에서 API 그룹의 제목처럼 사용된다.

즉, 컨트롤러 단위로 API를 묶어서 보여줄 때 사용한다. ✅

## 7. API 엔드포인트 설명

각 API 메서드의 설명을 작성할 때는 `@ApiOperation`을 사용한다.

형식은 다음과 같다.

    @ApiOperation(value = "api 명", notes = "설명")

예시는 다음과 같다.

    @GetMapping("/{no}")
    @ApiOperation(value = "게시글 조회", notes = "게시글 번호로 게시글 상세 정보를 조회합니다.")
    public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
        ...
    }

`value`는 API의 간단한 이름이고, `notes`는 API에 대한 자세한 설명이다. 📌

## 8. @ApiParam

`@ApiParam`은 엔드포인트 파라미터를 설명할 때 사용한다.

주로 다음 어노테이션 앞에 함께 작성한다.

- `@PathVariable`
- `@RequestBody`
- 요청 파라미터

예시는 다음과 같다.

    public ResponseEntity<BoardDTO> get(
            @ApiParam(value = "게시글 번호", required = true, example = "1")
            @PathVariable Long no) {
        ...
    }

즉, API 사용자가 어떤 값을 넣어야 하는지 Swagger 문서에 설명하기 위해 사용한다. ✅

## 9. @ApiParam 주요 속성

`@ApiParam`의 주요 속성은 다음과 같다.

- `value`
- `required`
- `example`

각 의미는 다음과 같다.

- `value` → 엔드포인트 파라미터의 간략한 설명
- `required` → 필수 여부
- `example` → 파라미터의 예시 값 제공

예시는 다음과 같다.

    @ApiParam(value = "게시글 ID", required = true, example = "1")

이렇게 작성하면 Swagger 문서에서 해당 파라미터가 어떤 의미인지 쉽게 확인할 수 있다. 📌

## 10. @ApiResponses

`@ApiResponses`는 API 응답 정보를 여러 개 작성할 때 사용한다.

형식은 다음과 같다.

    @ApiResponses(value = {
        ...
    })

`@ApiResponses` 안에는 `@ApiResponse` 배열을 넣는다.

즉, 하나의 API에서 발생할 수 있는 여러 응답 코드를 문서화할 수 있다.

## 11. @ApiResponse

`@ApiResponse`는 개별 응답 코드에 대한 설명을 작성할 때 사용한다.

주요 속성은 다음과 같다.

- `code`
- `message`
- `response`

각 의미는 다음과 같다.

- `code` → 응답 코드
- `message` → 응답 코드의 의미 설명
- `response` → 응답 객체 class 정보

예시는 다음과 같다.

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "조회 성공", response = BoardDTO.class),
        @ApiResponse(code = 404, message = "게시글 없음")
    })

즉, API 호출 결과로 어떤 상태 코드가 올 수 있고, 어떤 객체가 응답되는지 문서화할 수 있다. ✅

## 12. DTO 모델 문서화

API 응답이나 요청에 사용되는 DTO 모델도 Swagger 문서에 설명할 수 있다.

DTO 클래스에는 `@ApiModel`을 사용한다.

    @ApiModel(description = "게시글 DTO")
    public class BoardDTO {
    }

이 어노테이션은 DTO가 어떤 역할을 하는 객체인지 설명한다.

즉, API 문서에서 요청/응답 body 구조를 이해하기 쉽게 만든다. 📌

## 13. @ApiModelProperty

DTO의 각 필드에는 `@ApiModelProperty`를 사용할 수 있다.

형식은 다음과 같다.

    @ApiModelProperty(value = "게시글 ID", example = "1")

예시는 다음과 같다.

    @ApiModel(description = "게시글 DTO")
    public class BoardDTO {

        @ApiModelProperty(value = "게시글 ID", example = "1")
        private Long no;

        @ApiModelProperty(value = "게시글 제목", example = "테스트 제목")
        private String title;
    }

`value`는 필드 설명이고, `example`은 예시 값이다.

## 14. 숫자형 example 주의

숫자형 필드의 경우 `example`에 반드시 숫자 값을 지정해야 한다.

예를 들어 게시글 ID가 숫자형이라면 다음처럼 작성한다.

    @ApiModelProperty(value = "게시글 ID", example = "1")
    private Long no;

숫자형 필드에 숫자로 변환할 수 없는 예시 값을 넣으면 문서 생성이나 테스트 과정에서 문제가 생길 수 있다. ⚠️

## 15. Swagger 문서 작성 흐름

Swagger 문서 작성 흐름은 다음과 같이 정리할 수 있다.

1. REST API 컨트롤러에 `@Api`로 API 그룹 제목을 작성한다.
2. 각 엔드포인트 메서드에 `@ApiOperation`으로 API 설명을 작성한다.
3. 파라미터에는 `@ApiParam`으로 설명, 필수 여부, 예시 값을 작성한다.
4. 응답 정보는 `@ApiResponses`와 `@ApiResponse`로 작성한다.
5. 요청/응답 DTO에는 `@ApiModel`을 붙인다.
6. DTO 필드에는 `@ApiModelProperty`로 필드 설명과 예시 값을 작성한다.

즉, 컨트롤러, 메서드, 파라미터, 응답, DTO 모델을 각각 문서화할 수 있다. ✅

## 16. Swagger 어노테이션 정리

Swagger에서 자주 사용하는 어노테이션은 다음과 같다.

| 어노테이션          | 사용 위치            | 역할                |
| ------------------- | -------------------- | ------------------- |
| `@Api`              | Controller 클래스    | API 그룹 제목 설정  |
| `@ApiOperation`     | Controller 메서드    | API 엔드포인트 설명 |
| `@ApiParam`         | 파라미터             | 파라미터 설명       |
| `@ApiResponses`     | Controller 메서드    | 여러 응답 정보 묶음 |
| `@ApiResponse`      | `@ApiResponses` 내부 | 개별 응답 코드 설명 |
| `@ApiModel`         | DTO 클래스           | DTO 모델 설명       |
| `@ApiModelProperty` | DTO 필드             | DTO 필드 설명       |

## 17. 중요 포인트 📌

- Swagger는 개발한 REST API를 문서화하는 도구이다.
- Swagger 문서를 통해 API 관리와 호출 테스트가 가능하다.
- Swagger는 Postman, Talend API Tester처럼 API 테스트에도 활용할 수 있다.
- 대표 라이브러리는 Spring-Fox와 Spring-Doc이 있다.
- `@Api(tags = "API 타이틀")`은 RestController에 API 그룹 정보를 설정한다.
- `@ApiOperation(value = "api 명", notes = "설명")`은 엔드포인트를 설명한다.
- `@ApiParam`은 `@PathVariable`, `@RequestBody` 같은 파라미터를 설명한다.
- `@ApiParam`의 주요 속성은 `value`, `required`, `example`이다.
- `@ApiResponses`는 `@ApiResponse`의 배열이다.
- `@ApiResponse`는 `code`, `message`, `response` 속성을 가진다.
- `@ApiModel`은 DTO 클래스에 지정한다.
- `@ApiModelProperty`는 DTO 필드에 지정한다.
- 숫자형 필드는 `example`에 숫자 값을 지정해야 한다.

## 정리 ✅

Swagger는 REST API를 문서화하고 테스트할 수 있게 도와주는 도구이다.  
Spring 프로젝트에서는 Spring-Fox 또는 Spring-Doc 라이브러리를 사용해 Swagger 문서를 구성할 수 있으며, 현재 필기에서는 Spring-Fox 방식의 어노테이션을 중심으로 정리했다.  
`@Api`는 컨트롤러 단위의 API 그룹 제목을 설정하고, `@ApiOperation`은 각 엔드포인트의 이름과 설명을 작성한다.  
`@ApiParam`은 요청 파라미터를 설명하고, `@ApiResponses`와 `@ApiResponse`는 응답 코드와 응답 객체를 문서화한다.  
또한 DTO 클래스에는 `@ApiModel`, DTO 필드에는 `@ApiModelProperty`를 사용해 요청/응답 모델 구조를 문서화할 수 있다.
