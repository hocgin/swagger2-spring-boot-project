package in.hocg.swagger2.spring.boot.autoconfigure;

import lombok.AllArgsConstructor;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by hocgin on 2019/6/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@AllArgsConstructor
public class KvExtension implements VendorExtension<Object> {
    private String name;
    private Object value;
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Object getValue() {
        return value;
    }
    
    public static KvExtension valueOf(String name, Object value) {
        return new KvExtension(name, value);
    }
    
    public static ArrayList<KvExtension> valueOfMap(Map<String, Object> map) {
        ArrayList<KvExtension> result = new ArrayList<>(map.size());
        map.forEach((k, v) -> result.add(KvExtension.valueOf(k, v)));
        return result;
    }
}
