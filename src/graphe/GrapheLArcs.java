package graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import arcs.Arc;

public class GrapheLArcs extends Graphe {
    private List<Arc> arcs;
    private Set<String> sommets;

    public GrapheLArcs() {
        arcs = new ArrayList<>();
        sommets = new HashSet<>();
    }

    public GrapheLArcs(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            sommets.add(noeud);
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("L'arc " + source + "-" + destination + " admet une valuation négative: " + valeur);
        }
        if (this.contientArc(source, destination)) {
            throw new IllegalArgumentException("L'arc " + source + "-" + destination + " est déjà présent");
        }
        Arc arc = new Arc(source, destination, valeur);
        arcs.add(arc);
        sommets.add(source);
        sommets.add(destination);
    }

    @Override
    public void oterSommet(String noeud) {
        if (this.contientSommet(noeud)) {
            Iterator<Arc> it = arcs.iterator();
            while (it.hasNext()) {
                Arc arc = it.next();
                if (arc.getSource().equals(noeud) || arc.getDestination().equals(noeud)) {
                    it.remove();
                }
            }
            sommets.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!this.contientArc(source, destination)) {
            throw new IllegalArgumentException("L'arc " + source + "-" + destination + " n'est pas présent");
        }
        Iterator<Arc> it = arcs.iterator();
        while (it.hasNext()) {
            Arc arc = it.next();
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(sommets);
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        if (this.contientSommet(sommet)) {
            for (Arc arc : arcs) {
                if (arc.getSource().equals(sommet) && !arc.getDestination().isEmpty()) {
                    successeurs.add(arc.getDestination());
                }
            }
        }
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return arc.getValuation();
            }
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return sommets.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return true;
            }
        }
        return false;
    }
}
