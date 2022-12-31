package com.kyawnaingsoe.student.repository;

import com.kyawnaingsoe.student.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
