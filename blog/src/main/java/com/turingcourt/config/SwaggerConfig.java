package com.turingcourt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Controller
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //要输出swagger的路径
                .apis(RequestHandlerSelectors.basePackage("com.turingcourt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //自定义页面标题
                .title("个人博客 API")
                //自定义版本号
                .version("1.0")
                //自定义描述
                .description("博客")
                //自定义内容
                .contact(new Contact("Cya", "github.com", "@qq.com"))
                .build();
    }

}
