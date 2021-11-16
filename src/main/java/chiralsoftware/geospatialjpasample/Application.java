package chiralsoftware.geospatialjpasample;

import static java.lang.System.out;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demonstrate a geospatial application using JPA
 */
@SpringBootApplication
@EnableJpaRepositories("chiralsoftware.geospatialjpasample")
@EntityScan("chiralsoftware.geospatialjpasample")
public class Application implements ApplicationRunner {
    
    @Autowired
    private UfoSightingRepository ufoSightingRepository;
    
    public static void main(String[] args) throws Exception {
        out.println("starting the spring application");
        SpringApplication.run(Application.class, args);
    }
    

    @Override
    public void run(ApplicationArguments args) throws Exception {
        out.println("starting the run method");
        
        final GeometryFactory gf = new GeometryFactory();

        final UfoSighting sightingInTriangle = new UfoSighting();
        sightingInTriangle.setNote("saw a UFO in the Bermuda Triangle");
        sightingInTriangle.setTime(Instant.now());
        sightingInTriangle.setLocation(gf.createPoint(triangleCenter));
        saveIt(sightingInTriangle);
        
        final UfoSighting sightingInArea51 = new UfoSighting();
        sightingInArea51.setNote("saw a UFO in Area 51");
        sightingInArea51.setTime(Instant.now());
        sightingInArea51.setLocation(gf.createPoint(area51));
        saveIt(sightingInArea51);
        
        out.println("It has been saved. Now doing a search.");
        final Geometry triangle = gf.createPolygon(gf.createLinearRing(bermudaTriangle));
        out.println("Here is the Bermuda triangle: " + triangle);
        final List<UfoSighting> sightings = ufoSightingRepository.findAllByLocationWithin(triangle);
        if(sightings == null) {
            out.println("the result was NULL!");
            return;
        }
        out.println("Inside the Bermuda Triangle, these were the sightings:");
        final Iterator<UfoSighting> it = sightings.iterator();
        while(it.hasNext()) {
            final UfoSighting ufo = it.next();
            out.println("Found this sighting: " + ufo);
        }
    }
    
    private static final Coordinate miami = new Coordinate(-80.2,25.7);
    private static final Coordinate sanJuan = new Coordinate(-66,18.4);
    private static final Coordinate bermuda= new Coordinate(-64.7,32.3);
    
    /** To do a within query, we need a linear ring:
     https://locationtech.github.io/jts/javadoc/org/locationtech/jts/geom/LinearRing.html
     A linear ring has either no points or four or more points, and the first
     and last point must be equal so the ring is closed. */
    private static final Coordinate[] bermudaTriangle = { miami, sanJuan, bermuda, miami };
    
     private static final Coordinate triangleCenter = new Coordinate(-71,25);
    
    private static final Coordinate area51 = new Coordinate(-115,37);
    
    @Transactional
    private void saveIt(UfoSighting sighting) {
        ufoSightingRepository.save(sighting);
        out.println("Saved this UFO sighting: " + sighting);
    }
    
}
