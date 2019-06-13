package in.hocg.swagger2.spring.boot.samples;

import com.google.common.collect.Lists;
import in.hocg.swagger2.spring.boot.samples.ro.AddArticle;
import in.hocg.swagger2.spring.boot.samples.ro.UpdateArticle;
import in.hocg.swagger2.spring.boot.samples.vo.Article;
import in.hocg.swagger2.spring.boot.samples.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hocgin
 * @date 2019/6/13
 */
@RestController
@RequestMapping("api/article")
@Api(tags = "文章相关接口")
public class ArticleController {
    
    @GetMapping("{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章 ID", required = true, paramType = "path"),
    })
    @ApiOperation("获取文章详情")
    public Result<Article> get(@PathVariable("id") Long id) {
        Article data = new Article().setContent("内容")
                .setTitle("标题");
        return Result.success(data);
    }
    
    @PostMapping("_search")
    @ApiOperation("获取文章详情")
    public Result<List<Article>> _search() {
        Article data = new Article().setContent("内容")
                .setTitle("标题");
        return Result.success(Lists.newArrayList(data));
    }
    
    @DeleteMapping("{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章 ID", required = true, paramType = "path"),
    })
    @ApiOperation("删除指定文章")
    public Result<Void> delete(@PathVariable("id") Long id) {
        return Result.success();
    }
    
    @PutMapping
    @ApiOperation("更新文章详情")
    public Result<Void> update(@RequestBody UpdateArticle updateArticle) {
        return Result.success();
    }
    
    @PostMapping
    @ApiOperation("增加文章详情")
    public Result<Void> add(@RequestBody AddArticle addArticle) {
        return Result.success();
    }
}
