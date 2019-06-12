package in.hocg.swagger2.spring.boot.autoconfigure.properties;

import com.google.common.collect.Maps;
import in.hocg.swagger2.spring.boot.autoconfigure.KvExtension;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import springfox.documentation.service.Contact;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
public class DocketConfig {
    private String pathMapping;
    private String basePackage;
    private String path;
    
    
    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private ContactConfig contact;
    private Map<String, Object> extensions = Maps.newHashMap();
    
    public Contact get2Contact() {
        if (Objects.isNull(contact)) {
            return null;
        }
        return new Contact(contact.getName(), contact.getUrl(), contact.getEmail());
    }
    
    public List get2Extensions() {
        if (Objects.isNull(extensions) || extensions.isEmpty()) {
            return Collections.emptyList();
        }
        return KvExtension.valueOfMap(extensions);
    }
    
}
