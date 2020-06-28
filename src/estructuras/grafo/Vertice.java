package estructuras.grafo;

/**
 * Representa una Vertice de un grafo.
 * 
 * @author juan
 *
 */
public interface Vertice<T> {
	
	public T dato();
	public void setDato(T dato);
	public int getPosicion();

}
