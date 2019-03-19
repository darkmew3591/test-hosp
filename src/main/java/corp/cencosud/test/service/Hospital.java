package corp.cencosud.test.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import corp.cencosud.test.models.Consulta;
import corp.cencosud.test.models.Paciente;
import corp.cencosud.test.models.PacienteAnciano;
import corp.cencosud.test.models.PacienteJoven;
import corp.cencosud.test.models.PacienteNino;

@Component
public class Hospital {

	public ArrayList<Paciente> getTodos() {
		return todos;
	}
	public void setTodos(ArrayList<Paciente> todos) {
		this.todos = todos;
	}

	/**
	 * Instancia de clase de servicio.
	 */
	@Autowired
	private PacienteService service;
	
	/**
	 * Nombre del hospital.
	 */
	private String nombre;
	
	/**
	 * Pacientes atendidos por el hospital.
	 */
	private ArrayList<Paciente> pacientesAtendidos;
	
	/**
	 * Pacientes pendientes de derivar segun prioridad.
	 */
	private ArrayList<Paciente> pacientesPendientes;
	
	/**
	 * Pacientes en espera de ser atendidos en consulta
	 */
	private ArrayList<Paciente> pacientesEnEspera;
	
	/**
	 * Variable que contendra todos los pacientes cargados.
	 */
	private ArrayList<Paciente> todos;
	
	private ArrayList<Consulta> consultas;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Paciente> getPacientesAtendidos() {
		return pacientesAtendidos;
	}
	public void setPacientesAtendidos(ArrayList<Paciente> pacientesAtendidos) {
		this.pacientesAtendidos = pacientesAtendidos;
	}
	public ArrayList<Paciente> getPacientesPendientes() {
		return pacientesPendientes;
	}
	public void setPacientesPendientes(ArrayList<Paciente> pacientesPendientes) {
		this.pacientesPendientes = pacientesPendientes;
	}
	public ArrayList<Paciente> getPacientesEnEspera() {
		return pacientesEnEspera;
	}
	public void setPacientesEnEspera(ArrayList<Paciente> pacientesEnEspera) {
		this.pacientesEnEspera = pacientesEnEspera;
	}
	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hospital [nombre=").append(nombre).append(", pacientesAtendidos=").append(pacientesAtendidos)
				.append(", pacientesPendientes=").append(pacientesPendientes).append(", pacientesEnEspera=")
				.append(pacientesEnEspera).append(", consultas=").append(consultas).append("]");
		return builder.toString();
	}
	
	   /**
     * Obtiene listado de todos los usuarios.
     * @param model
     * @return
     */
    @PostConstruct
    public void inicializar(){
    	ArrayList<Paciente> pacientes = service.obtenerPacientes();
    	this.setTodos(pacientes);
    	this.setPacientesPendientes(pacientes);
    	this.setPacientesAtendidos(new ArrayList<>());
    	this.setPacientesEnEspera(new ArrayList<>());
    	this.nombre = "Hospital Cencosud";
    	this.consultas = this.armarConsultas();
    }
    
    private ArrayList<Consulta> armarConsultas(){
    	ArrayList<Consulta>  consultas= new ArrayList<>();
    	Consulta consulta = new Consulta();
    	consulta.setNombreEspecialista("Naty Perez");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("Pediatria");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Miguel Navarro");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("Pediatria");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Marcela Rodriguez");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("Urgencia");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Silvana Abarca");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("Urgencia");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Oscar Bustamante");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("Urgencia");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Manuel Castro");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("CGI");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	consulta = new Consulta();
    	consulta.setNombreEspecialista("Homero Simpson");
    	consulta.setCantPacientes(0);
    	consulta.setEstado("Espera");//Ocupada
    	consulta.setTipoConsulta("CGI");//Pediatria Urgencia CGI
    	consultas.add(consulta);
    	
    	return consultas;
    	
    }
    
	public int hayConsultaDisponible(int edad, int prioridad) {
		int respuesta = -1;
			if (prioridad > 4) {//urgencia
				respuesta = this.consultaPorTipo("Urgencia");
			}
			else if (edad <= 15 && prioridad <=4) {//pediatria
				respuesta = this.consultaPorTipo("Pediatria");
			}
			else {//cgi
				respuesta = this.consultaPorTipo("CGI");
			}
		
		return respuesta;
	}
	
