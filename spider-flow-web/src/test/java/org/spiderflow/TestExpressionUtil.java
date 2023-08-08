package org.spiderflow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spiderflow.core.io.GetHttpRequest;
import org.spiderflow.core.io.HttpRequest;
import org.spiderflow.core.io.HttpResponse;
import org.spiderflow.core.utils.ExpressionUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : xuaoping
 * @Date : 2023/07
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestExpressionUtil {

    @Test
    public void test() throws IOException {
        //String variableValue = "${resp.html}";
        String variableValue = "${resp.html}";
        Map<String, Object> variables = new HashMap<>();
        HttpRequest request = new GetHttpRequest();
        request.url("https://www.baidu.com");

        HttpResponse response = request.execute();
        variables.put("resp", response);
        Object value = ExpressionUtils.execute(variableValue, variables);
        System.out.println(value);
    }

    @Test
    public void test2() {

        Map<String, Object> variables = new HashMap<>();
        Goods goods = new Goods();
        goods.setName("商品名");
        goods.setPrice(1.22D);

        variables.put("goods", goods);
        double price = (double) ExpressionUtils.execute("${goods.price}", variables);
        String name = (String) ExpressionUtils.execute("${goods.name}", variables);

        log.info("goods,name:{},price:{}", name, price);
    }


    public static class Goods {
        private String name;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}

