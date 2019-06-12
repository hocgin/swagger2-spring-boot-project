package in.hocg.swagger2.spring.boot.autoconfigure.properties;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
public class ContactConfig {
    private String name;
    private String url;
    private String email;
}
