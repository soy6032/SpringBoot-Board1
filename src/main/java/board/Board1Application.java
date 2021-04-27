package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

//@SpringBootApplication
// @SpringBootApplication 어노테이션은 @SpringBootConfiguration, @CommponentScan, @EnableAutoConfiguration 세 개의 어노테이션이 합쳐서 만들어진 어노테이션이며, @EnableAutoConfiguration 어노테이션이 스프링부터의 자동 설정을 담당함
// 파일 업로드 부분을 사용하려면 자동 구성 부분을 꺼야함
// exclude 옵션을 사용하여 지정한 부분의 설정만 자동 구성을 끔
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class Board1Application {

	public static void main(String[] args) {
		SpringApplication.run(Board1Application.class, args);
	}

}
