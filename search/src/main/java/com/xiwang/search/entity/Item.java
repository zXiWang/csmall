package com.xiwang.search.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Accessors(chain = true)    // 支持链式Set赋值
@AllArgsConstructor         // 自动生成当前类的全参构造方法
@NoArgsConstructor          // 自动生成当前类的无参构造方法
// @Document注解标记表示当前类是对应ES框架的一个实体类
// 属性indexName指定ES中的索引名称,运行时,如果这个索引不存在SpringData会自动创建这个索引
@Document(indexName = "items")
public class Item implements Serializable {

    // SpringData通过@Id注解标记当前实体类主键
    @Id
    private Long id;
    // SpringData 标记title字段需要支持分词,并定义分词器
    @Field(type = FieldType.Text,
            analyzer = "ik_max_word",
            searchAnalyzer = "ik_max_word")
    private String title;
    // Keyword类型是不分词的字符串类型
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String brand;
    @Field(type = FieldType.Double)
    private Double price;
    // imgPath是图片路径,他不会称之为搜索条件,所以可以不创建索引,节省空间
    // 设置index = false,就是不创建索引
    // 但是需要注意,不创建索引并不是不保存这个数据,ES会保存这个数据
    @Field(type = FieldType.Keyword,index = false)
    private String imgPath;
}
