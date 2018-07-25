package cn.songlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import tk.mybatis.spring.annotation.MapperScan;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = "cn.songlin.mapper") // 用于扫描mapper
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
