package domain;


import javafx.scene.transform.MatrixType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haverlantmatthias on 13/11/2014.
 */
public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String From, String To) {
        /*Test Graph vide*/
        int distance = 0;
        int Ifrom = 0;
        int Ichemin = 0;
        int Ito = 0;
        int a = -1;
        int b= -1;
        int n =vertices.size();
        List<Vertex> Vertice = new ArrayList<Vertex>();
        List<Integer> dist = new ArrayList<Integer>();
        // Ajouter Si From ou To ne sont pas dans graphe...
        for (int i=0; i<vertices.size();i++) {
            if (vertices.get(i).getName() == From) {
                a = i;
            }
            if (vertices.get(i).getName() == To) {
                b = i;
            }
        }
        if (vertices.size() == 0 || a==-1 || b==-1 || a==b) {
            if (vertices.size() == 0){
                System.out.println("Graph Vide");
            }
            else if (a == -1){
                System.out.println("Point de depart non valide");
            }
            else if (b==-1){
                System.out.println("Destination non valide");
            }
            else{
                System.out.println("Destination = Depart");
            }
        }
        else {
            TousChemins (vertices.get(a),vertices.get(b),n,distance,dist,Vertice);
                distance = Minimum(dist);
        }
        return distance;
    }

    /*Recherche parmis tous les chemins possibles et on allimente la liste de distance*/
    public List<Integer> TousChemins(Vertex from, Vertex to,int n,int distance,List<Integer> dist,List<Vertex> Vertice){
        if (n  != 1 && from.getEdges().size() != 0) {
            for (Edge edge : from.getEdges()) {
                int distances;
                Vertex vertex = edge.getTarget();
                if (vertex == to && Apartient(vertex,vertices) && Apartient (vertex,Vertice)==false) {
                    distances = distance + edge.getDistance();
                    dist.add(distances);
                }
                else if (Apartient (vertex, vertices) && Apartient(vertex, Vertice)==false){
                    Vertice.add(vertex);
                    distances = distance + edge.getDistance();
                    TousChemins(vertex,to,n-1,distances,dist,Vertice);
                    Vertice.remove(vertex);
            /*on fait un remove du fait de la boucle for*/
                }
            }
        }
        return dist;
    }

    /*Recherche d'un vertex (point) dans une liste de points*/
    public boolean Apartient(Vertex vertex, List<Vertex> vertice){
        boolean resultat = false;
        for (Vertex vert : vertice) {
            if (vert == vertex) {
                resultat = true;
            }
        }
        return resultat;
    }

    /*Recherche du minimum dans une liste d'integer (entiers)
    Dans le cas ou la liste est vide on retourne distance=0 c'est pourquoi min est initialisée à 0*/
    public int Minimum(List<Integer> L){
        int min=0;
        if (L.size() != 0){
            min = Integer.MAX_VALUE;
            for (int element: L){
                if (element < min){
                    min= element;
                }
            }
        }
        return min;
    }

}
