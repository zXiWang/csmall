package com.xiwang.csmall.cart.webapi.config;



import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置类
 *
 * @author 夕妄
 * @version 0.0.1
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    /**
     * 【重要】指定Controller包路径
     */
    private final String basePackage = "com.xiwang.csmall.cart.webapi.controller";
    /**
     * 分组名称
     */
    private final String groupName = "base-cart-webapi";
    /**
     * 主机名
     */
    private final String host = "https://java.xiwang.com";
    /**
     * 标题
     */
    private String title = "酷鲨商城项目案例在线API文档--基础cart-webapi-web实例";
    /**
     * 简介
     */
    private String description = "构建基础cart-webapi-web项目,实现购买";
    /**
     * 服务条款URL
     */
    private final String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private final String contactName = "Java教学研发部";
    /**
     * 联系网址
     */
    private final String contactUrl = "https://java.xiwang.com";
    /**
     * 联系邮箱
     */
    private final String contactEmail = "java@xiwang.com";
    /**
     * 版本号
     */
    private final String version = "1.0.0";

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfiguration() {
        log.debug("加载配置类：Knife4jConfiguration");
    }

    @Bean
    public Docket docket() {
        String groupName = "1.0-SNAPSHOT";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .build();
    }

}