# ✨ 데이터 입출력2

## 1. 문자 출력

문자 데이터를 출력할 때는 `Writer` 클래스를 사용한다.  
`Writer`는 문자 출력 스트림의 최상위 추상 클래스이다.

즉, 문자 단위로 데이터를 출력할 때 사용하는 기본 클래스라고 볼 수 있다. 📌

## 2. Writer 클래스 주요 메소드

`Writer` 클래스의 주요 메소드는 다음과 같다.

    write(int c)
    write(char[] cbuf)
    write(char[] cbuf, int off, int len)
    write(String str)
    write(String str, int off, int len)
    flush()
    close()

각 메소드의 의미는 다음과 같다.

- `write(int c)` → 한 문자 출력
- `write(char[] cbuf)` → 문자 배열의 모든 문자 출력
- `write(char[] cbuf, int off, int len)` → 배열에서 `cbuf[off]`부터 `len`개까지 출력
- `write(String str)` → 문자열 출력
- `write(String str, int off, int len)` → 문자열에서 `off` 순번부터 `len`개까지 출력
- `flush()` → 버퍼에 남아 있는 모든 문자 출력
- `close()` → 출력 스트림을 닫고 사용 메모리 해제

즉, `Writer`는 문자 하나, 문자 배열, 문자열을 모두 출력할 수 있다.

## 3. file pointer

파일을 읽거나 쓸 때는 file pointer가 사용된다.

file pointer는 현재 읽거나 쓸 위치를 가리킨다.  
`read`나 `write` 작업이 수행되면 처리한 크기만큼 포인터가 자동으로 이동한다.

즉, 파일 입출력은 현재 위치에서 시작하고, 처리한 만큼 다음 위치로 이동하면서 진행된다. 📌

## 4. Reader 클래스

문자 데이터를 입력받을 때는 `Reader` 클래스를 사용한다.  
`Reader`는 문자 입력 스트림의 최상위 추상 클래스이다.

즉, 문자 단위로 데이터를 읽을 때 사용하는 기본 클래스이다.

## 5. Reader 클래스 주요 메소드

`Reader` 클래스의 주요 메소드는 다음과 같다.

    int read()
    int read(char[] cbuf)
    void close()

각 메소드의 의미는 다음과 같다.

- `read()` → 문자 1개를 읽고 리턴
- `read(char[] cbuf)` → 읽은 문자들을 문자 배열에 저장하고 읽은 문자 수 리턴
- `close()` → 입력 스트림을 닫고 사용 메모리 해제

`read()`는 더 이상 읽을 문자가 없으면 `-1`을 리턴한다.  
이 값은 반복문 종료 조건으로 자주 사용된다.

## 6. 보조 스트림

보조 스트림은 기존 스트림에 연결해서 추가 기능을 제공하는 스트림이다.

즉, 단독으로는 사용할 수 없고 기존의 입력 스트림이나 출력 스트림이 있어야 한다.  
기존 스트림은 보조 스트림 생성자의 매개변수로 전달된다.

보조 스트림은 일종의 Wrapper 클래스처럼 기존 스트림을 감싸서 기능을 추가한다. ✅

## 7. 스트림 체인

여러 보조 스트림을 연결해서 사용하는 구조를 스트림 체인이라고 한다.

예를 들어 바이트 단위 스트림을 문자 단위로 바꾸고, 다시 버퍼 기능을 추가할 수 있다.

    FileInputStream fis = new FileInputStream("file.txt");
    InputStreamReader isr = new InputStreamReader(fis);
    BufferedReader br = new BufferedReader(isr);

이 구조는 다음과 같이 이해할 수 있다.

1. `FileInputStream` → byte 단위 입력
2. `InputStreamReader` → byte를 char 단위로 변환
3. `BufferedReader` → 버퍼 기능 추가

즉, 스트림을 여러 겹으로 연결해서 필요한 기능을 조합할 수 있다. 📌

