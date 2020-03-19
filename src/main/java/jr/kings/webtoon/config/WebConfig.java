package jr.kings.webtoon.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebConfig
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[] {WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        
        return null;
    }

    @Override
    protected String[] getServletMappings() {
       
        return null;
    }

    
}