package com.demo.jpa.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

/**
 * 部门实体类
 *
 * @author yueyang
 * @since 2022-02-28 14:44:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orm_department")
public class DepartmentDO extends AbstractAuditModel {

  /** 部门名 */
  @Column(name = "name", columnDefinition = "varchar(255) not null")
  private String name;

  /** 上级部门id */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "superior", referencedColumnName = "id")
  private DepartmentDO superior;

  /** 所属层级 */
  @Column(name = "levels", columnDefinition = "int not null default 0")
  private Integer levels;

  /** 排序 */
  @Column(name = "order_no", columnDefinition = "int not null default 0")
  private Integer orderNo;

  /** 子部门集合 */
  @OneToMany(
      cascade = {CascadeType.REFRESH, CascadeType.REMOVE},
      fetch = FetchType.EAGER,
      mappedBy = "superior")
  private Collection<DepartmentDO> children;

  /** 部门下用户集合 */
  @ManyToMany(mappedBy = "departmentDOList", fetch = FetchType.EAGER)
  @ToString.Exclude
  private Collection<UserDO> userList;

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "("
        + "id = "
        + getId()
        + ", "
        + "createTime = "
        + getCreateTime()
        + ", "
        + "lastUpdateTime = "
        + getLastUpdateTime()
        + ", "
        + "name = "
        + getName()
        + ", "
        + "superior = "
        + getSuperior()
        + ", "
        + "levels = "
        + getLevels()
        + ", "
        + "orderNo = "
        + getOrderNo()
        + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    DepartmentDO that = (DepartmentDO) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
