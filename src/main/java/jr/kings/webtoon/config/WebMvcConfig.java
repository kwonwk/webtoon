// package jr.kings.webtoon.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import jr.kings.webtoon.security.LoggerInterceptor;

// @Configuration
// public class WebMvcConfig implements WebMvcConfigurer {
	
// 	@Autowired
// 	LoggerInterceptor loggerInterceptor;
	
// 	@Override
// 	public void addInterceptors (InterceptorRegistry registry) {
// 		registry.addInterceptor(loggerInterceptor).excludePathPatterns("/assets/**");
// 	}
// }