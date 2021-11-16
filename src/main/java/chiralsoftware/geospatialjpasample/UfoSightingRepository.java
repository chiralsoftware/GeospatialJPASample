package chiralsoftware.geospatialjpasample;

import java.util.List;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository to manage UFO sightings
 */
public interface UfoSightingRepository extends JpaRepository<UfoSighting, Long> {
    
    @Query(value="select us from UfoSighting us where within(us.location, :r) = true")
    List<UfoSighting> findAllByLocationWithin(@Param("r") Geometry geometry);
//    GeoResults<UfoSighting> findAllByLocationWithin(@Param("r") Geometry geometry);
    
}
