package com.huntercodexs.repository;

import com.huntercodexs.model.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerProcedureJdbcRepository {

    private final DataSource dataSource;

    public List<CustomerEntity> searchByName(String name) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withSchemaName("DB2INST1")
                .withProcedureName("SEARCH_CUSTOMERS_BY_NAME")
                .returningResultSet("C1", new CustomerRowMapper());

        Map<String, Object> result = jdbcCall.execute(Map.of("P_NAME", name));

        return (List<CustomerEntity>) result.get("C1");
    }

    /**
     * RowMapper para converter cursor -> objeto
     */
    private static class CustomerRowMapper implements RowMapper<CustomerEntity> {

        @Override
        public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerEntity c = new CustomerEntity();
            c.setId(rs.getLong("ID"));
            c.setName(rs.getString("NAME"));
            c.setEmail(rs.getString("EMAIL"));
            c.setAge(rs.getInt("AGE"));
            return c;
        }
    }
}
