package org.telran.social.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.telran.social.model.Message;

import java.util.List;

@Repository
public class MessageJdbcRepository implements MessageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getAllByUserId(Long userId) {
        String sql = "SELECT * FROM messages WHERE sender_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), userId);
    }

    @Override
    public Message getById(Long id) {
        String sql = "SELECT * FROM messages WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Message.class), id);
    }

    @Override
    public Message create(Message message) {
        String sql = "INSERT INTO messages (text, sender_id, receiver_id, status) VALUES (?, ?, ?, ?)";
        int update = jdbcTemplate.update(sql,
                message.getText(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getStatus().name());
        if (update > 0) {
            return jdbcTemplate.queryForObject("SELECT * FROM messages ORDER BY id DESC LIMIT 1", new BeanPropertyRowMapper<>(Message.class));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
