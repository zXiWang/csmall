package com.xiwang.search.repository;

import com.xiwang.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    // ItemRepository接口需要继承SpringData框架提供的父接口ElasticsearchRepository
    // 一旦继承,当前接口就可以编写连接ES操作数据的代码了
    // 继承了父接口后,SpringData会根据泛型中编写的Item找到对应的索引
    // 会对这个索引自动生成基本的增删改查方法,我们自己无需再编写
    // ElasticsearchRepository<[要操作的\关联的实体类名称],[实体类主键的类型]>

    Iterable<Item> queryItemsByTitleMatches(String title);

    Iterable<Item> queryItemsByTitleMatchesAndBrandMatches(String title, String brand);

    Iterable<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title, String brand);

    Page<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title, String brand, Pageable pageable);
}
