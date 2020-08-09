package parciales_y_extras;

import estructuras.grafo.Arista;
import estructuras.grafo.Grafo;
import estructuras.grafo.GrafoImplListAdy;
import estructuras.grafo.Vertice;
import estructuras.grafo.VerticeImplListAdy;
import estructuras.listas.ListaGenerica;

/*
 * Implemente la clase Parcial y el método
	
	??? resolver (Grafo<???> ciudades, String origen, String destino)

			
	Se quiere calcular el máximo tiempo en tránsito desde una ciudad origen hacia una ciudad destino,
	teniendo en cuenta que debido a la pandemia no todas las rutas existentes se encuentran habilitadas.
	Para cada ciudad se conoce el nombre y la cantidad días que una persona en tránsito puede permanecer
	alli.
	En este ejemplo, para llegar desde La Plata a Suipacha, el camino que permite más días de tránsito y
	se encuentra habilitado, tiene un tiempo de tránsito 11 (La Plata, Quilmes, Moreno, Carlos Keen,
	Suipacha). ERROR DE ENUNCIADO, según la imagen, el mayor tiempo de tránsito es 16 (La Plata, Quilmes,
	Moreno, Abasto, Cañuelas, Navarro, Suipacha).
	
	Nota: 
			
	- Complete en la firma del método los tipos de datos ejemplificados con signo de interrogación.
	- Debe verificar la existencia de la ciudad origen y la ciudad destino.
	- No se puede pasar dos veces por el mismo lugar.
	- En caso de no existir un camino posible, debe devolver 0.
	- Use los métodos de Grafo y Listas vistos en clase.
 */

public class Parcial2 {

	private Grafo<Ciudad> mapaCiudades;

	private class Ciudad {

		private Ciudad(String nombre, int diasTransito) {
			super();
			this.nombre = nombre;
			this.diasTransito = diasTransito;
		}

		private String nombre;
		private int diasTransito;

		public String getNombre() {
			return this.nombre;
		}

		public int getDiastransito() {
			return this.diasTransito;
		}
	}

	public int resolver(Grafo<Ciudad> ciudades, String origen, String destino) {

		int[] maximoTiempo = { 0 };

		Vertice<Ciudad> v = buscarCiudad(origen);

		if (v != null && buscarCiudad(destino) != null) {
			mapaCiudades = ciudades;
			boolean[] marcas = new boolean[mapaCiudades.listaDeVertices().tamanio() + 1];
			resolver(v, destino, marcas, v.dato().getDiastransito(), maximoTiempo);
		}
		return maximoTiempo[0];
	}

	private void resolver(Vertice<Ciudad> v, String destino, boolean[] marcas, int tiempoActual, int[] maximoTiempo) {

		marcas[v.getPosicion()] = true;
		if (v.dato().getNombre().equals(destino)) {

			if (tiempoActual > maximoTiempo[0])

				maximoTiempo[0] = tiempoActual;

		} else {
			ListaGenerica<Arista<Ciudad>> ady = mapaCiudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<Ciudad> arista = ady.proximo();
				Vertice<Ciudad> vertice = arista.verticeDestino();
				if (!marcas[vertice.getPosicion()] && (arista.peso() != 0)) {
					int t = tiempoActual + vertice.dato().getDiastransito();
					resolver(vertice, destino, marcas, t, maximoTiempo);
				}
			}
		}
		marcas[v.getPosicion()] = false;
	}

	private Vertice<Ciudad> buscarCiudad(String nombre) {
		ListaGenerica<Vertice<Ciudad>> vertices = mapaCiudades.listaDeVertices();
		Vertice<Ciudad> v = null;
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().getNombre().equals(nombre))
				return v;
		}
		
		if (vertices.fin())
			return null;
		
		return v;

	}

	public static void main(String[] args) {

		Grafo<Ciudad> grafo = new GrafoImplListAdy<Ciudad>();
		Parcial2 parcial = new Parcial2();

		Vertice<Ciudad> a = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Quilmes", 3));
		Vertice<Ciudad> b = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("La Plata", 1));
		Vertice<Ciudad> c = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Pinamar", 0));
		Vertice<Ciudad> d = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Moreno", 2));
		Vertice<Ciudad> e = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Abasto", 4));
		Vertice<Ciudad> f = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Carlos Keen", 2));
		Vertice<Ciudad> g = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Cañuelas", 2));
		Vertice<Ciudad> h = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Suipacha", 3));
		Vertice<Ciudad> i = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Navarro", 1));
		Vertice<Ciudad> j = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Saladillo", 2));
		Vertice<Ciudad> k = new VerticeImplListAdy<Ciudad>(parcial.new Ciudad("Lobos", 1));

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(g);
		grafo.agregarVertice(h);
		grafo.agregarVertice(i);
		grafo.agregarVertice(j);
		grafo.agregarVertice(k);

		// 1 = ruta habilitada
		// 0 = ruta no habilitada

		grafo.conectar(a, b, 1);
		grafo.conectar(a, d, 1);
		grafo.conectar(b, a, 1);
		grafo.conectar(b, c, 1);
		grafo.conectar(b, e, 0);
		grafo.conectar(c, b, 1);
		grafo.conectar(d, a, 1);
		grafo.conectar(d, f, 1);
		grafo.conectar(d, e, 1);
		grafo.conectar(e, b, 0);
		grafo.conectar(e, d, 1);
		grafo.conectar(e, g, 1);
		grafo.conectar(f, d, 1);
		grafo.conectar(f, h, 1);
		grafo.conectar(g, e, 1);
		grafo.conectar(g, i, 1);
		grafo.conectar(h, f, 1);
		grafo.conectar(h, i, 1);
		grafo.conectar(i, g, 1);
		grafo.conectar(i, k, 0);
		grafo.conectar(i, j, 1);
		grafo.conectar(i, h, 1);
		grafo.conectar(j, i, 1);
		grafo.conectar(k, i, 0);

		int rta = parcial.resolver(grafo, "La Plata", "Suipacha");
		System.out.print(rta);

	}
}
