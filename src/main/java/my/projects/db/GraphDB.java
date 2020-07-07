package my.projects.db;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.Map;

public class GraphDB {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
                new File("data/cars"));

        graphDb.beginTx();

        Node car = graphDb.createNode(Label.label("Car"));
        car.setProperty("make", "tesla");
        car.setProperty("model", "model3");

        Node owner = graphDb.createNode(Label.label("Person"));
        owner.setProperty("firstName", "baeldung");
        owner.setProperty("lastName", "baeldung");

        owner.createRelationshipTo(car, RelationshipType.withName("owner"));


        Result result = graphDb.execute(
                "MATCH (c:Car) <-[owner]- (p:Person) " +
                        "WHERE c.make = 'tesla'" +
                        "RETURN p.firstName, p.lastName");
        String rows = "";
        while ( result.hasNext() ){
            Map<String,Object> row = result.next();
            for ( Map.Entry<String,Object> column : row.entrySet() ) {
                rows += column.getKey() + ": " + column.getValue() + "; ";
            }
            rows += "\n";
        }
        System.out.println(rows);
    }
}