## 8. 타입 변환 보조 스트림

바이트 스트림을 문자 스트림으로 변환할 때는 다음 클래스를 사용한다.

- `InputStreamReader`
- `OutputStreamWriter`

정리하면 다음과 같다.

- `InputStream` → `Reader` 변환: `InputStreamReader`
- `OutputStream` → `Writer` 변환: `OutputStreamWriter`

즉, 바이트 기반 입출력을 문자 기반 입출력으로 바꾸고 싶을 때 사용한다.

## 9. 성능 향상 보조 스트림

입출력 성능을 높이기 위해 버퍼를 사용하는 보조 스트림이 있다.

바이트 스트림용 버퍼 클래스는 다음과 같다.

- `BufferedInputStream`
- `BufferedOutputStream`

문자 스트림용 버퍼 클래스는 다음과 같다.

- `BufferedReader`
- `BufferedWriter`

출력 스트림은 직접 하드 디스크에 데이터를 보내지 않고 메모리 버퍼에 먼저 보낸다.  
그리고 버퍼가 차거나 `flush()`가 호출되면 실제 장치로 출력된다.

입력 스트림도 버퍼를 사용하면 장치 접근 횟수를 줄일 수 있어 성능이 향상된다. ✅

## 10. 대표 보조 스트림 종류

보조 스트림은 기능에 따라 여러 종류가 있다.

- `InputStreamReader` → 바이트 스트림을 문자 스트림으로 변환
- `BufferedInputStream`, `BufferedOutputStream`, `BufferedReader`, `BufferedWriter` → 입출력 성능 향상
- `DataInputStream`, `DataOutputStream` → 기본 타입 데이터 입출력
- `PrintStream`, `PrintWriter` → 줄바꿈 처리, 형식화된 문자열 출력
- `ObjectInputStream`, `ObjectOutputStream` → 객체 입출력

즉, 보조 스트림은 기존 스트림에 변환, 버퍼, 데이터 타입 처리, 출력 편의 기능, 객체 입출력 기능을 추가한다.

## 11. 텍스트 파일 행 단위 읽기

텍스트 파일을 한 줄씩 읽을 때는 `BufferedReader`를 많이 사용한다.

예시는 다음과 같다.

    BufferedReader br = new BufferedReader(new FileReader("file.txt"));

    while (true) {
        String str = br.readLine();

        if (str == null) {
            break;
        }

        // 읽은 행 처리
    }

`readLine()`은 파일에서 한 행씩 읽는다.  
더 이상 읽을 행이 없으면 `null`을 리턴한다.

즉, `null`을 파일 끝 판단 조건으로 사용할 수 있다. 📌

## 12. 기본 타입 스트림

기본 타입 값을 입출력할 때는 `DataInputStream`과 `DataOutputStream`을 사용한다.

형식은 다음과 같다.

    DataInputStream dis = new DataInputStream(바이트 입력 스트림);
    DataOutputStream dos = new DataOutputStream(바이트 출력 스트림);

바이트 스트림에 이 보조 스트림을 연결하면 기본 타입 값을 그대로 입출력할 수 있다.

사용 메소드는 다음과 같은 형태이다.

    read타입명()
    write타입명(타입 v)

예를 들어 다음과 같은 메소드가 있다.

    readInt()
    writeInt(int v)

    readDouble()
    writeDouble(double v)

중요한 점은 읽기와 쓰기 순서가 같아야 한다는 것이다. ⚠️  
쓴 순서와 다른 순서로 읽으면 데이터가 올바르게 복원되지 않을 수 있다.

## 13. PrintStream과 PrintWriter

`PrintStream`과 `PrintWriter`는 프린터와 유사하게 출력하는 메소드를 가진 보조 스트림이다.

특징은 다음과 같다.

- `PrintStream` → 바이트 출력 스트림과 연결
- `PrintWriter` → 문자 출력 스트림과 연결

이 클래스들은 다음과 같은 편의 기능을 제공한다.

