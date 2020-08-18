package com.qwang.blog.service;

import com.qwang.blog.model.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author qwang
 * @date 2020/7/28
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer topSize);

    Type updateType(Long id, Type type);

    void removeType(Long id);
}
