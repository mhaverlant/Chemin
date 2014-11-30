package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haverlantmatthias on 13/11/2014.
 */
public class Vertex {
    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public List<Edge> getEdges(){
        return edges;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }
}
