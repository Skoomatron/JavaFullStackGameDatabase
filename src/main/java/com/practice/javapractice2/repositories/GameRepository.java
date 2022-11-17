package com.practice.javapractice2.repositories;

import com.practice.javapractice2.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}
