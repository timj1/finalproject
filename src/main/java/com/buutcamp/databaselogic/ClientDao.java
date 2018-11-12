package com.buutcamp.databaselogic;

import com.buutcamp.objects.Messages;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ClientDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Messages> getAllData() {
        String query = "SELECT * FROM messages";
        List<Messages> messages = jdbcTemplate.query(query,new ClientRowMapper());
        return messages;
    }

    public boolean createRow(Messages messages) {
        String query = "INSERT INTO messages (date_create, date_update, title, " +
                "message, username) VALUES (?,?,?,?,?)";

        Object[] args = new Object[] {messages.getCreateDate(),messages.getUpdateDate(),messages.getTitleName(),
                messages.getMessage(),messages.getUserName()};
        System.out.println(args);

        return jdbcTemplate.update(query,args) == 1;
    }

    public boolean updateRow(Messages messages) {
        String query = "UPDATE messages SET message=?, date_update=? WHERE id=?";

        Object[] args = new Object[] {messages.getMessage(),messages.getUpdateDate(),messages.getId()};

        return jdbcTemplate.update(query,args) == 1;
    }

}
