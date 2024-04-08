package graphe;

import java.util.ArrayList;
import java.util.List;

import arcs.Arc;

public class GrapheLArcs extends Graphe{
	private ArrayList<String> sommets;
	private ArrayList<Arc> arcs;
	
	public GrapheLArcs() {
		sommets = new ArrayList<String>();
		arcs = new ArrayList<Arc>();
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!sommets.contains(noeud))
			sommets.add(noeud);
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(valeur < 0)
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" admet une valuation négative: "+valeur);
		for(Arc arc : arcs)
				if (arc.getSource().equals(source) &&
					arc.getDestination().equals(destination)) {
				}
		if(this.contientArc(source, destination)) {
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" est déjà présent");
		}
		Arc arc = new Arc(source, destination, valeur);
		arcs.add(arc);
	}

	@Override
	public void oterSommet(String noeud) {
		sommets.remove(noeud);
	}

	@Override
	public void oterArc(String source, String destination) {
		for(int i = 0; i < arcs.size(); ++i) {
			if(arcs.get(i).getSource().equals(source) && arcs.get(i).getDestination().equals(destination)) {
				arcs.remove(i);
			}
		}
		if(!this.contientArc(source, destination))
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+ " n'est pas présent");
	}

	@Override
	public List<String> getSommets() {
		return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		ArrayList<String> successeurs = new ArrayList<String>();
		for(Arc arc : arcs)
			if (arc.getSource().equals(sommet))
				successeurs.add(arc.getDestination().toString());
		return successeurs;
	}

	@Override
	public int getValuation(String src, String dest) {
		for(int i = 0; i < arcs.size(); ++i) {
			if(arcs.get(i).getSource().equals(src) && arcs.get(i).getDestination().equals(dest))
				return arcs.get(i).getValuation();
		}
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		for(String so : sommets)
			if(so.equals(sommet))
				return true;
		return false;
	}

	@Override
	public boolean contientArc(String src, String dest) {
		for(Arc arc : arcs)
			if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
				return true;
		return false;
	}

}
