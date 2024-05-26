package graphe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import arcs.Arc;

public class GrapheLArcs extends Graphe {
    private List<Arc> arcs;

    public GrapheLArcs() {
        arcs = new ArrayList<>();
    }

    public GrapheLArcs(String str) {
        this();
        peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
        	arcs.add(new Arc(noeud, "", 0));
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
        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        Iterator<Arc> it = arcs.iterator();
        while (it.hasNext()) {
            Arc arc = it.next();
            if (arc.getSource().equals(noeud) || arc.getDestination().equals(noeud)) {
                it.remove();
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
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
        List<String> sommets = new ArrayList<>();
        for (Arc arc : arcs) {
            if (!sommets.contains(arc.getSource())) {
                sommets.add(arc.getSource());
            }
            if (!sommets.contains(arc.getDestination())) {
                sommets.add(arc.getDestination());
            }
        }
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet) && !successeurs.contains(arc.getDestination())) {
                successeurs.add(arc.getDestination());
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
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet) || arc.getDestination().equals(sommet)) {
                return true;
            }
        }
        return false;
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


