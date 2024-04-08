package arcs;

public class Arc {
	private String source, destination;
	private int valeur;
	
	public Arc(String src, String dest, int val) {
		source = src;
		destination = dest;
		valeur = val;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public int getValuation() {
		return valeur;
	}

}
