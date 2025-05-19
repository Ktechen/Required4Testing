package org.example.required4testing.repositories;

import org.example.required4testing.models.UserLevelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserLevelRepository extends JpaRepository<UserLevelType, Integer> {
}
