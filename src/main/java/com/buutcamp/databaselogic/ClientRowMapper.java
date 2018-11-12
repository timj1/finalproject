package com.buutcamp.databaselogic;

import com.buutcamp.objects.Messages;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Messages> {

    public Messages mapRow(ResultSet resultSet, int i) throws SQLException {
        Messages messages = new Messages();
        messages.setId(resultSet.getInt("id"));
        messages.setCreateDate(resultSet.getTimestamp("date_create"));
        messages.setUpdateDate(resultSet.getTimestamp("date_update"));
        messages.setTitleName(resultSet.getString("title"));

        messages.setMessage(resultSet.getString("message"));
        messages.setUserName(resultSet.getString("username"));
        System.out.println("RowMapper" + messages);
        return messages;
    }
}
