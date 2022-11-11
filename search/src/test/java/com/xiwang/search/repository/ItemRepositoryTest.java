package com.xiwang.search.repository;

import com.xiwang.search.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addOne() {
        // 实例化Item对象
        Item item = new Item()
                .setId(1L)
                .setTitle("罗技激光无线游戏鼠标")
                .setCategory("鼠标")
                .setBrand("罗技")
                .setPrice(188.0)
                .setImgPath("/1.jpg");
        // 利用SpringDataElasticsearch提供的新增方法,完成Item新增到ES
        itemRepository.save(item);
        System.out.println("ok");
    }


    @Test
    void addList() {
//        List<Item> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Item item = new Item((long) i, i + "", i + "", i + "", (double) i, i + "");
//            list.add(item);
//        }
//        itemRepository.saveAll(list);


        // 实例化一个List对象,用户添加要保存到ES的Item对象
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "罗技激光有线办公鼠标", "鼠标",
                "罗技", 9.9, "/2.jpg"));
        list.add(new Item(3L, "雷蛇机械无线游戏键盘", "键盘",
                "雷蛇", 262.0, "/3.jpg"));
        list.add(new Item(4L, "微软有线静音办公鼠标", "鼠标",
                "微软", 190.0, "/4.jpg"));
        list.add(new Item(5L, "罗技机械有线背光键盘", "键盘",
                "罗技", 236.0, "/5.jpg"));
        itemRepository.saveAll(list);
        System.out.println("ok");
    }


    @Test
    void getOne() {
        Optional<Item> optional = itemRepository.findById(1L);
        Item item = optional.get();
        System.out.println("item: " + item);
    }

    @Test
    void getAll() {
        Iterable<Item> list = new ArrayList<>();
        list = itemRepository.findAll();
        for (Item item : list) {
            System.out.println(item);
        }
        list.forEach(System.out::println);
    }

    @Test
    void queryItemsByTitleMatches() {
        Iterable<Item> items = itemRepository.queryItemsByTitleMatches("鼠标");
        items.forEach(System.out::println);

    }

    @Test
    void queryItemsByTitleMatchesAndBrandMatches() {
        Iterable<Item> items = itemRepository.queryItemsByTitleMatchesAndBrandMatches("游戏", "罗技");
        items.forEach(System.out::println);

    }

    @Test
    void queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc() {
        Iterable<Item> items = itemRepository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏", "罗技");
        items.forEach(System.out::println);

    }

    @Test
    void queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDescPageable() {
        int page = 1;
        int pageSize = 2;
        Page<Item> items = itemRepository
                .queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏", "罗技", PageRequest.of(page-1 , pageSize));
        items.forEach(System.out::println);
        System.out.println("总页数:"+items.getTotalPages());
        System.out.println("总条数:"+items.getTotalElements());
        System.out.println("当前页:"+items.getNumber()+1);
        System.out.println("每页条数:"+items.getSize());
        System.out.println("是否是首页:"+items.isFirst());
        System.out.println("是否是尾页:"+items.isLast());

    }

}