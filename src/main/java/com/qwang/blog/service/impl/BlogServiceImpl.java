package com.qwang.blog.service.impl;

import com.qwang.blog.exception.NotFoundException;
import com.qwang.blog.model.param.BlogParam;
import com.qwang.blog.model.po.Blog;
import com.qwang.blog.model.po.Type;
import com.qwang.blog.repository.BlogRepository;
import com.qwang.blog.service.BlogService;
import com.qwang.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author qwang
 * @date 2020/7/29
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 获取单个Blog对象
     * 将Blog的Markdown内容转换成Html格式
     *
     * @param id 主键
     * @return 查询结果，查询不到返回null
     */
    @Override
    public Blog getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new NotFoundException("该帖子不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return blog;
    }

    /**
     * 根据查询的参数动态拼接SQL，获取分页结果
     *
     * @param pageable 分页参数
     * @param blog 查询参数
     * @return 分页结果
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogParam blog) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(blog.getTitle())) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    /**
     * 获取帖子分页
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * 获取搜索分页结果
     *
     * @param query 待查询的关键字
     * @param pageable 参数参数
     * @return 分页集合
     */
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery("%" + query + "%", pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"), tagId);
            }
        }, pageable);
    }


    @Override
    public Page<Blog> listBlogByUserId(Long userId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("user");
                return criteriaBuilder.equal(join.get("id"), userId);
            }
        }, pageable);
    }
//    public List<Blog> listBlogByUserId(Long userId) {
//        Pageable pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "updatedTime"));
//        return blogRepository.findAllByUserId(userId);
//    }

    /**
     * 获取热门推荐
     *
     * @param topSize 条数
     * @return 帖子集合
     */
    @Override
    public List<Blog> listRecommendBlogTop(Integer topSize) {
        Pageable pageable = PageRequest.of(0, topSize, Sort.by(Sort.Direction.DESC, "updatedTime"));
        return blogRepository.findTop(pageable);
    }

    /**
     * 获取每日十大推荐
     *
     * @param topSize 条数
     * @return 帖子集合
     */
    @Override
    public List<Blog> listBlogTopTen(Integer topSize) {
        Pageable pageable = PageRequest.of(0, topSize, Sort.by(Sort.Direction.DESC, "updatedTime"));
        return blogRepository.findTopTen(pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        // 获取所有的年份
        List<String> years = blogRepository.findGroupByYear();
        // 用LinkedHashMap保证读取的顺序
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    /**
     * 保存帖子
     *
     * @param blog 帖子对象
     * @return 保存后的结果
     */
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreatedTime(new Date());
        blog.setUpdatedTime(new Date());
        blog.setViews(0);
        return blogRepository.save(blog);
    }

    /**
     * 更新帖子
     *
     * @param id 主键
     * @param blog 旧的帖子对象
     * @return 新的帖子对象
     */
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findById(id).orElse(null);
        if (b == null) {
            throw new NotFoundException("该帖子不存在");
        }
        // 将数据库中查到的帖子对象的一些字段赋值到新的帖子对象中
        // 因为直接更新的话，会导致新的对象会覆盖数据库中无需修改的那些字段
        blog.setUpdatedTime(new Date());
        blog.setCreatedTime(b.getCreatedTime());
        blog.setUser(b.getUser());
        blog.setViews(b.getViews());
        return blogRepository.save(blog);
    }

    /**
     * 删除帖子
     * @param id 主键
     */
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
