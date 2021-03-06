package com.example.blog.model.repository;

import com.example.blog.model.entity.Blog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BlogRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("select c from Blog c", Blog.class);
        return query.getResultList();
    }


    public void save(Blog blog) {
        if (blog.getId() != null) {
            entityManager.merge(blog);
        } else {
            entityManager.persist(blog);
        }
    }


    public Blog findById(int id) {
        return null;
    }
}
