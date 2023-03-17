package com.freedom.system.repository.crud;

import com.freedom.common.repository.CrudPageRepository;
import com.freedom.system.entity.crud.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface UserRepository extends CrudPageRepository<UserEntity, UUID> {

    UserEntity findByNo(String no);

    @Modifying
    @Query(value = " UPDATE UserEntity user " +
            "   SET user.tryCount = :tryCount" +
            "     , user.tryTime = :tryTime " +
            " WHERE user.id = :id")
    int updateTryCount(@Param("id") UUID id,
                       @Param("tryCount") int tryCount,
                       @Param("tryTime") Date tryTime);

    // 修改密码
    @Modifying
    @Query(" UPDATE UserEntity e" +
            "   SET e.password = :password" +
            "      ,e.status = '1'" +
            " WHERE e.no = :no")
    void changePassword(
            @Param(value = "password") String password,
            @Param(value = "no") String no
    );
}
