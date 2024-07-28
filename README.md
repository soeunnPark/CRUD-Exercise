# CRUD-Exercise
CRUD 혼자 만들어보기 프로젝트



### @NotNull vs nullable = false

1. DTO에서 유효성 검사를 수행하면 클라이언트로부터 받은 데이터가 서버에서 처리되기 전에 검증될 수 있습니다.


- 사용자 입력 데이터를 빠르게 검증하여 잘못된 데이터가 서버 로직에 들어오는 것을 방지합니다.
- 사용자에게 즉시 피드백을 제공할 수 있습니다.
- 클라이언트와의 데이터 통신에서 발생할 수 있는 문제를 사전에 차단합니다.

2. 엔티티에서 유효성 검사를 수행하면 데이터베이스의 제약 조건을 정의하여 데이터 무결성을 보장할 수 있습니다.
 
- 데이터베이스 레벨에서 데이터 무결성을 보장할 수 있습니다.
- 데이터베이스에 잘못된 데이터가 저장되는 것을 방지합니다.

결론 : 둘 다 하는 것이 좋다. 


<br>





### 비밀번호 해시 처리 - BCryptPasswordEncoder


1. build.gradle 파일에 Spring Security 의존성 추가
   ```	
   implementation 'org.springframework.boot:spring-boot-starter-security'
    ```
2. Service에서 UserEntity 생성할 때 비밀번호 해시하여 저장

    ```
    .userPassword(passwordEncoder.encode(registerRequest.getUserPassword())) // 비밀번호 해시 처리
    ```

3. Service의 login 메서드에서 비밀번호 일치 여부 확인

    ```
   passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword()))
    ```
    원본 비밀번호와 해시된 비밀번호를 비교하는 데 사용

    - `loginRequest.getUserPassword()`: 사용자가 로그인 시 입력한 원본 비밀번호.
    - `user.getUserPassword()`: 데이터베이스에 저장된 해시된 비밀번호.

    - 반환값은 `boolean`




<br>

### Spring Security 401에러

Spring Security를 사용하면 기본적으로 모든 요청에 대해 인증이 필요하게 설정되기 때문에, 인증되지 않은 사용자가 접근하려고 하면 401 에러가 발생할 수 있습니다.

해결 > SecurityConfig 파일 작성
    - 모든 경로에 대해 허용 <br>
    - `csrf.disable()`

<br>


### Service, Controller, Entity



`Controller`는 Service를 호출하여 RequestDto를 넘긴다. 서비스 호출 반환값을 ResponseDto로 받고, ResponseEntity로 감싸 응답 결과를 반환한다. 
Controller에서 Entity에 직접 접근하지 않는 편이 좋다.
<br>

`Service`에서 Dto와 Entity 사이에서 변환 과정을 거치며, Repository에 작업 내용을 반영한다.

### 에러처리

서비스 계층은 비즈니스 로직을 처리하고, 컨트롤러는 사용자 요청과 응답을 관리합니다. 따라서, 서비스 계층에서 문제가 발생하면 이를 컨트롤러로 전달하고, 컨트롤러는 적절한 HTTP 응답을 반환하도록 하는 것이 좋습니다.

> 서비스에서는 에러를 던지고, 컨트롤러에서 이를 처리한다.

Service의 메서드의 반환값을 Optional로 감싸는 것은 좋지 않다. 만약 Entity 또는 Dto가 null을 반환한다면, 컨트롤러로 에러를 던지도록 한다.

### Entity <-> Dto

둘 다 builder로 생성


### Optional

Optional로 생성된 객체는 `isPresent()`를 통해 값이 있는지 여부로 확인한다. 
값이 없는 경우 Service 계층에서 예외를 던진다. 


### Service 반환값

서비스 메서드는 `ResponseDto`를 반환하거나 `예외`를 던집니다. 서비스 메서드의 반환값이 `null`이 되는 일이 없도록 합니다.


### ExceptionHandler

IllegalArgumentException이 던져질 때 Spring Boot는 기본적으로 500 Internal Server Error로 응답합니다. 그러나 이 예외를 @ExceptionHandler를 사용하여 적절히 처리하면 500 오류 대신 특정한 HTTP 상태 코드(예: 401 Unauthorized)를 반환할 수 있습니다.

서비스에서는 예외를 던지고, 컨트롤러에서는 예외를 처리한다. 