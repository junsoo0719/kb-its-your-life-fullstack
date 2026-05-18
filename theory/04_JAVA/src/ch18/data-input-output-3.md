# ✨ 데이터 입출력3

## 1. 직렬화

직렬화는 객체를 파일이나 네트워크로 출력하기 위해 객체의 필드값을 바이트 형태로 변환하는 과정이다.

자바에서는 객체 직렬화에 `ObjectOutputStream`을 사용한다.  
객체를 출력할 때는 `writeObject()` 메소드를 사용한다.

    ObjectOutputStream oos = new ObjectOutputStream(출력스트림);
    oos.writeObject(객체);

하지만 모든 객체가 직렬화될 수 있는 것은 아니다. ⚠️  
직렬화하려는 객체는 반드시 `Serializable` 인터페이스를 구현해야 한다.

## 2. Serializable 인터페이스

`Serializable`은 객체가 직렬화 가능하다는 것을 표시하는 인터페이스이다.

이 인터페이스는 특별한 메소드를 가지고 있지 않다.  
이처럼 기능 메소드 없이 표시 역할만 하는 인터페이스를 Marker 인터페이스라고 한다.

즉, `Serializable`을 구현했다는 것은 "이 객체는 직렬화해도 된다"는 표시를 남기는 것이다. 📌

    public class Member implements Serializable {
        ...
    }

## 3. 직렬화 대상

직렬화에서는 객체의 모든 내용이 저장되는 것이 아니다.

기본적으로 직렬화 대상은 필드이다.  
메소드는 직렬화 대상에서 제외된다.

즉,

- 필드 → 직렬화 대상
- 메소드 → 직렬화 대상 아님

으로 이해하면 된다.

객체의 동작은 클래스 파일에 정의되어 있고, 직렬화는 객체의 상태값을 저장하는 것이기 때문이다.

## 4. 역직렬화

역직렬화는 직렬화된 바이트 데이터를 다시 객체로 복원하는 과정이다.

자바에서는 `ObjectInputStream`을 사용하고, `readObject()` 메소드로 객체를 읽는다.

    ObjectInputStream ois = new ObjectInputStream(입력스트림);
    객체타입 변수 = (객체타입) ois.readObject();

`readObject()`의 리턴 타입은 `Object`이므로 원래 객체 타입으로 강제 타입 변환이 필요하다.

즉, 역직렬화할 때는 저장된 객체의 실제 타입을 알고 있어야 한다. 📌

## 5. Serializable과 멤버 처리

`Serializable`을 구현한 객체는 인스턴스 멤버가 직렬화에 자동으로 포함된다.

이때 접근 제한자는 상관없다.

즉,

- `public` 필드
- `private` 필드
- `protected` 필드
- default 필드

모두 인스턴스 필드라면 직렬화 대상에 포함될 수 있다.

반면 `static` 멤버는 객체의 인스턴스 상태가 아니라 클래스에 속하는 값이므로 직렬화에서 제외된다. ⚠️

## 6. transient 키워드

직렬화 대상에서 특정 필드를 제외하고 싶다면 `transient` 키워드를 사용한다.

    private transient String password;

이렇게 선언된 필드는 직렬화되지 않는다.

즉, 비밀번호처럼 저장하면 안 되는 정보나, 복원할 필요가 없는 임시 데이터에 사용할 수 있다. ✅

## 7. File 클래스

`File` 클래스는 실제 파일 데이터를 담는 클래스가 아니다.  
파일이나 디렉토리의 메타 정보를 다루기 위한 클래스이다.

즉, `File` 객체는 파일의 이름, 경로, 존재 여부, 권한, 크기 같은 정보를 다룬다.

기본 생성 방식은 다음과 같다.

    File file = new File("경로");

경로는 절대 경로와 상대 경로 모두 가능하다.

또한 다음 생성자도 많이 사용한다.

    File file = new File(디렉토리File객체, "파일명");

