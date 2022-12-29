package org.example.repository;

import org.example.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

Card  findByCardnumber (String cardnumber); //свой запрос
Card findByName (String name);

// @Query("SELECT s FROM Group g where g.id =: id")
//  Group customSelect (@Param("id") int id); // свой поиск по id




}
