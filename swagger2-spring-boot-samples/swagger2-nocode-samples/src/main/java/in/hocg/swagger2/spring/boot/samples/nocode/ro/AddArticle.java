package in.hocg.swagger2.spring.boot.samples.nocode.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hocgin
 * @date 2019/6/13
 */
@Data
@Accessors(chain = true)
@ApiModel("发布文章")
public class AddArticle {
    
    @ApiModelProperty("文章标题")
    private String title;
    
    @ApiModelProperty("文章内容")
    private String content;
}
