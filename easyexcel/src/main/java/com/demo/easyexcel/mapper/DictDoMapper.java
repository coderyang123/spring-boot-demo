package com.demo.easyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.easyexcel.domain.entity.DictDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2021-04-04 13:33:00
 */
@Repository
public interface DictDoMapper extends BaseMapper<DictDO> {

  /**
   * 新增数据
   *
   * @param dictDoList 数据字典集合
   */
  void saveBatch(@Param("dictDoList") List<DictDO> dictDoList);
}
