# Elasticsearch 高级客户端 CRUD 演示

> 本项目主要基于`Spring Boot`集成`Elasticsearch`高级客户端演示基本`CRUD`操作

# 1 Query DSL

## 1.1 索引

### 1.1.1 创建索引

#### 1.1.1.1 基本创建

```
PUT /my-index
{
  "mappings": {
    "properties": {
      "field1": {
        "type": "text"
      },
      "field2": {
        "type": "keyword"
      }
    }
  }
}
```

#### 1.1.1.2 指定分片和副本创建

```
PUT /my-index
{
  "mappings": {
    "properties": {
      "field1": {
        "type": "text"
      },
      "field2": {
        "type": "keyword"
      }
    }
  },
  "settings": {
    "number_of_shards": 3,
    "number_of_replicas": 1
  }
}
```

### 1.1.2 删除索引

```
DELETE /my-index
```

### 1.1.3 查看所有索引

```
GET /_cat/indices?v
```

## 1.2 文档

### 1.2.1 创建文档

#### 1.2.1.1 PUT方式创建（必须指定ID）

```
PUT /my-index/_doc/1
{
  "field1": "test",
  "field2": "test2"
}
```

#### 1.2.1.2 POST方式创建（指定ID）

```
POST /my-index/_doc/2
{
  "field1": "test",
  "field2": "test2"
}
```

#### 1.2.1.3 POST方式创建（不指定ID，自动生成ID）

```
POST /my-index/_doc
{
"field1": "test",
"field2": "test2"
}
```

#### 1.2.1.4 批量创建创建

```
POST /my-index/_bulk
{"create":{"_id":"7"}}
{"field1":"test7","field2":"test7"}
{"create":{"_id":"8"}}
{"field1":"test8","field2":"test8"}
```

### 1.2.2 删除文档

#### 1.2.2.1 删除指定ID的文档

```
DELETE /my-index/_doc/1
```

#### 1.2.2.2 删除多个指定ID的文档

```
POST /my-index/_bulk
{"delete":{"_id":"1"}}
{"delete":{"_id":"2"}}
```

#### 1.2.2.3 条件删除多个文档

```
POST /my-index/_delete_by_query
{
  "query": {
    "range": {
      "field1": {
        "lt": 30
      }
    }
  }
}
```

#### 1.2.2.4 批量删除

```
POST /my-index/_bulk
{"delete":{"_id":"7"}}
{"delete":{"_id":"8"}}
```

### 1.2.3 修改文档

#### 1.2.3.1 根据ID修改

```
POST /my-index/_update/1
{
  "doc": {
    "field1": "test1",
    "field2": "test1"
  }
}
```

#### 1.2.3.2 查询并更新

```
POST /my-index/_update_by_query
{
  "query": {
    "term": {
      "field1": {
        "value": "22"
      }
    }
  },
  "script": {
    "source": "ctx._source.field2='test22'",
    "lang": "painless"
  }
}
```

#### 1.2.3.3 异步查询并更新

```
POST /my-index/_update_by_query
{
  "query": {
    "term": {
      "field1": {
        "value": "22"
      }
    }
  },
  "script": {
    "source": "ctx._source.field2='test22'",
    "lang": "painless"
  }
}
```

#### 1.2.3.4 批量更新

```
POST /my-index/_bulk
{"update":{"_id":"5"}}
{"doc":{"field1":"555","field2":"555"}}
{"update":{"_id":"6"}}
{"doc":{"field1":"666","field2":"666"}}
```

此方式返回一个任务ID，可以根据此ID获取更新进度：

```
GET /_tasks/任务ID
```

### 1.2.4 查询文档

#### 1.2.4.1 查询单个文档

```
GET /my-index/_doc/1
```

#### 1.2.4.2 查询多个文档

```
GET /_mget
{
  "docs": [
    {
      "_index": "my-index",
      "_id": "1"
    },
    {
      "_index": "my-index",
      "_id": "2"
    }
  ]
}
```

#### 1.2.4.3 查询多个文档

```
GET /my-index/_doc/_mget
{
  "docs": [
    {
      "_id": "1"
    },
    {
      "_id": "2"
    }
  ]
}
```

#### 1.2.4.4 查询多个文档

```
GET /my-index/_mget
{
  "ids": [
    "1",
    "2"
  ]
}
```

# 2 查询

## 2.1 Term Level Query API

### 2.1.1 Term Query

```
POST /my-index/_search
{
  "query": {
    "term": {
      "field1": {
        "value": "666"
      }
    }
  }
}
```

### 2.1.2 Terms Query

```
POST /my-index/_search
{
  "query": {
    "terms": {
      "field1": [
        "555",
        "666"
      ]
    }
  }
}
```

### 2.1.3 Range Query

```
POST /my-index/_search
{
  "query": {
    "range": {
      "field2": {
        "gte": 1,
        "lt": 3
      }
    }
  }
}
```

