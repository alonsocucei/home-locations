package com.luxoft.homelocations.dao.mappers;

import com.luxoft.homelocations.representations.Home;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author jjsanche
 */
public class HomeListMapper implements ResultSetMapper<List<Home>> {

    public List<Home> map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        List<Home> homes = new ArrayList<>();
        
        do {
            homes.add(
                    new Home(
                            r.getInt("id"),
                            r.getString("address1"),
                            r.getString("address2"),
                            r.getString("city"),
                            r.getString("state"),
                            r.getString("zipcode"),
                            r.getString("country")
                    )
            );
        
        } while (r.next());

        return homes;
    }
}
