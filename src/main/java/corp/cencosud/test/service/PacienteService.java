package corp.cencosud.test.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import corp.cencosud.test.models.Consulta;
import corp.cencosud.test.models.Paciente;
import corp.cencosud.test.models.PacienteAnciano;
import corp.cencosud.test.models.PacienteJoven;
import corp.cencosud.test.models.PacienteNino;

@Service
public class PacienteService {
	
	
    public JSONArray parseJSON() {
        JSONParser jsonParser = new JSONParser();
        
        try {
        	File file = ResourceUtils.getFile("classpath:static/pacientes.json");
        	FileReader reader = new FileReader(file);
            Object obj = jsonParser.parse(reader);
 
            JSONArray pacientes = (JSONArray) obj;
            System.out.println("antes de retornar pacientes " + pacientes);
            return pacientes;
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public ArrayList<Paciente> obtenerPacientes(){
		JSONArray pacientes = this.parseJSON();
		ArrayList<Paciente> pacientesDefinidos = new ArrayList<>();
		if (pacientes != null && !pacientes.isEmpty()) {
			
			for(int i = 0; i < pacientes.size(); i++) {
				JSONObject paciente = (JSONObject) pacientes.get(i);
				int edadPaciente = Integer.parseInt(paciente.get("edad").toString());
				Paciente pacienteNuevo = null;
				
				if (edadPaciente <= 15 ) {
					//niño
					PacienteNino pacienteNino = new PacienteNino();
					pacienteNino.setEdad(edadPaciente);
					pacienteNino.setRelacionPesoEstatura(Integer.parseInt(paciente.get("relacionPesoEstatura").toString()));
					pacienteNino = (PacienteNino)priorizarPacientes(pacienteNino);
					pacienteNuevo = pacienteNino;
				}
				else if (edadPaciente > 15 && edadPaciente < 41) {
					//lolo
					PacienteJoven pacienteJoven = new PacienteJoven();
					pacienteJoven.setEdad(edadPaciente);
					pacienteJoven.setAniosFumador(Integer.parseInt(paciente.get("aniosFumador").toString()));
					pacienteJoven.setFumador(pacienteJoven.getAniosFumador()>0);
					pacienteJoven = (PacienteJoven)priorizarPacientes(pacienteJoven);
					pacienteNuevo = pacienteJoven;
				}
				else {
					//viejo
					PacienteAnciano pacienteAnciano= new PacienteAnciano();
					pacienteAnciano.setEdad(edadPaciente);
					pacienteAnciano.setTieneDieta(paciente.get("tieneDieta").toString().equals("0")?false:true);
					pacienteAnciano = (PacienteAnciano)priorizarPacientes(pacienteAnciano);
					pacienteNuevo = pacienteAnciano;
				}
				
				pacienteNuevo.setNoHistorialClinico(Integer.parseInt(paciente.get("noHistoriaClinica").toString()));
				pacienteNuevo.setNombre(paciente.get("nombre").toString());
				pacientesDefinidos.add(pacienteNuevo);
			}
		}
		return pacientesDefinidos;
		
	}
	
	public Paciente obtenerPacienteMasAnciano(ArrayList<Paciente> pacientes) {
		if(pacientes != null && !pacientes.isEmpty()) {
			Paciente anciano = pacientes.get(0);
			for (Paciente revisando : pacientes) {
				if(anciano != null && anciano.getEdad() < revisando.getEdad()) {
					anciano = revisando;
				}
			}
			return anciano;
		}
		else {
			return null;
		}
	}
	
	public ArrayList<Paciente> obtenerPacientesMayorRiesgo(ArrayList<Paciente> pacientes, int fichaPaciente){
		System.out.println("buscando pacientes de mayor riesgo" + pacientes.size());
		ArrayList<Paciente> pacientesMasPrioritarios = new ArrayList<>();
		for ( Paciente paciente : pacientes) {
			System.out.println("buscado " + fichaPaciente + " pacienteiterado "+ paciente.getNoHistorialClinico());
			if (paciente.getNoHistorialClinico() == fichaPaciente) {
				for(Paciente comparacion : pacientes) {
					if(comparacion.getRiesgo() > paciente.getRiesgo()) {
						pacientesMasPrioritarios.add(comparacion);
					}
				}
				break;
			}
		}
		System.out.println("pacientes prioritarios son " + pacientesMasPrioritarios.size());
		return pacientesMasPrioritarios;
	}
	
	public Paciente priorizarPacientes(Paciente paciente){
		
		if (paciente != null) {
			if(paciente instanceof PacienteNino) {
				PacienteNino nino = (PacienteNino)paciente;
				if(nino.getEdad()<=5) {
					nino.setPrioridad(nino.getRelacionPesoEstatura()+3);
				}
				else if (nino.getEdad()>5 && nino.getEdad() <=12) {
					nino.setPrioridad(nino.getRelacionPesoEstatura()+2);
				}
				else if (nino.getEdad() > 12 && nino.getEdad() <=15) {
					nino.setPrioridad(nino.getRelacionPesoEstatura()+1);
				}
				nino.setRiesgo((nino.getEdad() * nino.getPrioridad())/100);
				return nino;
			}
			else if (paciente instanceof PacienteJoven) {
				PacienteJoven joven = (PacienteJoven)paciente;
				if(joven.isFumador()) {
					joven.setPrioridad((joven.getAniosFumador()/4)+2);
				}
				else {
					joven.setPrioridad(2);
				}
				joven.setRiesgo((joven.getEdad() * joven.getPrioridad())/100);
				return joven;
			}
			else if (paciente instanceof PacienteAnciano) {
				PacienteAnciano tatita = (PacienteAnciano)paciente;
				if(tatita.isTieneDieta() && tatita.getEdad()>=60 && tatita.getEdad() <=100) {
					tatita.setPrioridad((tatita.getEdad()/20)+4);
				}
				else {
					tatita.setPrioridad((tatita.getEdad()/30)+3);
				}
				double preriesgo = tatita.getEdad() * tatita.getPrioridad();
				double preriesgo2 = preriesgo / 100;
				tatita.setRiesgo( preriesgo2 + 5.3);
				return tatita;
			}
			else{
				System.out.println("fuera de rango");
			}
		}
		
		return null;
	}

	public ArrayList<Consulta> obtenerConsultasMayorAtencion(ArrayList<Consulta> consultas) {
		ArrayList<Consulta> consultasAtencion = new ArrayList<>();
		int pacientesAtendidos = 0;
		for(Consulta revisando : consultas) {
			if(pacientesAtendidos < revisando.getCantPacientes()) {
				pacientesAtendidos = revisando.getCantPacientes();
			}
		}
		for(Consulta revisando : consultas) {
			if(revisando.getCantPacientes() == pacientesAtendidos) {
				consultasAtencion.add(revisando);
			}
		}
		return consultasAtencion;
	}
}