//	Pediatria Urgencia CGI  -- Espera Ocupada
	public int consultaPorTipo(String tipo) {
		int idConsulta = -1;
		for(int i = 0; i < consultas.size(); i++) {
			if(consultas.get(i).getTipoConsulta().equals(tipo) && consultas.get(i).getEstado().equals("Espera")) {
				idConsulta = i;
				break;
			}
		}
		return idConsulta;
	}
	
	public String consultaDisponible() {
		for(int i = 0; i < consultas.size(); i++) {
			if(consultas.get(i).getEstado().equals("Espera")) {
				return consultas.get(i).getTipoConsulta();
			}
		}
		return null;
	}
	
	public void liberarConsultas() {
		for(int i = 0; i < consultas.size(); i++) {
			if(consultas.get(i).getEstado().equals("Ocupada")) {
				consultas.get(i).setEstado("Espera");
				consultas.get(i).setPacienteAtendido(null);
			}
		}
		this.atenderPacientes();
	}
	
	public void atenderPacientes() {
		boolean atendioAlguien = false;
		for(int i = 0; i < consultas.size(); i++) {
			if(consultas.get(i).getEstado().equals("Espera")) {
				Paciente pacienteAtender = buscarPaciente(consultas.get(i).getTipoConsulta());
				if (pacienteAtender != null) {
					consultas.get(i).setCantPacientes(consultas.get(i).getCantPacientes()+1);
					consultas.get(i).setEstado("Ocupada");
					consultas.get(i).setPacienteAtendido(pacienteAtender);
					atendioAlguien = true;
				}
			}
		}
		if(!atendioAlguien) {//si no pudo atender a nadie, se pasa paciente prioritario a sala de espera.
			Paciente paciente = buscarPacientePrioritario();
			this.pacientesPendientes.remove(paciente);
			this.pacientesEnEspera.add(paciente);
		}
	}
	
	
	private Paciente buscarPacientePrioritario() {
		Paciente paciente = null;
		int prioridad = 0;
		for(Paciente prioritario : this.pacientesPendientes) {
			if(prioritario.getPrioridad() > prioridad) {
				paciente = prioritario;
				prioridad = paciente.getPrioridad();
			}
		}
		
		return paciente;
	}
	
	private Paciente buscarPaciente(String tipoConsulta) {
		Paciente pacienteAtender = null;
		int prioridadAlta = 0;
		if(pacientesEnEspera != null && !pacientesEnEspera.isEmpty()) {
			System.out.println("[atenderPacientes] hay pacientes en espera");
			for (Paciente prioritario : this.pacientesEnEspera) {
				if (prioritario != null && tipoConsulta.equals("Urgencia") && prioritario.getPrioridad() > 4 && prioritario.getPrioridad() > prioridadAlta) {//urgencia
					pacienteAtender = prioritario;
					prioridadAlta = prioritario.getPrioridad();
				}
				else if (prioritario != null && tipoConsulta.equals("Pediatria") && prioritario.getEdad() <= 15 && prioritario.getPrioridad() <=4 && pacienteAtender == null) {//pediatria
					pacienteAtender = prioritario;
				}
				else if (prioritario != null && prioritario.getEdad() > 15 && prioritario.getPrioridad() > prioridadAlta){//cgi
					pacienteAtender = prioritario;
					prioridadAlta = prioritario.getPrioridad();
				}
			}
			
		}
		if (pacienteAtender != null) {
			this.pacientesAtendidos.add(pacienteAtender);
			this.pacientesEnEspera.remove(pacienteAtender);
		}
		else if(this.pacientesPendientes!= null && !this.pacientesPendientes.isEmpty()){//busca paciente en lista pendiente
			for (Paciente prioritario : this.pacientesPendientes) {
				if (prioritario != null && tipoConsulta.equals("Urgencia") && prioritario.getPrioridad() > 4 && prioritario.getPrioridad() > prioridadAlta) {//urgencia
					pacienteAtender = prioritario;
					prioridadAlta = prioritario.getPrioridad();
				}
				else if (prioritario != null && tipoConsulta.equals("Pediatria") && prioritario.getEdad() <= 15 && prioritario.getPrioridad() <=4 && pacienteAtender == null) {//pediatria
					pacienteAtender = prioritario;
				}
				else if (prioritario != null && prioritario.getEdad() > 15 && prioritario.getPrioridad() > prioridadAlta){//cgi
					pacienteAtender = prioritario;
					prioridadAlta = prioritario.getPrioridad();
				}
			}
			if (pacienteAtender != null) {
				this.pacientesAtendidos.add(pacienteAtender);
				this.pacientesPendientes.remove(pacienteAtender);
			}
		}
		
		return pacienteAtender;
	}
	
	public ArrayList<Paciente> obtenerFumadores() {
		ArrayList<Paciente> fumadores = new ArrayList<>();
		for(Paciente iterado : todos) {
			if(iterado instanceof PacienteJoven) {
				PacienteJoven joven = (PacienteJoven)iterado;
				if(joven.isFumador() && joven.getPrioridad() > 4) {
					fumadores.add(joven);
				}
			}
		}
		return fumadores;
	}
	
	public void optimizarAtencion() {
		ArrayList<Paciente> pacientesOptimizados = this.pacientesPendientes;
		Collections.sort(pacientesOptimizados, new Comparator<Paciente>() {
		    @Override
		    public int compare(Paciente z1, Paciente z2) {
		        if (z1.getPrioridad() > z2.getPrioridad())
		            return -1;
		        if (z1.getPrioridad() < z2.getPrioridad())
		            return 1;
		        return 0;
		    }
		});
		ArrayList<Paciente> prioridad1 = new ArrayList<>();
		ArrayList<Paciente> prioridad2 = new ArrayList<>();
		for (Paciente iterado : pacientesOptimizados) {
			if(iterado.getPrioridad()>4) {
				prioridad1.add(iterado);
			}
			else {
				prioridad2.add(iterado);
			}
		}
		Collections.sort(prioridad2, new Comparator<Paciente>() {
		    @Override
		    public int compare(Paciente z1, Paciente z2) {
		        if (z1 instanceof PacienteNino)
		            return -1;
		        if (z1 instanceof PacienteAnciano)
		            return 1;
		        if (z1 instanceof PacienteJoven)
		            return 2;
		        return 0;
		    }
		});
		for (Paciente iterado : prioridad2) {
			if(iterado.getPrioridad()<=4) {
				prioridad1.add(iterado);
			}
		}
		this.pacientesPendientes = prioridad1;
		
	}
	
}
