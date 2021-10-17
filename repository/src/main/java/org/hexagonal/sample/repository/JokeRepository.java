package org.hexagonal.sample.repository;

import org.hexagonal.sample.entity.JokeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<JokeEntity, Long> {

}
