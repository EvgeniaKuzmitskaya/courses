package by.myProject.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.myProject")
//@Import(value = {LoginSecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter  {

    private static final Charset UTF8 = Charset.forName("UTF-8");


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setPrefix("/WEB-INF/view/");
            viewResolver.setSuffix(".jsp");
            registry.viewResolver(viewResolver);
        }

        /**
         * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        }


        /**
         * Configure MessageSource to lookup any validation/error message in internationalized property files
         */
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("classpath:messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }

        /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
         * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
         * This is a workaround for this issue.
         */
        @Override
        public void configurePathMatch(PathMatchConfigurer matcher) {
            matcher.setUseRegisteredSuffixPatternMatch(true);
        }

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", UTF8)));
        converters.add(stringConverter);
        }
}
