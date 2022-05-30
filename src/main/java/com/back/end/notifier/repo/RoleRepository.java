package com.back.end.notifier.repo;

import com.back.end.notifier.entity.Role;
import com.back.end.notifier.enums.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByUserRole(UserRole userRole);
}
