# Spring Boot AWS 실습

## 단축키 모음

* `Ctrl + Shift + A` : Action 검색창 열기 => plugins 등을 검색해서 설치를 가능토록 함
* `Alt + Insert` : 목록 창에서 누르면 ignore 파일 등 다양한 파일 생성 가능
* `Ctrl + K` : 깃 커밋 창
* `Ctrl + Shift + K` : 깃허브로 푸시



## 스프링 부트에서 테스트 코드를 작성

### Hello Controller에서 테스트 코드 작성

```java
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```



* `@SpringBootAplication`
  * 스프링 부트의 자동 설정, 스프링 Bean 일기와 생성 모두 자동으로 설정
  * `@SpringBootApplication`이 있는 위치부터 설정을 읽어가기 때문에 프로젝트 최상단에 위치해야함
  * main 메소드에서 실행하는 SpringApplication.run으로 인해 `내장 WAS(웹 애플리케이션 서버)`를 실행
    * 내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것을 의미
  * 내장 WAS로 인해 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됨



```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
```

* `@RestController`
  * 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
  * 예전에는 `@ResponseBody`를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각
* `@GetMapping`
  * HTTP Method인 Get 요청을 받을 수 있는 API를 만들어 줌
  * 예전에는 `@RequestMapping(method=RequestMethod.GET)`으로 사용. 이제 이 프로젝트는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 됨



```java
@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
```

* `@Runwith(SpringRunner.class)`
  * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
  * 여기서는 SpringRunner라는 스프링 실행자를 사용
  * 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
* `@WebMvcTest`
  * 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
  * 선언할 경우 `@Controller`, `@ControllerAdvice` 등을 사용할 수 있음
  * 단, `@Service, @Component, @Repository` 등을 사용할 수 없음
  * 여기서는 컨트롤러만 사용하기 때문에 선언
* `@Autwired`
  * 스프링이 관리하는 빈(Bean)을 주입 받음
* `private MockMvc mvc`
  * 웹 API를 테스트할 때 사용
  * 스프링 MVC 테스트의 시작점
  * 이 클래스를 통해서 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
* `mvc.perform(get("/hello"))`
  * MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
  * 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음
* `.andExpect(status().isOk())`
  * mvc.perform의 결과를 검증
  * HTTP Header의 Status를 검증
  * 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증
  * 여기선 OK(200)인지 아닌지 검증
* `.andExpect(content().string(hello))`
  * mvc.perform의 결과를 검증
  * 응답 본문의 내용을 검증
  * Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증



### Hello Controller 코드를 롬북으로 전환

```java
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
```

* `@Getter`
  * 선언된 모든 필드의 get 메소드를 생성
* `@RequiredArgsConstructor`
  * 선언된 모든 final 필드가 포함된 생성자를 생성
  * final이 없는 필드는 생성자에 포함되지 않음



```java
public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
```

* `assertThat`
  * `assertj`라는 테스트 검증 라이브러리의 검증 메소드
  * 검증하고 싶은 대상을 메소드 인자로 받음
  * 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있음
  * 여기서는 Junit의 기본 assertThat이 아닌 assertj의 assertThat을 사용했는데 다음과 같은 장점이 있기 때문
    * CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
    * 자동완성이 좀 더 확실하게 지원됨
* `isEqualTo`
  * `assertj`의 동등 비교 메소드
  * assertThat에 있는 값과 isEqualTo 갑을 비교해서 같을 때만 성공



```java
@GetMapping("/hello/dto")
public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                         @RequestParam("amount") int amount) {
    return new HelloResponseDto(name, amount);
}
```

* `@RequestParam`
  * 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
  * 여기서는 외부에서 name (@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)으로 저장



```java
@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
```

* `param`
  * API 테스트할 때 사용될 요청 파라미터를 설정
  * 단, 값은 String만 허용
  * 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능
* `jsonPath`
  * JSON 응답값을 필드별로 검증할 수 있는 메소드
  * $를 기준으로 필드명을 명시
  * 여기서는 name과 amount를 검증하니 `$.name, $.amount`로 검증





## 스프링 부트에서 JPA로 데이터베이스 다루기

