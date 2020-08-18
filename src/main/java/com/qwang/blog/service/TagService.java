package com.qwang.blog.service;

import com.qwang.blog.model.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author qwang
 * @date 2020/7/29
 */
public interface TagService {

    Tag saveTag(Tag tag);
    
    Tag getTag(Long id);
    
    Tag getTagByName(String name);
    
    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer topSize);
    
    Tag updateTag(Long id, Tag tag);

    void removeTag(Long id);
}
