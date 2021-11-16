package chiralsoftware.geospatialjpasample;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

/**
 * Track information about UFO sightings
 */
@Entity
public class UfoSighting implements Serializable {
    
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    
    @Column(name="times")
    private Instant time;
    private String note;
    
//     @Column(columnDefinition = "geometry")
    /** For some reason the spatial query will not work if a Coordinate column type is used */
    private Geometry location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Geometry getLocation() {
        return location;
    }

    public void setLocation(Geometry location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UfoSighting{" + "id=" + id + ", time=" + time + ", note=" + note + ", location=" + location + '}';
    }


}