이때 첫 번째 `File`은 디렉토리에 대한 파일 객체이고, 두 번째 문자열은 파일명이다. 📌

## 8. 파일 또는 디렉토리 존재 여부

파일이나 디렉토리가 존재하는지 확인할 때는 `exists()`를 사용한다.

    file.exists();

`exists()`가 `false`를 리턴하면 파일이나 디렉토리가 없다는 뜻이다.  
이 경우 다음 메소드를 사용해서 새로 만들 수 있다.

    createNewFile()
    mkdir()
    mkdirs()

각 메소드의 의미는 다음과 같다.

- `createNewFile()` → 새 파일 생성
- `mkdir()` → 새 디렉토리 생성
- `mkdirs()` → 경로상에 없는 모든 디렉토리 생성

즉, 중간 경로까지 한 번에 만들고 싶다면 `mkdirs()`를 사용한다.

## 9. 파일 삭제와 권한

파일이나 디렉토리가 존재하면 `delete()`로 삭제할 수 있다.

    file.delete();

삭제하려면 쓰기 권한이 필요하다. ⚠️

권한 확인 메소드는 다음과 같다.

    canExecute()
    canRead()
    canWrite()

의미는 다음과 같다.

- `canExecute()` → 실행 권한 확인
- `canRead()` → 읽기 권한 확인
- `canWrite()` → 쓰기 권한 확인

디렉토리에서 실행 권한은 탐색 권한과 관련된다.  
또한 `dir`, `ls`처럼 디렉토리 목록을 보려면 읽기 권한이 필요하다.

## 10. File 정보 조회 메소드

`File` 클래스는 파일이나 디렉토리의 정보를 조회하는 다양한 메소드를 제공한다.

대표적인 메소드는 다음과 같다.

    getName()
    getParent()
    getParentFile()
    getPath()
    isDirectory()
    isFile()
    isHidden()
    lastModified()
    length()

각 메소드의 의미는 다음과 같다.

- `getName()` → 파일 이름
- `getParent()` → 부모 디렉토리 경로 문자열
- `getParentFile()` → 부모 디렉토리를 `File` 객체로 생성 후 리턴
- `getPath()` → 전체 경로
- `isDirectory()` → 디렉토리 여부
- `isFile()` → 파일 여부
- `isHidden()` → 숨김 파일 여부
- `lastModified()` → 마지막 수정 시간
- `length()` → 파일 크기

즉, `File`은 파일 자체의 내용보다 파일의 상태와 정보를 다루는 데 사용된다. 📌

## 11. 디렉토리 목록 조회

디렉토리 안의 파일 목록을 얻을 때는 다음 메소드를 사용할 수 있다.

    list()
    list(FilenameFilter filter)
    listFiles()
    listFiles(FilenameFilter filter)

차이는 다음과 같다.

- `list()` → 파일 이름을 문자열 배열로 리턴
- `listFiles()` → 파일들을 `File` 객체 배열로 리턴
- `FilenameFilter` 사용 → 조건에 맞는 파일만 필터링

`FilenameFilter`는 함수형 인터페이스이므로 람다식으로 사용할 수 있다. ✅

예를 들면 특정 확장자만 필터링할 때 사용할 수 있다.

## 12. Files 클래스

`Files` 클래스는 파일 작업을 더 편리하게 처리할 수 있는 유틸리티 클래스이다.

자주 사용하는 기능은 다음과 같다.

- `copy()`
- `list()`
- `newBufferedReader()`
- `newBufferedWriter()`
- `readAllBytes()`
- `lines()`
- `readAllLines()`

특히 파일 복사에는 `copy()`가 많이 사용된다.

`Files` 클래스의 메소드들은 주로 `Path` 객체를 매개변수로 사용한다. 📌

## 13. 전통적인 I/O와 NIO.2

전통적인 I/O는 동기식 방식이다.

반면 NIO.2 라이브러리는 동기와 비동기를 모두 지원한다.

