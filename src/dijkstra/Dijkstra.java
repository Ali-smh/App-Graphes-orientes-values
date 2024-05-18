package dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import graphe.IGrapheConst;

public class Dijkstra {
    private final static int INFINI = Integer.MAX_VALUE - 1;
    
    private static String getSommetMin(List<String> sommets, Map<String, Integer> dist) {
    	String sommetMin = null;
        int distanceMin = INFINI;

        for (String sommet : sommets) {
            if (dist.containsKey(sommet) && dist.get(sommet) < distanceMin) {
                distanceMin = dist.get(sommet);
                sommetMin = sommet;
            }
        }
        return sommetMin;
    }

    public static void dijkstra(IGrapheConst g, String src, Map<String, Integer> dist, Map<String, String> prev) {
        List<String> sommetsRestants = new ArrayList<>(g.getSommets());
        dist.put(src, 0);
        prev.put(src, null);

        while (!sommetsRestants.isEmpty()) {
            String sommetMin = getSommetMin(sommetsRestants, dist);
            
            if (sommetMin == null) break;

            sommetsRestants.remove(sommetMin);

            for (String successeur : g.getSucc(sommetMin)) {
                int nouvelleDistance = dist.get(sommetMin) + g.getValuation(sommetMin, successeur);
                if (!dist.containsKey(successeur) || nouvelleDistance < dist.get(successeur)) {
                    dist.put(successeur, nouvelleDistance);
                    prev.put(successeur, sommetMin);
                }
            }
        }
    }
}
