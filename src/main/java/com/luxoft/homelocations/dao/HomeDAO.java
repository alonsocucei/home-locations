package com.luxoft.homelocations.dao;

import com.luxoft.homelocations.dao.mappers.HomeListMapper;
import com.luxoft.homelocations.dao.mappers.HomeMapper;
import com.luxoft.homelocations.representations.Home;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 *
 * @author jjsanche
 */
public interface HomeDAO {
    @Mapper(HomeMapper.class)
    @SqlQuery("select * from homes where id = :id")
    public Home getHomeById(@Bind("id") int id);
    
    @Mapper(HomeListMapper.class)
    @SqlQuery("select * from homes")
    public List<Home> getHomes();
    
    @GetGeneratedKeys
    @SqlUpdate("insert into homes (id, address1, address2, city, state, zipcode, country) values (NULL, :address1, :address2, :city, :state, :zipcode, :country)")
    public int createHome(@Bind("address1") String address1, @Bind("address2") String address2,
            @Bind("city") String city, @Bind("state") String state, @Bind("zipcode") String zipcode,
            @Bind("country") String country);
    
    @SqlUpdate("update homes set address1=:address1, address2=:address2, city=:city, state=:state, zipcode=:zipcode, country=:country where id=:id")
    public void updateHome(@Bind("id") int id, @Bind("address1") String address1, @Bind("address2") String address2,
            @Bind("city") String city, @Bind("state") String state, @Bind("zipcode") String zipcode,
            @Bind("country") String country);
    
    @SqlUpdate("delete from contact where id=:id")
    public void deleteHome(@Bind("id") int id);
}   
