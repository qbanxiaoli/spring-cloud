package com.qbanxiaoli.common.model.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.MappedSuperclass;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@MappedSuperclass
public class IdEntity {

    /**
     * 必须是ToStringStyle.SHORT_PREFIX_STYLE，形式如下:</br>
     * Person[name=John Doe,age=33,smoker=false]</br>
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
