package kr.co.kbs.distribute.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.kbs.distribute.interce.interceptor.LoginInterceptor;

//import co.kr.medialog.prj.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.kbs.distribute")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	
	@Bean
	public ViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setOrder(1);
		
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
	public BeanNameViewResolver beanNameViewResolver(){
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
		
	}
//	
//	@Bean
//	public CommonsMultipartResolver multipartResolver(){
//		CommonsMultipartResolver mResolver = new CommonsMultipartResolver();
//		mResolver.setDefaultEncoding("UTF-8");
//		mResolver.setMaxUploadSize(10000000);
//		return mResolver;
//	}
	
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();

	    factory.setMaxFileSize(100000000);
	    factory.setMaxRequestSize(100000000);

	    return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@Bean
	public MappingJackson2JsonView jsonView(){
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrefixJson(false);
		jsonView.setPrettyPrint(true);
		jsonView.setObjectMapper(jsonMapper());
		jsonView.setContentType("application/json;charset=utf-8");
		return jsonView;
		
	}
	
	@Bean
	public ObjectMapper jsonMapper(){
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper;
	}
	
	/*
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return configurableEmbeddedServletContainer -> configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
	}

*/
    @Override
    public void addInterceptors(InterceptorRegistry registry)   {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/adminLogin" ,"/login/user" ,"/login/loginCheck" ,"/logout", "/error", "/error/menuCheck");
    }
    
}