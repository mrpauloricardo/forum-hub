package br.com.alura.forumhub.repository;

import br.com.alura.forumhub.entity.Role;
import br.com.alura.forumhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}