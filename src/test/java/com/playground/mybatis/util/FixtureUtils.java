package com.playground.mybatis.util;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FixtureUtils {
    private FixtureUtils() {
        // do nothing
    }

    private static Map<String, Object> newDefaultParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("created_by", "unit-test");
        params.put("created_on", LocalDateTime.now());
        params.put("updated_by", "unit-test");
        params.put("updated_using", "unit-test");
        params.put("updated_on", LocalDateTime.now());

        return params;
    }

    public static Long newPerson(DataSource dataSource, String firstName, String lastName, String email) {
        Map<String, Object> params = newDefaultParams();
        params.put("first_name", firstName);
        params.put("last_name", lastName);
        params.put("email", email);

        return (Long) new SimpleJdbcInsert(dataSource)
                .withTableName("person")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);
    }
}
