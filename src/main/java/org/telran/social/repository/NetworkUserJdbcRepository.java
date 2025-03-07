package org.telran.social.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.telran.social.model.NetworkUser;

import java.util.List;

@Primary
@Repository
public class NetworkUserJdbcRepository implements NetworkUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<NetworkUser> getAll() {
        String sql = "SELECT * FROM network_users";
        List<NetworkUser> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NetworkUser.class));
        return query;
    }

    @Override
    public NetworkUser getById(Long id) {
        String sql = "SELECT * FROM network_users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(NetworkUser.class), id);
    }

    @Override
    public NetworkUser create(NetworkUser networkUser) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<NetworkUser> filter(Integer minAge, Integer maxAge) {
        return List.of();
    }
}
