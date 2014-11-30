package test;

import domain.Graph;
import domain.Vertex;
import domain.Edge;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
        private Vertex lille = new Vertex("Lille");
        private Vertex paris = new Vertex("Paris");
        private Vertex reims = new Vertex("Reims");
        private Vertex nancy = new Vertex("Nancy");
        private Vertex lyon = new Vertex("Lyon");
        private Vertex marseille = new Vertex("Marseille");
        private Vertex lemans = new Vertex("Le Mans");
        private Vertex nantes = new Vertex("Nantes");
        private Vertex bordeaux = new Vertex("Bordeaux");
        private Vertex toulouse = new Vertex("Toulouse");
        private Vertex clermont = new Vertex("Clermont Ferrant");
        private Vertex montpellier = new Vertex("Montpellier");
        private Vertex cycle1 = new Vertex("Cycle1");
        private Vertex cycle2 = new Vertex("Cycle2");
        private Vertex cycle3 = new Vertex("Cycle3");
        private Vertex cycle4 = new Vertex("Cycle4");
        private Vertex noway1 = new Vertex("Noway1");
        private Vertex noway2 = new Vertex("Noway2");
        private Vertex noway3 = new Vertex("Noway3");
        private Vertex noway4 = new Vertex("Noway4");

        @Before
        public void setup() {
            lille.connectTo(reims, 206);
            lille.connectTo(paris, 222);
            lille.connectTo(nancy, 418);

            reims.connectTo(paris, 144);
            reims.connectTo(nancy, 245);
            reims.connectTo(lyon, 489);

            paris.connectTo(lyon, 465);
            paris.connectTo(lemans, 208);
            paris.connectTo(clermont, 423);

            lyon.connectTo(clermont, 166);
            lyon.connectTo(marseille, 313);
            lyon.connectTo(montpellier, 304);

            lemans.connectTo(nantes, 189);
            lemans.connectTo(bordeaux, 443);

            nantes.connectTo(bordeaux, 347);

            bordeaux.connectTo(toulouse, 243);

            toulouse.connectTo(montpellier, 245);

            montpellier.connectTo(marseille, 169);
            montpellier.connectTo(toulouse, 245);

            marseille.connectTo(montpellier, 169);

            clermont.connectTo(lyon, 166);
            clermont.connectTo(montpellier, 333);
            clermont.connectTo(marseille, 474);
            /*Remarque pour s'assurer que le chemin le plus court passe par un cycle
            on va mettre des poids negatif sur les aretes du cycle
             */
            cycle1.connectTo(cycle2,1);
            cycle1.connectTo(cycle3,-1);

            cycle3.connectTo(cycle4,-1);

            cycle4.connectTo(cycle1,-1);

            noway1.connectTo (noway2,1);
            noway3.connectTo(noway4,1);
        }
        @Test
        public void getDistanceForTwoAdjacentVertices() {
            Graph graph = new Graph(paris, lyon);
            assertEquals(graph.getDistance("Paris", "Lyon"/*,0*/), 465);
        }
        @Test
        public void getDistanceGrapheVide() {
            Graph graph = new Graph();
            assertEquals(graph.getDistance("Paris", "Lyon"), 0);
        }
        @Test
        public void getDistanceDeparNonValide() {
            Graph graph = new Graph(paris,lyon);
            assertEquals(graph.getDistance("Lille", "Lyon"), 0);
        }
        @Test
        public void getDistanceTargetNonValide() {
            Graph graph = new Graph(paris,lyon);
            assertEquals(graph.getDistance("Lyon", "Lille"), 0);
        }
        @Test
        public void getDistanceUnIntermedaire() {
        Graph graph = new Graph(paris,lyon,marseille);
        assertEquals(graph.getDistance("Paris", "Marseille"), 778);
        }
        @Test
        public void getDistanceDeuxIntermedaire() {
            Graph graph = new Graph(lille,reims,lyon,marseille);
            assertEquals(graph.getDistance("Lille", "Marseille"), 1008);
        }
        @Test
        public void getDistanceMinimum() {
            Graph graph = new Graph(paris,lyon,clermont);
            assertEquals(graph.getDistance("Paris", "Clermont Ferrant"), 423);
        }
        @Test
        public void getDistanceSansCycle() {
            Graph graph = new Graph(cycle1,cycle2,cycle3,cycle4);
            assertEquals(graph.getDistance("Cycle1", "Cycle2"), 1);
        }
        @Test
        public void GrapheaUnPoint() {
            Graph graph = new Graph(cycle1);
            assertEquals(graph.getDistance("Cycle1", "Cycle1"),0);
        }
        @Test
        public void GrapheDepartTarget() {
            Graph graph = new Graph(cycle1,cycle2);
            assertEquals(graph.getDistance("Cycle1", "Cycle1"),0);
        }
        @Test
        public void GraphePasChemin() {
            Graph graph = new Graph(noway1,noway2,noway3,noway4);
            assertEquals(graph.getDistance("Noway1", "Noway4"),0);
        }

        



}
