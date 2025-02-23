package com.demo.nosql;

import com.demo.nosql.domain.DictDO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootTest
class NosqlMongoApplicationTests {
  @Autowired private MongoTemplate mongoTemplate;

  @Test
  void contextLoads() {}

  @Test
  void test() {
    DictDO dictDO = new DictDO(2L, 1L, "dict-name", 6, "dict-code");
    DictDO dict = mongoTemplate.save(dictDO);
    log.info("set dict:{}", dict);
  }

  @Test
  void test2() {
    List<DictDO> dictList = mongoTemplate.findAll(DictDO.class);
    dictList.forEach(dict -> log.info("get dict:{}", dict));
  }
}
