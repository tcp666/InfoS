package com.keshe.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/6/1 23:34
 */
@Configuration
@EnableSwagger2 //开启Swagger支持
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("wf")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.keshe.controller"))
                .build()//这是一个工厂模式
                ;
    }

    //修改Swagger的ApiInfo信息
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "haining的API文档",//标题
                "学习Swagger的开发API文档",//APi文档的描述
                "v1.0",
                "https://blog.csdn.net/gyhdxwang",//开发的组织
                new Contact("haining", "https://blog.csdn.net/gyhdxwang", "wangfeng614@163.com"),//开发者信息
                "Apache 2.0",//默认开源协议
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}