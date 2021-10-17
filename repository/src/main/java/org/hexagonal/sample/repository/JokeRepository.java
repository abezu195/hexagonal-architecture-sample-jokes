package org.hexagonal.sample.repository;

import org.hexagonal.sample.entity.JokeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface JokeRepository extends JpaRepository<JokeEntity, Long> {

//    Page<JokeEntity> find
}
