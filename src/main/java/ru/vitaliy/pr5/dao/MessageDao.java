package ru.vitaliy.pr5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitaliy.pr5.entity.Message;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {
}
