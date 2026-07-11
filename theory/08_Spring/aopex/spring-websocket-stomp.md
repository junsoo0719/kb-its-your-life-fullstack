# ✨ Spring Web Socket, STOMP

## 1. HTTP 통신 특징

HTTP는 웹에서 가장 많이 사용되는 통신 방식이다.

하지만 HTTP는 기본적으로 실시간 양방향 통신에 적합한 구조는 아니다.

HTTP의 대표적인 특징은 다음과 같다.

- 비연결성
- 무상태성
- 단방향 통신

이러한 특징 때문에 채팅, 실시간 알림, 실시간 위치 공유처럼 서버와 클라이언트가 계속 데이터를 주고받아야 하는 서비스에는 한계가 있다. 📌

## 2. 비연결성

비연결성은 connectionless라고 한다.

HTTP는 클라이언트가 서버와 연결을 맺고 요청을 보낸 뒤, 서버로부터 응답을 받으면 연결을 끊는다.

즉, 요청과 응답이 끝나면 연결이 유지되지 않는다.

흐름은 다음과 같다.

1. 클라이언트가 서버에 요청
2. 서버가 응답
3. 연결 종료

이 구조는 일반적인 웹 페이지 요청에는 적합하지만, 계속 연결을 유지해야 하는 실시간 통신에는 불리하다. ⚠️

## 3. 무상태성

무상태성은 stateless라고 한다.

서버가 클라이언트의 상태를 기본적으로 저장하지 않는다는 의미이다.

즉, 이전 요청에서 어떤 사용자가 어떤 작업을 했는지 서버가 자동으로 기억하지 않는다.

그래서 로그인 상태나 사용자 정보를 유지하려면 세션, 쿠키, 토큰 같은 추가적인 방식이 필요하다. 📌

## 4. 단방향 통신

HTTP는 기본적으로 클라이언트가 요청을 보내고 서버가 응답하는 구조이다.

즉, 클라이언트가 먼저 요청해야 서버가 응답할 수 있다.

서버가 먼저 클라이언트에게 데이터를 보내는 구조는 기본 HTTP만으로는 자연스럽지 않다.

따라서 서버에서 실시간으로 메시지를 밀어 보내야 하는 채팅 서비스에는 적합하지 않다. ✅

## 5. 채팅과 HTTP의 한계

채팅 서비스에서는 다음 기능이 필요하다.

- 사용자가 메시지를 보내면 상대방에게 즉시 전달
- 서버가 클라이언트에게 실시간 메시지 전송
- 연결을 유지한 상태에서 양방향 통신
- 여러 사용자에게 동시에 메시지 전달

하지만 HTTP는 요청 후 응답이 끝나면 연결을 끊는 구조이므로, 실시간 채팅을 구현하기 어렵다.

이 문제를 해결하기 위해 WebSocket을 사용할 수 있다. 📌

## 6. 웹소켓 프로토콜

WebSocket은 클라이언트와 서버 사이에서 양방향 통신을 가능하게 하는 프로토콜이다.

HTTP처럼 요청과 응답을 한 번만 주고받고 끝나는 것이 아니라, 연결을 유지한 상태에서 양쪽이 자유롭게 메시지를 주고받을 수 있다.

즉, WebSocket은 실시간 통신에 적합한 방식이다. ✅

## 7. 클라이언트 주도 양방향 통신

WebSocket은 클라이언트가 먼저 연결을 요청하면서 시작된다.

처음 연결은 클라이언트가 주도하지만, 연결이 성립된 이후에는 클라이언트와 서버가 서로 메시지를 주고받을 수 있다.

즉, WebSocket은 다음과 같은 구조를 가진다.

- 클라이언트가 서버에 연결 요청
- 연결 성공 후 연결 유지
- 클라이언트 → 서버 메시지 전송 가능
- 서버 → 클라이언트 메시지 전송 가능

이 구조 덕분에 채팅이나 실시간 알림을 구현하기 좋다. 📌

## 8. WebSocket과 HTTP

WebSocket은 HTTP 환경에서 동작 가능하게 디자인되었다.

기본적으로 80, 443 포트를 사용할 수 있다.

- 80 포트 → 일반 웹 통신
- 443 포트 → HTTPS 기반 보안 통신

WebSocket 프로토콜은 보통 다음과 같이 표현된다.

- `ws://`
- `wss://`

`ws`는 일반 WebSocket이고, `wss`는 보안 연결을 사용하는 WebSocket이다. ✅

## 9. Upgrade 헤더

WebSocket 연결은 처음부터 완전히 별도의 방식으로 시작되는 것이 아니다.

처음에는 일반 HTTP 요청으로 시작한다.

