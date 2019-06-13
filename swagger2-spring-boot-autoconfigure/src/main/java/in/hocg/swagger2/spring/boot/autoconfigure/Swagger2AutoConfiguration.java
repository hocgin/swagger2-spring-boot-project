package in.hocg.swagger2.spring.boot.autoconfigure;

import in.hocg.swagger2.spring.boot.autoconfigure.properties.DocketConfig;
import in.hocg.swagger2.spring.boot.autoconfigure.properties.Swagger2Properties;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Log
@EnableSwagger2
@ConditionalOnProperty(prefix = Swagger2Properties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(Swagger2Properties.class)
public class Swagger2AutoConfiguration implements BeanFactoryPostProcessor, EnvironmentAware {
    private final static String DEFAULT_DOCKET_GROUP_NAME_FORMATTER = "Swagger_%s";
    private final static String DEFAULT_DOCKET_GROUP_NAME = "DEFAULT";
    private Environment environment;
    
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    
    private Docket defaultDocket(Swagger2Properties properties) {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(properties.get2Contact())
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .extensions(properties.get2Extensions())
                .build();
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    
    private Docket buildDocket(String groupName, DocketConfig config) {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(config.get2Contact())
                .title(config.getTitle())
                .description(config.getDescription())
                .version(config.getVersion())
                .termsOfServiceUrl(config.getTermsOfServiceUrl())
                .extensions(config.get2Extensions())
                .build();
        
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo)
                .pathMapping(config.getPathMapping())
                .select()
                .apis(RequestHandlerSelectors.basePackage(config.getBasePackage()));
        if (Objects.nonNull(config.getPath())) {
            apiSelectorBuilder.paths(PathSelectors.regex(config.getPath()));
        }
        
        return apiSelectorBuilder.build();
    }
    
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Swagger2Properties properties = getProperties();
        Map<String, DocketConfig> group = properties.getGroup();
        if (group.isEmpty()) {
            Docket defaultDocket = defaultDocket(properties);
            beanFactory.registerSingleton(formatterBeanName(DEFAULT_DOCKET_GROUP_NAME), defaultDocket);
            return;
        }
        for (String groupName : group.keySet()) {
            if (beanFactory.containsBean(groupName)) {
                break;
            }
            DocketConfig docketConfig = group.get(groupName);
            Docket docket = buildDocket(groupName, docketConfig);
            beanFactory.registerSingleton(formatterBeanName(groupName), docket);
        }
    }
    
    private String formatterBeanName(String groupName) {
        return String.format(DEFAULT_DOCKET_GROUP_NAME_FORMATTER, groupName);
    }
    
    private Swagger2Properties getProperties() {
        return Binder.get(environment).bind(Swagger2Properties.PREFIX, Swagger2Properties.class).get();
    }
    
}
