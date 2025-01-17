package org.spiderflow;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@MapperScan("org.spiderflow.*.mapper")
public class SpiderApplication implements ServletContextInitializer{
	
	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(SpiderApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//设置文本缓存1M
		servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize", Integer.toString((1024 * 1024)));
	}
	
	@Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor(DbType.MYSQL);
    }
	
}
