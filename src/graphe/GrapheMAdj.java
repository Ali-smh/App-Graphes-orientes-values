package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheMAdj extends Graphe {
	private int[][] matrice;
    private Map<String, Integer> indices;
    
    public GrapheMAdj() {
        matrice = new int[0][0];
        indices = new HashMap<>();
    }

    public GrapheMAdj(String str) {
    	this();
		this.peupler(str);
	}

	@Override
    public void ajouterSommet(String noeud) {
        if (!this.contientSommet(noeud)) {
            indices.put(noeud, indices.size());

            int newTaille = indices.size();
            int[][] newMatrice = new int[newTaille][newTaille];
            for (int i = 0; i < matrice.length; i++) {
                System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice.length);
            }
            matrice = newMatrice;
        }
    }

	public void ajouterArc(String source, String destination, Integer valeur) {
		if (valeur < 0)
	    	throw new IllegalArgumentException("L'arc "+source+"-"+destination+" admet une valuation négative: "+valeur);
		
		if (!this.contientSommet(source))
	        this.ajouterSommet(source);
	    
	    if (!this.contientSommet(destination))
	        this.ajouterSommet(destination);
	    
		
	    int indiceSource = indices.get(source);
	    int indiceDestination = indices.get(destination);

	    if (matrice[indiceSource][indiceDestination] > 0)
	    	throw new IllegalArgumentException("L'arc "+source+"-"+destination+" est déjà présent");
	    
	    

	    matrice[indiceSource][indiceDestination] = valeur;
	}


    @Override
    public void oterSommet(String noeud) {
        if (this.contientSommet(noeud)) {
            int indice = indices.get(noeud);
            indices.remove(noeud);

            for (int i = 0; i < matrice.length; i++) {
                matrice[indice][i] = 0;
                matrice[i][indice] = 0;
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (this.contientSommet(source) && this.contientSommet(destination)) {
            int indiceSource = indices.get(source);
            int indiceDestination = indices.get(destination);

            if (matrice[indiceSource][indiceDestination] == 0)
            	throw new IllegalArgumentException("L'arc "+source+"-"+destination+ " n'est pas présent");
            

            matrice[indiceSource][indiceDestination] = 0;
        } else
        	throw new IllegalArgumentException("L'arc "+source+"-"+destination+ " n'est pas présent");
        
    }

    @Override
    public List<String> getSucc(String sommet) {
    	List<String> successeurs = new ArrayList<String>();
		for (String destination : getSommets()) {
			if (getValuation(sommet, destination) > 0) {
				successeurs.add(destination);
			}
		}
		return successeurs;
    }


    @Override
    public List<String> getSommets() {
    	return new ArrayList<String>(indices.keySet());
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)] > 0;
    }

}
