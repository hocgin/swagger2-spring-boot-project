package in.hocg.swagger2.spring.boot.autoconfigure;

import com.google.common.collect.Maps;
import in.hocg.swagger2.spring.boot.autoconfigure.properties.DocketConfig;
import in.hocg.swagger2.spring.boot.autoconfigure.properties.Swagger2Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Map;

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
@RequiredArgsConstructor
public class Swagger2AutoConfiguration {
    private final Swagger2Properties properties;
    private final Map<String, Docket> DOCKETS = Maps.newHashMap();
    
    @PostConstruct
    public void postProcessBeforeInitialization() {
        Map<String, DocketConfig> group = properties.getGroup();
        if (group.isEmpty()) {
            return;
        }
        for (String groupName : group.keySet()) {
            if (DOCKETS.containsKey(groupName)) {
                break;
            }
            DocketConfig docketConfig = group.get(groupName);
            log.info(String.format("%s %s", groupName, docketConfig));
            Docket docket = buildDocket(groupName, docketConfig);
            DOCKETS.put(groupName, docket);
        }
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
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo)
                .pathMapping(config.getPathMapping())
                .select()
                .apis(RequestHandlerSelectors.basePackage(config.getBasePackage()))
                .paths(PathSelectors.regex(config.getPath()))
                .build();
    }
    
}
