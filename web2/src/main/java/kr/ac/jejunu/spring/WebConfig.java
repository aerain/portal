package kr.ac.jejunu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.List;


// Annotation 관련된 것
// dispatcher-servlet.xml 안에 있는거
@Configuration
@EnableWebMvc // annotationdriven 짜리 annotation
@ComponentScan(basePackages = "kr.ac.jejunu") // xml 에 정의된거에 해당하는 annotation

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("/WEB-INF/static/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("WEB-INF/views/");
//        registry.enableContentNegotiation(new MappingJackson2JsonView());
//        registry.enableContentNegotiation(new MappingJackson2XmlView());
    }
}