### 2.1.4 Exist Query

```
POST /my-index/_search
{
  "query": {
    "exists": {
      "field": "field2"
    }
  }
}
```

### 2.1.5 Prefix Query

```
POST /my-index2/_search
{
  "query": {
    "prefix": {
      "field1": {
        "value": "he"
      }
    }
  }
}
```

### 2.1.6 Wildcard Query

```
POST /my-index2/_search
{
"query": {
"wildcard": {
"field1": "hell*"
}
}
}
```

## 2.2 组合查询

### 2.2.1 Bool Query

#### 2.2.1.1 must

```
POST /my-index2/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "field1": {
              "value": "3"
            }
          }
        },
        {
          "term": {
            "field2": {
              "value": 3
            }
          }
        }
      ]
    }
  }
}
```

#### 2.2.1.2 should

minimum_should_match表示至少有一个term匹配

```
POST /my-index2/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "term": {
            "field1": {
              "value": "1"
            }
          }
        },
        {
          "term": {
            "field2": {
              "value": 2
            }
          }
        }
      ],
      "minimum_should_match": 1
    }
  }
}
```

#### 2.2.1.3 must_not

```
POST /my-index2/_search
{
  "query": {
    "bool": {
      "must_not": [
        {
          "term": {
            "field1": {
              "value": "1"
            }
          }
        },
        {
          "term": {
            "field2": {
              "value": 3
            }
          }
        }
      ]
    }
  }
}
```

#### 2.2.1.4 filter

精确范围查询，不用计算分时使用

```
POST /my-index2/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "field1": {
              "value": "1"
            }
          }
        }
      ],
      "filter": [
        {
          "range": {
            "field2": {
              "gt": 1
            }
          }
        }
      ]
    }
  }
}
```

# 3 SpringBoot整合开发

## 3.1 框架搭建

### 3.1.1 主要依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- SpringBoot与elasticsearch整合的相关依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
</dependencies>
```

### 3.1.2 配置文件

#### 3.1.2.1 配置文件-单节点

[application.yml](./src/main/resources/application.yml)

### 3.1.2.2 配置文件-多节点

[application-multi.yml](./src/main/resources/application-multi.yml)

### 3.1.3 配置类

### 3.1.3.1 配置类-使用@Value注入配置方式

[ElasticsearchConfig.java](./src/main/java/com/demo/elasticsearchhighlevelclient/config/ElasticsearchConfig.java)

### 3.1.3.2 配置类-使用@ConfigurationProperties注入配置方式

[ElasticsearchConfig2.java](./src/main/java/com/demo/elasticsearchhighlevelclient/config/ElasticsearchConfig2.java)

### 3.1.4 实体类

[Product.java](./src/main/java/com/demo/elasticsearchhighlevelclient/domain/entity/Product.java)

## 3.2 查询示例

### 3.2.1 新建索引

```
PUT /fruit
{
  "mappings": {
    "properties": {
      "title": {
        "type": "keyword"
      },
      "price": {
        "type": "double"
      },
      "description": {
        "type": "text",
        "analyzer": "ik_max_word"
      }
    }
  }
}
```

### 3.2.2 初始化数据

```
PUT /fruit/_bulk
{"index":{}}
{"title":"面包","price":19.9,"description":"小面包非常好吃"}
{"index":{}}
{"title":"旺仔牛奶","price":29.9,"description":"非常好喝"}
{"index":{}}
{"title":"日本豆","price":19.9,"description":"日本豆非常好吃"}
{"index":{}}
{"title":"小馒头","price":19.9,"description":"小馒头非常好吃"}
{"index":{}}
{"title":"大辣片","price":39.9,"description":"大辣片非常好吃"}
{"index":{}}
{"title":"透心凉","price":9.9,"description":"透心凉非常好喝"}
{"index":{}}
{"title":"小浣熊","price":19.9,"description":"童年的味道"}
{"index":{}}
{"title":"海苔","price":19.9,"description":"海的味道"}
```

### 3.2.3 文档简单操作示例

[RestHighLevelClientForDocumentTests](./src/test/java/com/demo/elasticsearchhighlevelclient/RestHighLevelClientForDocumentTests.java)

### 3.2.4 索引操作示例

[RestHighLevelClientForIndexTests](./src/test/java/com/demo/elasticsearchhighlevelclient/RestHighLevelClientForIndexTests.java)

### 3.2.5 对象操作示例

[RestHighLevelClientForObjectTests](./src/test/java/com/demo/elasticsearchhighlevelclient/RestHighLevelClientForObjectTests.java)

### 3.2.6 文档聚合查询示例

[RestHighLevelClientForAggregationTests](./src/test/java/com/demo/elasticsearchhighlevelclient/RestHighLevelClientForAggregationTests.java)