클라이언트가 HTTP 요청에 `Upgrade` 헤더를 포함하여 서버에 전송하면, 서버가 이를 받아 WebSocket 프로토콜로 전환할 수 있다.

즉, HTTP 연결을 WebSocket 연결로 업그레이드하는 방식이다. 📌

개념적으로는 다음 흐름이다.

1. 클라이언트가 HTTP 요청 전송
2. 요청 헤더에 `Upgrade: websocket` 포함
3. 서버가 WebSocket 전환을 허용
4. HTTP 연결이 WebSocket 프로토콜로 변환
5. WebSocket interaction 시작

## 10. WebSocket Interaction

WebSocket interaction은 WebSocket 연결이 성립된 이후 클라이언트와 서버가 메시지를 주고받는 상호작용을 의미한다.

이 단계에서는 일반 HTTP 요청/응답 방식이 아니라, 연결을 유지한 상태에서 양방향 메시지를 주고받는다.

즉, 채팅 메시지나 실시간 알림을 즉시 전달할 수 있다. ✅

## 11. STOMP

STOMP는 Simple Text Oriented Messaging Protocol의 약자이다.

간단한 메시지를 전송하기 위한 프로토콜이다.

WebSocket은 양방향 통신을 가능하게 해 주지만, 메시지를 어떤 형식으로 주고받을지에 대한 규칙은 별도로 필요할 수 있다.

이때 STOMP를 사용하면 메시지를 일정한 형식으로 주고받을 수 있다. 📌

## 12. STOMP를 사용하는 이유

WebSocket만 사용하면 메시지 형식, 목적지, 구독, 발행 등을 직접 설계해야 한다.

STOMP를 사용하면 다음과 같은 메시징 구조를 쉽게 사용할 수 있다.

- 메시지 발행
- 메시지 구독
- 메시지 브로커 사용
- 목적지 기반 메시지 전달
- frame 기반 메시지 처리

즉, STOMP는 WebSocket 위에서 메시징 규칙을 제공하는 프로토콜이라고 이해할 수 있다. ✅

## 13. 메시지 브로커

STOMP는 메시지 브로커를 사용한다.

브로커는 publisher가 보낸 메시지를 subscriber에게 전달하는 중간 관리자 역할을 한다.

즉, 메시지를 보내는 사람과 받는 사람을 직접 연결하지 않고, broker가 중간에서 메시지를 전달한다. 📌

## 14. Publisher

Publisher는 메시지를 전송하는 사람 또는 객체이다.

예를 들어 채팅방에서 사용자가 메시지를 입력하고 전송하면, 그 사용자는 publisher 역할을 한다.

즉, publisher는 메시지를 발행하는 주체이다.

## 15. Subscriber

Subscriber는 메시지를 수신하는 사람 또는 객체이다.

예를 들어 특정 채팅방을 보고 있는 사용자가 해당 채팅방 topic을 구독하고 있다면, 그 사용자는 subscriber 역할을 한다.

즉, subscriber는 특정 메시지 목적지를 구독하고 메시지를 전달받는 주체이다. ✅

## 16. Broker

Broker는 publisher가 발행한 메시지를 subscriber에게 전달하는 역할을 한다.

흐름은 다음과 같다.

1. Subscriber가 특정 topic을 구독
2. Publisher가 해당 topic으로 메시지 발행
3. Broker가 해당 topic을 구독 중인 subscriber에게 메시지 전달

즉, broker는 메시지 전달을 중개하는 핵심 요소이다. 📌

## 17. Publisher - Subscriber 방식

STOMP는 publisher-subscriber 방식을 사용한다.

이 방식에서는 메시지를 보내는 쪽과 받는 쪽이 직접 연결되는 것이 아니라, topic이나 destination을 기준으로 메시지를 주고받는다.

예를 들어 채팅방 `room1`이 있다면 다음과 같이 이해할 수 있다.

- 사용자는 `room1` topic을 구독한다.
- 누군가 `room1` topic에 메시지를 발행한다.
- broker가 `room1`을 구독 중인 사용자에게 메시지를 전달한다.

즉, pub-sub 구조는 여러 사용자에게 실시간 메시지를 전달하기에 적합하다. ✅

## 18. STOMP frame

STOMP는 frame 기반 프로토콜이다.

STOMP frame은 메시지를 일정한 구조로 표현한 것이다.

STOMP frame은 다음 요소로 구성된다.

- command
- header
- body

즉, STOMP 메시지는 명령, 부가 정보, 실제 메시지 내용을 나누어 표현한다. 📌

## 19. STOMP frame 구조

STOMP frame의 기본 구조는 다음과 같다.

    COMMAND
    header1:value1
    header2:value2

    Body^@

각 구성 요소의 의미는 다음과 같다.

