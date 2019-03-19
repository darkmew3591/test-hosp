package corp.cencosud.test.models;

/**
 * Clase que representa los atributos minimos de un paciente.
 * @author caracena
 *
 */
public abstract class Paciente {
	
	/**
	 * Numero de ficha clinica.
	 */
	private int noHistorialClinico;
	
	/**
	 * Nombre del paciente.
	 */
	private String nombre;
	
	/**
	 * Edad del paciente.
	 */
	private int edad;
	
	/**
	 * Prioridad del paciente.
	 */
	private int prioridad;
	
	/**
	 * Riesgo del paciente.
	 */
	private double riesgo;
	
	public int getNoHistorialClinico() {
		return noHistorialClinico;
	}
	public void setNoHistorialClinico(int noHistorialClinico) {
		this.noHistorialClinico = noHistorialClinico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	public double getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(double riesgo) {
		this.riesgo = riesgo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paciente [noHistorialClinico=").append(noHistorialClinico).append(", nombre=").append(nombre)
				.append(", edad=").append(edad).append(", prioridad=").append(prioridad).append(", riesgo=")
				.append(riesgo).append("]");
		return builder.toString();
	}
	
}
