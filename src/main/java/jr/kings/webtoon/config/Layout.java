package jr.kings.webtoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * Layout
 */
@Configuration
public class Layout {

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }
    
}