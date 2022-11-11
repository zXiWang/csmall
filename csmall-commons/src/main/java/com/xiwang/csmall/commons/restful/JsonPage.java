package com.xiwang.csmall.commons.restful;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable {

    // 当前类应该和PageInfo一样,既能包含分页查询结果又包含分页信息
    // 当前只声明最基本的四个分页信息,之后随需求变更增加
    @ApiModelProperty(value = "总页数", name = "totalPages")
    private Integer totalPages;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private Long totalCount;

    @ApiModelProperty(value = "页码", name = "page")
    private Integer page;

    @ApiModelProperty(value = "每页条数", name = "pageSize")
    private Integer pageSize;

    @ApiModelProperty(value = "分页数据", name = "list")
    private List<T> list;

    // 将PageInfo类型对象转换成JsonPage类型对象
    public static <T> JsonPage<T> restPage(PageInfo<T> pageInfo) {
        // 转换即将PageInfo对象中的信息赋值给JsonPage类型
        JsonPage<T> result = new JsonPage<>();
        result.setTotalCount(pageInfo.getTotal());
        result.setTotalPages(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setList(pageInfo.getList());
        return result;
    }
}
