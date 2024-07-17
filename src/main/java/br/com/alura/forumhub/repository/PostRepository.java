package br.com.alura.forumhub.repository;

import br.com.alura.forumhub.entity.Post;
import br.com.alura.forumhub.entity.Role;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}