package com.demo.mybatis.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * TODO
 *
 * @author yueyang
 * @since 2023-05-13 09:33:00
 */
@Slf4j
public class PageInfoUtils {

  /**
   * PageInfo对象转换
   *
   * @param pageInfo 原PageInfo对象
   * @param clazz 转换后的对象类型
   * @return 转换后的PageInfo对象
   * @param <E> 原对象类型
   * @param <D> 转换后的对象类型
   */
  public static <E, D> PageInfo<D> pageInfo2VO(PageInfo<E> pageInfo, Class<D> clazz) {
    Page<D> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
    page.setTotal(pageInfo.getTotal());
    try {
      D d = clazz.getConstructor().newInstance();
      for (E e : pageInfo.getList()) {
        BeanUtils.copyProperties(e, d);
        page.add(d);
      }
    } catch (Exception e) {
      log.error("pageInfo对象转换异常", e);
    }

    return new PageInfo<>(page, pageInfo.getNavigatePages());
  }
}
