package com.demo.alllearning.controller;

import com.demo.alllearning.common.constant.CacheConstants;
import com.demo.alllearning.common.enumeration.ErrorCodeEnum;
import com.demo.alllearning.common.validate.InsertValidationGroup;
import com.demo.alllearning.common.validate.UpdateValidationGroup;
import com.demo.alllearning.domain.common.PageQuery;
import com.demo.alllearning.domain.common.PageResult;
import com.demo.alllearning.domain.common.ResponseResult;
import com.demo.alllearning.domain.dto.UserDTO;
import com.demo.alllearning.domain.dto.UserQueryDTO;
import com.demo.alllearning.domain.vo.UserVO;
import com.demo.alllearning.service.UserService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-03-08 12:41:00
 */
@RestController
@RequestMapping("/api/user")
// 开启基础类型的校验
@Validated
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 新增用户
   *
   * @param userDTO 用户传输实体
   * @return 新增成功
   */
  @CacheEvict(cacheNames = CacheConstants.USERS_CACHE, allEntries = true) // 对数据有修改就清除缓存
  @PostMapping
  public ResponseResult<Void> save(
      @Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO) {
    int save = userService.save(userDTO);
    if (save == 1) {
      return ResponseResult.success();
    } else {
      return ResponseResult.failure(ErrorCodeEnum.USER_REGISTER_FAIL);
    }
  }

  /**
   * 删除用户
   *
   * @param id 用户ID
   * @return 新增成功
   */
  @CacheEvict(cacheNames = CacheConstants.USERS_CACHE, allEntries = true) // 对数据有修改就清除缓存
  @DeleteMapping("/{id}")
  public ResponseResult<Void> delete(@NotNull(message = "用户ID不能为空！") @PathVariable("id") Long id) {
    int delete = userService.delete(id);
    if (delete == 1) {
      return ResponseResult.success();
    } else {
      return ResponseResult.failure(ErrorCodeEnum.USER_DELETE_FAIL);
    }
  }

  /**
   * 更新用户
   *
   * @param id 用户ID
   * @param userDTO 用户传输实体
   * @return 新增成功
   */
  @CacheEvict(cacheNames = CacheConstants.USERS_CACHE, allEntries = true) // 对数据有修改就清除缓存
  @PutMapping("/{id}")
  public ResponseResult<Void> update(
      @NotNull(message = "用户ID不能为空！") @PathVariable("id") Long id,
      @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
    int update = userService.update(id, userDTO);
    if (update == 1) {
      return ResponseResult.success();
    } else {
      return ResponseResult.failure(ErrorCodeEnum.USER_UPDATE_FAIL);
    }
  }

  /**
   * 查询用户
   *
   * @param pageNo 当前页数
   * @param pageSize 每页行数
   * @param userQueryDTO 用户数据查询实体
   * @return 通用返回结果模型
   */
  @Cacheable(cacheNames = CacheConstants.USERS_CACHE) // 查询使用缓存
  @GetMapping
  public ResponseResult<PageResult<List<UserVO>>> query(
      @NotNull @RequestParam("pageNo") Integer pageNo,
      @NotNull @RequestParam("pageSize") Integer pageSize,
      @Validated UserQueryDTO userQueryDTO) {
    // 构造查询条件
    PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
    pageQuery.setPageNo(pageNo);
    pageQuery.setPageSize(pageSize);
    pageQuery.setQuery(userQueryDTO);
    PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

    // 数据解析
    List<UserVO> userVOList =
        Optional.ofNullable(pageResult.getData()).stream()
            .flatMap(Collection::stream)
            .map(
                userDTO -> {
                  UserVO userVO = new UserVO();
                  BeanUtils.copyProperties(userDTO, userVO);

                  // 特殊字段处理
                  userVO.setPassword("******");
                  if (!ObjectUtils.isEmpty(userDTO.getPhone())) {
                    userVO.setPhone(
                        userDTO.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                  }
                  return userVO;
                })
            .collect(Collectors.toList());

    // 封装返回结果
    PageResult<List<UserVO>> result = new PageResult<>();
    BeanUtils.copyProperties(pageResult, result);
    result.setData(userVOList);
    return ResponseResult.success(result);
  }
}
