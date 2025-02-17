package com.mendax47.learnspringboot.module.user.repository;

import com.mendax47.learnspringboot.module.user.User;

import com.mendax47.learnspringboot.module.user.dtos.responses.CustomUserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository< User, Long >, JpaSpecificationExecutor< User > {
    User findByUsernameOrEmail( String username, String email );

    boolean existsByUsername( String username );

    boolean existsByEmail( String email );

    @EntityGraph( attributePaths = { "roles" } )
    @Query( """
                SELECT
                    user
                FROM
                    User user
            """ )
    Page< CustomUserResponseDTO > findAllUsers( Pageable pageable );

    @EntityGraph( attributePaths = { "roles" } )
    CustomUserResponseDTO findUserById( Long id );
}