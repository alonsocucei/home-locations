package com.luxoft.homelocations.dao.mappers;

import com.luxoft.homelocations.representations.Home;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author jjsanche
 */
public class HomeMapper implements ResultSetMapper<Home> {
    public Home map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Home(
                r.getInt("id"),
                r.getString("address1"),
                r.getString("address2"),
                r.getString("city"),
                r.getString("state"),
                r.getString("zipcode"),
                r.getString("country")
        );
    }
}