- 줄바꿈 처리
- 형식화된 문자열 출력
- `print()`, `println()`, `printf()` 같은 메소드 제공

즉, 사람이 읽기 좋은 형태로 데이터를 출력할 때 유용하다.

## 14. 직렬화

직렬화는 객체를 파일 또는 네트워크로 출력하기 위해 객체의 필드값을 일렬로 늘어선 바이트로 변경하는 과정이다.

즉, 객체 상태를 바이트 형태로 바꾸는 것이다.

객체는 메모리 안에서 여러 필드와 참조 구조를 가지고 있기 때문에 그대로 파일에 저장하거나 네트워크로 보낼 수 없다.  
그래서 바이트 흐름으로 바꾸는 직렬화 과정이 필요하다. 📌

## 15. 역직렬화

역직렬화는 직렬화된 바이트 데이터를 다시 객체의 필드값으로 복원하는 과정이다.

즉, 파일이나 네트워크에서 읽어온 바이트 데이터를 다시 객체 상태로 되돌린다.

정리하면 다음과 같다.

- 직렬화 → 객체를 바이트로 변환
- 역직렬화 → 바이트를 객체로 복원

객체 입출력에는 보통 `ObjectOutputStream`, `ObjectInputStream`이 사용된다.

## 16. 중요 포인트 📌

- `Writer`는 문자 출력 스트림의 최상위 클래스이다.
- `Reader`는 문자 입력 스트림의 최상위 클래스이다.
- `write()`는 문자, 문자 배열, 문자열을 출력할 수 있다.
- `flush()`는 버퍼에 남은 데이터를 출력한다.
- `close()`는 스트림을 닫고 사용 메모리를 해제한다.
- file pointer는 읽기/쓰기 작업 크기만큼 자동 이동한다.
- 보조 스트림은 기존 스트림에 연결해서 추가 기능을 제공한다.
- 보조 스트림은 단독으로 생성할 수 없고 기존 스트림이 필요하다.
- 스트림 체인을 통해 여러 보조 스트림을 연결할 수 있다.
- `InputStreamReader`는 바이트 입력 스트림을 문자 입력 스트림으로 변환한다.
- `OutputStreamWriter`는 바이트 출력 스트림을 문자 출력 스트림으로 변환한다.
- 버퍼 보조 스트림은 입출력 성능을 향상시킨다.
- `BufferedReader.readLine()`은 한 줄씩 읽고, 더 이상 읽을 줄이 없으면 `null`을 리턴한다.
- `DataInputStream`, `DataOutputStream`은 기본 타입 데이터 입출력에 사용한다.
- 기본 타입 스트림은 읽기와 쓰기 순서가 중요하다.
- `PrintStream`, `PrintWriter`는 형식화된 문자열 출력에 유용하다.
- 직렬화는 객체를 바이트로 변환하는 과정이다.
- 역직렬화는 바이트를 객체로 복원하는 과정이다.

## 정리 ✅

데이터 입출력2에서는 문자 스트림과 보조 스트림이 핵심이다.  
`Writer`는 문자 출력, `Reader`는 문자 입력을 담당하며, 문자 배열이나 문자열 단위로 데이터를 처리할 수 있다.  
보조 스트림은 기존 스트림을 감싸서 타입 변환, 버퍼 성능 향상, 기본 타입 입출력, 형식화된 출력, 객체 입출력 같은 기능을 추가한다.  
특히 `InputStreamReader`와 `OutputStreamWriter`는 바이트 스트림과 문자 스트림을 연결하고, `BufferedReader`는 텍스트 파일을 행 단위로 읽을 때 많이 사용된다.  
시험 대비에서는 문자 스트림의 주요 메소드, 보조 스트림의 역할, 스트림 체인, 기본 타입 스트림의 읽기/쓰기 순서, 직렬화와 역직렬화 개념을 함께 정리해 두는 것이 중요하다.