즉,

- 전통적인 I/O → 동기식 중심
- NIO.2 → 동기와 비동기 모두 지원

으로 이해하면 된다.

`Files`, `Path`, `Paths`는 NIO.2와 관련된 파일 처리 기능이다.

## 14. Path 객체

`Path`는 파일이나 디렉토리의 경로를 표현하는 객체이다.

다만 `Path`는 `File`의 단순 대체 클래스는 아니다.  
서로 변환이 가능하며, 목적에 따라 함께 사용될 수 있다.

변환 메소드는 다음과 같다.

    file.toPath()
    path.toFile()

즉,

- `File` → `Path`: `toPath()`
- `Path` → `File`: `toFile()`

로 변환할 수 있다.

## 15. Paths 유틸리티 클래스

`Path` 객체는 `new`로 직접 생성하지 않는다.

대신 `Paths` 유틸리티 클래스를 사용해서 생성한다.

    Path path = Paths.get("경로");

즉, `Path`는 생성자를 직접 호출하는 방식이 아니라 `Paths.get()`을 통해 얻는 방식이다. ⚠️

## 16. 중요 포인트 📌

- 직렬화는 객체의 필드값을 바이트로 변환하는 과정이다.
- 역직렬화는 바이트 데이터를 다시 객체로 복원하는 과정이다.
- 객체 직렬화에는 `ObjectOutputStream.writeObject()`를 사용한다.
- 객체 역직렬화에는 `ObjectInputStream.readObject()`를 사용한다.
- 직렬화 가능한 객체는 `Serializable`을 구현해야 한다.
- `Serializable`은 Marker 인터페이스이다.
- 메소드는 직렬화 대상이 아니고, 필드만 직렬화 대상이다.
- 인스턴스 멤버는 접근 제한자와 관계없이 직렬화에 포함된다.
- `static` 멤버는 직렬화에서 제외된다.
- `transient` 필드는 직렬화에서 제외된다.
- `File`은 실제 데이터가 아니라 파일 메타 정보를 다루는 클래스이다.
- `exists()`로 파일 존재 여부를 확인할 수 있다.
- `createNewFile()`, `mkdir()`, `mkdirs()`로 파일과 디렉토리를 만들 수 있다.
- `delete()`는 쓰기 권한이 필요하다.
- `list()`와 `listFiles()`로 디렉토리 목록을 얻을 수 있다.
- `FilenameFilter`는 함수형 인터페이스라 람다식으로 사용할 수 있다.
- `Files` 클래스는 파일 작업을 편리하게 처리하는 유틸리티 클래스이다.
- `Files`의 주요 메소드는 `Path`를 매개변수로 사용한다.
- `Path`와 `File`은 `toPath()`, `toFile()`로 서로 변환할 수 있다.
- `Path`는 `new`로 만들지 않고 `Paths.get()`으로 생성한다.

## 정리 ✅

데이터 입출력3에서는 객체 직렬화와 파일 메타 정보 처리, NIO.2의 파일 처리 기능이 핵심이다.  
직렬화는 객체의 필드값을 바이트로 바꾸는 과정이고, 역직렬화는 바이트를 다시 객체로 복원하는 과정이다.  
직렬화하려는 객체는 반드시 `Serializable`을 구현해야 하며, 인스턴스 필드는 직렬화 대상이지만 `static` 멤버와 `transient` 필드는 제외된다.  
`File` 클래스는 실제 파일 데이터가 아니라 파일의 경로, 존재 여부, 권한, 크기 같은 메타 정보를 다루며, `Files` 클래스는 `Path` 기반으로 파일 복사, 읽기, 쓰기 같은 작업을 더 편리하게 제공한다.  
시험 대비에서는 `Serializable`, `transient`, `writeObject()`, `readObject()`, `File` 주요 메소드, `Files`와 `Path`의 관계를 함께 정리해 두는 것이 중요하다.
