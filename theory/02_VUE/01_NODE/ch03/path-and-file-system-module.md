# 파일 관리하기 - path, File System 모듈

## 1. 디렉토리 구분자

운영체제마다 디렉토리 구분자가 다르다.

- Windows: `\`(역슬래시)
- macOS, Linux: `/`

예:

- Windows: `C:\temp`
- macOS / Linux: `/temp`

---

## 2. path 모듈

### 2-1. path 모듈 가져오기

```js
const path = require('path');
```

`path`는 경로를 다루기 위한 내장 모듈이다.

`require('path')`에서 `'path'`를 탐색할 때는 다음 순서로 찾는다.

1. 내장 모듈 탐색
2. `node_modules` 탐색

---

### 2-2. 전역 설치

```bash
npm -g install
```

패키지를 전역으로 설치할 때 사용하는 방식이다.

---

### 2-3. 경로 합치기

```js
path.join(경로1, 경로2, ...)
```

여러 경로를 운영체제 환경에 맞게 합쳐 준다.

예:

```js
const fullPath = path.join(__dirname, 'test', 'a.txt');
console.log(fullPath);
```

---

### 2-4. 디렉토리 경로만 추출

```js
path.dirname(경로);
```

파일 경로에서 디렉토리 부분만 추출한다.

예:

```js
console.log(path.dirname(__filename));
```

---

### 2-5. 파일 이름 추출

```js
path.basename(경로);
path.basename(경로, 확장자);
```

- `path.basename(경로)` : 파일명과 확장자를 함께 반환
- `path.basename(경로, 확장자)` : 지정한 확장자를 제외한 파일명 반환

예:

```js
const fn1 = path.basename(__filename);
const fn2 = path.basename(__filename, '.js');

console.log(fn1);
console.log(fn2);
```

주의:

```js
const fn2 = path.basename(__filename, '.js');
```

이 경우에는 `.js`만 제거된다.  
확장자가 `.cpp` 같은 다른 형식이면 그대로 출력된다.

---

### 2-6. 확장자 추출

```js
path.extname(경로);
```

파일 경로에서 확장자만 추출한다.

예:

```js
console.log(path.extname(__filename));
```

---

### 2-7. 경로를 객체로 반환

```js
path.parse(경로);
```

경로를 다음과 같은 객체 형태로 반환한다.

```js
{
  (root, // 루트 디렉터리
    dir, // 디렉터리 경로
    base, // 파일명.확장명
    ext, // 확장명
    name); // 파일명(확장명 제외)
}
```

예:

```js
console.log(path.parse(__filename));
```

---

## 3. 동기 함수와 비동기 함수 구분

다음 기준으로 구분할 수 있다.

- `=` 대입 연산자로 반환값을 바로 받으면 보통 **동기 함수**
- `callback` 함수를 전달하면 보통 **비동기 함수**

예:

```js
const data = fs.readFileSync('a.txt', 'utf8'); // 동기
fs.readFile('a.txt', 'utf8', callback); // 비동기
```

---

## 4. File System 모듈

### 4-1. fs 모듈 가져오기

```js
const fs = require('fs');
```

`fs` 모듈은 파일과 디렉토리를 읽고, 쓰고, 삭제하는 기능을 제공한다.

---

## 5. 디렉토리 읽기

### 5-1. 동기 처리로 디렉터리 읽기

```js
fs.readdirSync(경로[, 옵션])
```

디렉터리 안의 내용을 읽어서 배열로 반환한다.

예:

```js
const files = fs.readdirSync(__dirname);
console.log(files);
```

---

### 5-2. 비동기 처리로 디렉터리 읽기

```js
fs.readdir(경로[, 옵션], 콜백)
```

콜백 함수 형식:

```js
(err, files);
```

- `err`: 에러 객체
- `files`: 처리 결과

예:

```js
fs.readdir(__dirname, (err, files) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log(files);
});
```

비동기 함수에서는 첫 번째 매개변수에 보통 에러 객체가 들어간다.

---

### 5-3. `./` 와 `__dirname`의 차이

```js
fs.readdir('./', ...)
```

이렇게 작성하면 현재 파일이 있는 폴더가 아니라,  
**프로세스가 실행된 현재 디렉터리(current working directory)** 를 기준으로 탐색한다.

- `.` : 현재 프로세스의 디렉터리
- `..` : 부모 디렉터리

현재 실행 파일이 존재하는 디렉터리를 기준으로 하려면 `__dirname`을 사용하면 된다.

예:

```js
fs.readdir(__dirname, (err, files) => {
  console.log(files);
});
```

---

## 6. 파일 읽기

### 6-1. 동기 처리로 파일 읽기

```js
fs.readFileSync(경로[, 옵션])
```

예:

```js
const data = fs.readFileSync('./test.txt', 'utf8');
console.log(data);
```

---

### 6-2. 비동기 처리로 파일 읽기

```js
fs.readFile(파일[, 옵션], 콜백)
```

예:

```js
fs.readFile('./test.txt', 'utf8', (err, data) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log(data);
});
```

---

## 7. 파일 쓰기

### 7-1. 동기 처리로 파일에 쓰기

```js
fs.writeFileSync(파일, 내용[, 옵션])
```

예:

```js
fs.writeFileSync('./test.txt', 'hello node', 'utf8');
```

---

### 7-2. 파일 존재 여부 확인

```js
fs.existsSync(파일);
```

파일이 존재하면 `true`, 없으면 `false`를 반환한다.

예:

```js
console.log(fs.existsSync('./test.txt'));
```

---

### 7-3. 비동기 처리로 파일에 쓰기

```js
fs.writeFile(파일, 내용[, 옵션], 콜백)
```

예:

```js
fs.writeFile('./test.txt', 'hello node', 'utf8', (err) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log('저장 완료');
});
```

---

## 8. 기존 파일에 내용 추가

### 8-1. flag 옵션

기존 파일에 내용을 추가할 때는 `flag` 옵션을 사용할 수 있다.

- `"a"` : 파일을 열고, 없으면 새로 만든다
- `"ax"` : `"a"`와 같지만 파일이 이미 있으면 실패한다
- `"a+"` : 파일을 읽고 내용을 추가하기 위해 열고, 없으면 새로 만든다

---

### 8-2. 동기 처리로 내용 추가

```js
fs.appendFileSync(파일, 내용[, 옵션])
```

예:

```js
fs.appendFileSync('./test.txt', '\nnew line', 'utf8');
```

---

### 8-3. 비동기 처리로 내용 추가

```js
fs.appendFile(파일, 내용[, 옵션], 콜백)
```

예:

```js
fs.appendFile('./test.txt', '\nnew line', 'utf8', (err) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log('추가 완료');
});
```

---

## 9. 파일 삭제

### 9-1. 동기 처리로 파일 삭제

```js
fs.unlinkSync(파일);
```

예:

```js
fs.unlinkSync('./test.txt');
```

---

### 9-2. 파일 삭제 실패 이유

파일 삭제가 실패하는 대표적인 경우는 다음과 같다.

1. 파일명이 잘못되었을 때
2. 쓰기 권한이 없을 때
3. 다른 프로세스가 파일을 열고 락(lock)을 걸었을 때

---

### 9-3. 비동기 처리로 파일 삭제

```js
fs.unlink(파일, 콜백);
```

예:

```js
fs.unlink('./test.txt', (err) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log('삭제 완료');
});
```

---

## 10. 디렉터리 생성과 삭제

### 10-1. 디렉터리 만들기

동기:

```js
fs.mkdirSync(경로[, 옵션])
```

비동기:

```js
fs.mkdir(경로[, 옵션], 콜백)
```

예:

```js
fs.mkdirSync('./sample');
```

---

### 10-2. 빈 디렉터리 삭제

동기:

```js
fs.rmdirSync(경로[, 옵션])
```

비동기:

```js
fs.rmdir(경로[, 옵션], 콜백)
```

빈 디렉터리만 삭제할 수 있다.

---

### 10-3. 파일 삭제 및 내용이 있는 디렉터리 삭제

동기:

```js
fs.rmSync(경로[, 옵션])
```

비동기:

```js
fs.rm(경로[, 옵션], 콜백)
```

`fs.rm()`은 파일 삭제와 내용이 있는 디렉터리 삭제에 사용할 수 있다.

---

## 11. 스트림

### 11-1. 리더블 스트림

리더블 스트림은 데이터를 읽기 위한 스트림이다.  
주로 서버에서 용량이 큰 데이터를 읽어 올 때 많이 사용한다.

```js
fs.createReadStream(경로[, 옵션])
```

예:

```js
const readStream = fs.createReadStream('./big.txt', 'utf8');
```

---

### 11-2. 이벤트 처리

스트림은 이벤트 방식으로 처리한다.

```js
.on('이벤트', 콜백)
```

대표 이벤트:

- `data`
- `end`
- `error`

예:

```js
readStream.on('data', (chunk) => {
  console.log(chunk);
});

