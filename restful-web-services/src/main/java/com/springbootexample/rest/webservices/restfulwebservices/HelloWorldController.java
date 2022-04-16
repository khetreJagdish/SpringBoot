package com.springbootexample.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springbootexample.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		
		return "Hello World from Get";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello World from Get Bean");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World from Get PathVariable, %s", name));
	}
	
	//internationalized -i18n
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name ="Accept-Language",required = false) Locale locale 
			) {
		
		return messageSource.getMessage("good.morning.message", null,"Default Message", 
				LocaleContextHolder.getLocale());
				//locale);
		
		//return "hello-world-internationalized from English";
		
		//en = Hello World
		//nl = guten morgen
		//fr = Bonjour
	}
}