- `COMMAND` → 수행할 명령
- `header` → 메시지에 대한 부가 정보
- `body` → 실제 메시지 내용
- `^@` → frame 종료를 나타내는 문자

즉, STOMP는 텍스트 기반 frame 형식으로 메시지를 주고받는다. ✅

## 20. Command

Command는 STOMP frame에서 수행할 동작을 나타낸다.

예를 들어 다음과 같은 동작을 표현할 수 있다.

- 연결
- 구독
- 메시지 전송
- 연결 해제

즉, command는 이 frame이 어떤 목적의 메시지인지 알려준다. 📌

## 21. Header

Header는 메시지에 대한 부가 정보를 담는다.

예를 들어 메시지를 보낼 목적지, 구독 ID, content-type 같은 값이 들어갈 수 있다.

형식은 key-value 구조이다.

    header1:value1
    header2:value2

즉, header는 메시지를 처리하는 데 필요한 설정 정보를 담는다.

## 22. Body

Body는 실제 메시지 내용을 담는다.

예를 들어 채팅 메시지라면 body에 사용자가 입력한 메시지 내용이 들어갈 수 있다.

    안녕하세요

즉, body는 subscriber에게 전달될 실제 데이터 영역이다. ✅

## 23. topic

`topic`은 메시지의 카테고리를 지정하는 문자열이다.

Subscriber는 특정 topic을 구독하고, Publisher는 특정 topic으로 메시지를 발행한다.

예를 들어 채팅방마다 topic을 나눌 수 있다.

    /topic/chat/room1
    /topic/chat/room2

이렇게 하면 `room1`을 구독한 사용자에게는 `room1` 메시지만 전달되고, `room2`를 구독한 사용자에게는 `room2` 메시지만 전달된다. 📌

## 24. WebSocket과 STOMP의 관계

WebSocket은 클라이언트와 서버 사이의 양방향 통신 연결을 제공한다.

STOMP는 그 연결 위에서 메시지를 어떤 규칙으로 주고받을지 정의한다.

정리하면 다음과 같다.

- WebSocket → 실시간 양방향 통신 연결
- STOMP → 메시지 전송 규칙과 pub-sub 구조 제공
- Broker → 발행된 메시지를 구독자에게 전달

즉, WebSocket은 통신 통로이고, STOMP는 메시지 전달 규칙이다. ✅

## 25. 중요 포인트 📌

- HTTP는 비연결성, 무상태성, 단방향 통신 특징을 가진다.
- HTTP는 요청과 응답이 끝나면 연결을 끊는다.
- HTTP는 서버가 클라이언트 상태를 기본적으로 저장하지 않는다.
- HTTP는 클라이언트 요청이 있어야 서버가 응답하는 구조이다.
- 채팅 같은 실시간 통신에는 기본 HTTP 구조가 적합하지 않다.
- WebSocket은 클라이언트 주도 양방향 통신을 지원한다.
- WebSocket은 HTTP에서 동작 가능하도록 디자인되었다.
- WebSocket은 80, 443 포트를 사용할 수 있다.
- HTTP 요청에 `Upgrade` 헤더를 포함하면 WebSocket 프로토콜로 전환될 수 있다.
- STOMP는 Simple Text Oriented Messaging Protocol의 약자이다.
- STOMP는 간단한 메시지를 전송하기 위한 프로토콜이다.
- STOMP는 메시지 브로커와 publisher-subscriber 방식을 사용한다.
- Publisher는 메시지 전송자이다.
- Subscriber는 메시지 수신자이다.
- Broker는 publisher가 발행한 메시지를 subscriber에게 전달한다.
- STOMP는 frame 기반 프로토콜이다.
- STOMP frame은 command, header, body로 구성된다.
- topic은 메시지의 카테고리를 지정하는 문자열이다.

## 정리 ✅

HTTP는 비연결성, 무상태성, 단방향 통신이라는 특징을 가지기 때문에 채팅과 같은 실시간 양방향 통신에는 적합하지 않다.  
WebSocket은 클라이언트가 연결을 요청한 뒤 연결을 유지하면서 클라이언트와 서버가 서로 메시지를 주고받을 수 있게 해 주는 양방향 통신 프로토콜이다.  
처음에는 HTTP 요청으로 시작하지만 `Upgrade` 헤더를 통해 WebSocket 프로토콜로 전환되고, 이후 WebSocket interaction이 시작된다.  
STOMP는 WebSocket 위에서 사용할 수 있는 간단한 메시지 전송 프로토콜로, 메시지 브로커와 publisher-subscriber 방식을 사용한다.  
STOMP 메시지는 command, header, body로 구성된 frame 형태이며, topic을 통해 메시지의 카테고리를 나누고 구독자에게 메시지를 전달할 수 있다.