readStream.on('end', () => {
  console.log('읽기 종료');
});

readStream.on('error', (err) => {
  console.log(err);
});
```

---

### 11-3. 라이터블 스트림

라이터블 스트림은 데이터를 기록하기 위한 스트림이다.

```js
fs.createWriteStream(경로[, 옵션])
```

예:

```js
const writeStream = fs.createWriteStream('./copy.txt');
```

---

### 11-4. pipe

`pipe()`는 두 개의 스트림을 연결하는 기능이다.

```js
readStream.pipe(writeStream[, 옵션])
```

원래는 `data` 이벤트가 발생할 때마다 직접 읽어서 기록해야 하지만,  
`pipe()`를 사용하면 이 과정을 한 번에 처리할 수 있다.

예:

```js
const readStream = fs.createReadStream('./source.txt');
const writeStream = fs.createWriteStream('./target.txt');

readStream.pipe(writeStream);
```

`pipe()`를 사용하면 파일 크기와 상관없이 파일 복사가 가능하다.

---

## 12. 정리

- `path` 모듈은 파일 경로를 다룰 때 사용한다.
- `path.join()`은 경로를 합칠 때 사용한다.
- `path.dirname()`, `path.basename()`, `path.extname()`, `path.parse()`로 경로 정보를 분리할 수 있다.
- `fs` 모듈은 파일과 디렉터리를 읽고, 쓰고, 삭제할 때 사용한다.
- 반환값으로 바로 받는 방식은 보통 동기 함수이고, 콜백을 전달하는 방식은 보통 비동기 함수이다.
- `./`는 현재 실행 중인 프로세스 기준 경로이고, `__dirname`은 현재 파일이 있는 디렉터리 경로이다.
- 스트림은 큰 파일을 효율적으로 처리할 때 사용한다.
- `pipe()`를 사용하면 읽기 스트림과 쓰기 스트림을 연결해 파일 복사를 쉽게 처리할 수 있다.
