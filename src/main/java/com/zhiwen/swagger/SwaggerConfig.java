package com.zhiwen.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ProjectName swagger-demo
 * @ClassName SwaggerConfig
 * @create 2020-05-28 13:31
 * @Author by hyp
 * 访问地址: http://localhost:端口/swagger-ui.html
 */
@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.zhiwen.controller"))
        /**
         * any()  扫描所有，项目中的所有接口都会被扫描到
           none()  不扫描接口
           通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
           withMethodAnnotation(final Class<? extends Annotation> annotation)
           通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
           withClassAnnotation(final Class<? extends Annotation> annotation)
           basePackage(final String basePackage)  根据包路径扫描接口
         **/
                .build();
    }

    //配置swagger信息 apiInfo
    private ApiInfo apiInfo(){
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "知文", // 标题
                "知文后端对接", // 描述
                "v1.0", // 版本
                "", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

    }

