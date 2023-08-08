package org.spiderflow;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.spiderflow.core.utils.ExtractUtils;

/**
 * @Author : xuaoping
 * @Date : 2023/08
 */
@Slf4j
public class TestJsonPath {

    @Test
    public void testJsonPath() {

        String json = "{\"code\":\"0\",\"msg\":\"OK\",\"data\":{\"list\":[{\"id\":108,\"book_id\":21,\"book_name\":\"草房子\",\"age_id\":10,\"term_id\":1,\"book_version_ids\":[3],\"xb_value\":973,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[102],\"status\":1,\"create_time\":\"2023-06-15 19:29:09\",\"update_time\":\"2023-08-07 11:29:13\"},{\"id\":145,\"book_id\":478,\"book_name\":\"万物启蒙：竹、茶、柳、蝉、枸杞、蜜蜂（全6册）\",\"age_id\":0,\"term_id\":0,\"book_version_ids\":[1,2],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[102],\"status\":1,\"create_time\":\"2023-06-21 15:45:48\",\"update_time\":\"2023-08-07 10:14:48\"},{\"id\":5,\"book_id\":1645,\"book_name\":\"寻找小黄鸭\",\"age_id\":6,\"term_id\":0,\"book_version_ids\":[],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[53,58,102],\"status\":1,\"create_time\":\"2023-03-20 15:59:34\",\"update_time\":\"2023-08-07 09:42:56\"},{\"id\":162,\"book_id\":78,\"book_name\":\"安德烈的木鞋\",\"age_id\":13,\"term_id\":0,\"book_version_ids\":[3],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[101],\"status\":1,\"create_time\":\"2023-08-07 08:54:39\",\"update_time\":\"2023-08-07 08:54:39\"},{\"id\":144,\"book_id\":104,\"book_name\":\"世界顶尖科学家答儿童问\",\"age_id\":11,\"term_id\":0,\"book_version_ids\":[1,2],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[102],\"status\":1,\"create_time\":\"2023-06-21 10:14:25\",\"update_time\":\"2023-08-03 09:52:37\"},{\"id\":141,\"book_id\":1146,\"book_name\":\"一粒种子的旅程：向日葵、牵牛花、蒲公英（全3册）\",\"age_id\":11,\"term_id\":2,\"book_version_ids\":[1,2,3],\"xb_value\":123,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[101,102,103],\"status\":1,\"create_time\":\"2023-06-15 19:38:05\",\"update_time\":\"2023-08-02 08:47:04\"},{\"id\":22,\"book_id\":951,\"book_name\":\"小狗的小房子\",\"age_id\":7,\"term_id\":1,\"book_version_ids\":[1,2,3],\"xb_value\":663,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[58,102],\"status\":1,\"create_time\":\"2023-03-23 14:48:13\",\"update_time\":\"2023-08-01 14:23:36\"},{\"id\":102,\"book_id\":101,\"book_name\":\"易中天中华经典故事（全6册）\",\"age_id\":0,\"term_id\":0,\"book_version_ids\":[1,2],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[],\"status\":1,\"create_time\":\"2023-06-13 22:58:23\",\"update_time\":\"2023-08-01 14:20:59\"},{\"id\":146,\"book_id\":67,\"book_name\":\"那年深夏\",\"age_id\":0,\"term_id\":0,\"book_version_ids\":[1,2],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[],\"status\":1,\"create_time\":\"2023-06-26 16:07:23\",\"update_time\":\"2023-08-01 14:20:45\"},{\"id\":161,\"book_id\":1948,\"book_name\":\"草原巧克力\",\"age_id\":6,\"term_id\":0,\"book_version_ids\":[3],\"xb_value\":0,\"is_copy\":0,\"copy_num\":0,\"copy_desc\":\"\",\"related_pack\":[],\"status\":1,\"create_time\":\"2023-08-01 11:44:10\",\"update_time\":\"2023-08-01 11:44:10\"}],\"meta\":{\"count\":85}}}";
        JSONArray jsonArray = (JSONArray) ExtractUtils.getValueByJsonPath(json, "$.data.list[*]");
        for (int i = 0; i < jsonArray.size(); i++) {
            String bookName = (String) ExtractUtils.getValueByJsonPath(jsonArray.get(i), "$.book_name");
            log.info("index:{} ,bookName: {}", i, bookName);
        }
    }
}
