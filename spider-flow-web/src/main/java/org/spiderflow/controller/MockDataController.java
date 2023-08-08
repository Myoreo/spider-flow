package org.spiderflow.controller;

import lombok.Data;
import org.spiderflow.common.CommonResult;
import org.spiderflow.common.ListResult;
import org.spiderflow.model.mock.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author : xuaoping
 * @Date : 2023/08
 */
@RestController
@RequestMapping("/mock")
public class MockDataController {

    @GetMapping("/test-list")
    public CommonResult<ListResult<Book>> testList(@RequestParam(value = "page_num", defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "page_size", defaultValue = "10") int pageSize) {
        List<Book> mockList = Book.getMockListData();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            list.add(mockList.get(random.nextInt(mockList.size())));
        }
        return CommonResult.success(ListResult.buildResult(list, 100));
    }

}
