# GeospatialJPASample
Absolutely minimal sample project to show spatial queries in JPA with Spring Boot and embedded H2

Run it and see how it works.

It shows correctly defining spatial queries with JPA, plus the subtle quirks that make H2 work with h2gis.

In this example we will keep a database of UFO sightings, and then find the sightings that occurred
inside the Bermuda Triangle.

# Quick run

Clone or unzip the files.

    mvn clean package
    java -jar target/GeospatialJpaSample-1.0-SNAPSHOT.jar

It will initialize H2, insert a couple of objects, and then find the objects within a polygon.

# Some key non-obvious points

Specifying locations requires creating a Geometry from a Coordinate. Specifying a polygon boundary also
is non-obvious. It requires a linear ring, which requires the first and last point to be equal. This
linear ring is then turned into a Polygon. Then it can be used in a within query.

The h2gis module requires a very specific version of H2. It also requires some initialization SQL code
to run to activate the GIS functions.

# To do
Make this sample also work with PostGIS.
