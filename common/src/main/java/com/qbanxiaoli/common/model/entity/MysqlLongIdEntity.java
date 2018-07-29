package com.qbanxiaoli.common.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by qbanxiaoli on 2017/9/5.
 */
@MappedSuperclass
public class MysqlLongIdEntity extends IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    private Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof MysqlLongIdEntity) {
            MysqlLongIdEntity mysqlLongIdEntity = (MysqlLongIdEntity) object;
            if (this.getId() == null || mysqlLongIdEntity.getId() == null) {
                return false;
            } else {
                return (this.getId().equals(mysqlLongIdEntity.getId()));
            }
        }
        return false;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
    }

}
