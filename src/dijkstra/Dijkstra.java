package dijkstra;

import java.util.*;
import graphe.IGrapheConst;

public class Dijkstra {
    private final static int INFINI = Integer.MAX_VALUE - 1;

    public static void dijkstra(IGrapheConst g, String src, Map<String, Integer> dist, Map<String, String> prev) {
        // Initialisation
        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(dist.getOrDefault(s1, INFINI), dist.getOrDefault(s2, INFINI)));
        dist.put(src, 0);
        prev.put(src, null);
        pq.add(src);

        while (!pq.isEmpty()) {
            String sommetMin = pq.poll();

            for (String successeur : g.getSucc(sommetMin)) {
                int nouvelleDistance = dist.get(sommetMin) + g.getValuation(sommetMin, successeur);

                if (nouvelleDistance < dist.getOrDefault(successeur, INFINI)) {
                    dist.put(successeur, nouvelleDistance);
                    prev.put(successeur, sommetMin);
                    pq.add(successeur);
                }
            }
        }
    }
}
