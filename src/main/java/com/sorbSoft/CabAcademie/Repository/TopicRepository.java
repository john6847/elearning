package com.sorbSoft.CabAcademie.Repository;

import com.sorbSoft.CabAcademie.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dany on 15/05/2018.
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
