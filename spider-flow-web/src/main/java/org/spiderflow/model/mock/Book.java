package org.spiderflow.model.mock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : xuaoping
 * @Date : 2023/08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public static List<Book> getMockListData() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "三国演义", BigDecimal.valueOf(69.9)));
        list.add(new Book(2, "红楼梦", BigDecimal.valueOf(69.9)));
        list.add(new Book(3, "水浒传", BigDecimal.valueOf(69.9)));
        list.add(new Book(4, "西游记", BigDecimal.valueOf(69.9)));
        list.add(new Book(5, "封神演义", BigDecimal.valueOf(50)));
        list.add(new Book(6, "罪与罚", BigDecimal.valueOf(50)));
        list.add(new Book(7, "战争与和平", BigDecimal.valueOf(50)));
        list.add(new Book(8, "刀锋", BigDecimal.valueOf(50)));
        list.add(new Book(9, "百年孤独", BigDecimal.valueOf(59.5)));
        list.add(new Book(10, "飘", BigDecimal.valueOf(50)));
        return list;
    }


    private Integer bookId;
    private String bookName;
    private BigDecimal bookPrice;
}
