package in.hocg.swagger2.spring.boot.autoconfigure.properties;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@ConfigurationProperties(prefix = Swagger2Properties.PREFIX)
public class Swagger2Properties {
    public static final String PREFIX = "spring.swagger2";
    
    private Map<String, DocketConfig> group = Maps.newHashMap();
}
