package corp.cencosud.test.rest;

import java.util.ArrayList;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import corp.cencosud.test.models.Consulta;
import corp.cencosud.test.models.Paciente;
import corp.cencosud.test.service.Hospital;
import corp.cencosud.test.service.PacienteService;


/**
 * Clase controladora de la aplicacion
 * @author caracena
 *
 */
@Controller
@RequestMapping("/paciente")
public class HospitalController {

	
	/**
	 * Instancia de clase de servicio.
	 */
	@Autowired
	private PacienteService service;
	
	/**
	 * Clase encargada de guardar la informacion de sesion del hospital.
	 */
	@Autowired
	private Hospital datosHospital;
	
    @Autowired
    public HospitalController(Environment environment){
    	
    }
 
    /**
     * Muestra los pacientes con mayor riesgo, del total de pacientes.
     * @param numClinico
     * @param model
     * @return
     */
    @PostMapping(value = "/riesgo/buscar")
    public String buscarpacienteRiesgo(@ModelAttribute("numClinico") String numClinico, Model model){
    	System.out.println("busca por ficha "+ numClinico);
    	String mensaje = null;
    	ArrayList<Paciente> pacientes = null;
    	int numFicha = 0;
    	try {
    		numFicha = Integer.parseInt(numClinico);
    	}
    	catch(Exception e) {
    		System.out.println("debe ser numerico");
    		mensaje = "Ficha debe ser numerica";
    	}
    	if (numFicha > 0) {
    		pacientes = service.obtenerPacientesMayorRiesgo(datosHospital.getTodos(), numFicha);
    	}
    	else if (mensaje == null) {
    		System.out.println("debe ser mayor que 0");
    		mensaje = "Numero de ficha debe ser mayor que cero";
    	}
        model.addAttribute("numFicha", "");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("pacientesRiesgo",pacientes);
        return "riesgo";
    }
    
    /**
     * Deriba a la vista de busqueda de pacientes con mayor riesgo.
     * @param model
     * @return
     */
    @GetMapping(value = "/riesgo")
    public String pacienteRiesgo(Model model){
        model.addAttribute("numFicha", "");
        model.addAttribute("mensaje", null);
        model.addAttribute("pacientesRiesgo",null);
        return "riesgo";
    }
    
    /**
     * Busca el paciente mas mayor de la sala de espera.
     * @param model
     * @return
     */
    @GetMapping(value = "/viejo")
    public String pacienteViejito(Model model) {
    	Paciente viejito = service.obtenerPacienteMasAnciano(datosHospital.getPacientesEnEspera());
    	model.addAttribute("pacienteMayor",viejito);
    	return "older";
    }
    
    /**
     * Deriva a la vista de atencion del hospital.
     * @param model
     * @return
     */
    @GetMapping(value = "/atencion")
    public String atencionDePacientes(Model model){
    	ArrayList<Paciente> pacientes = datosHospital.getTodos();
    	model.addAttribute("consultas",datosHospital.getConsultas());
    	System.out.println("consultas son " + datosHospital.getConsultas().size());
    	
    	model.addAttribute("pacientesEspera",datosHospital.getPacientesEnEspera());
    	model.addAttribute("pacientesPendientes",datosHospital.getPacientesPendientes());
        model.addAttribute("pacientes", pacientes);
        return "atencion";
    }
    
    /**
     * Genera la atencion de los pacientes o deriva a sala de espera segun sea el caso.
     * @param model
     * @return
     */
    @GetMapping(value = "/atender")
    public String atenderPaciente(Model model){
    	datosHospital.atenderPacientes();
    	model.addAttribute("consultas",datosHospital.getConsultas());
    	System.out.println("consultas son " + datosHospital.getConsultas().size());
    	
    	model.addAttribute("pacientesEspera",datosHospital.getPacientesEnEspera());
    	model.addAttribute("pacientesPendientes",datosHospital.getPacientesPendientes());
        return "atencion";
    }
    
    /**
     * Libera las consultas, y vuelve a atender los pacientes prioritarios.
     * @param model
     * @return
     */
    @GetMapping(value = "/liberar")
    public String liberarConsultas(Model model) {
    	datosHospital.liberarConsultas();
    	model.addAttribute("consultas",datosHospital.getConsultas());
    	System.out.println("consultas son " + datosHospital.getConsultas().size());
    	
    	model.addAttribute("pacientesEspera",datosHospital.getPacientesEnEspera());
    	model.addAttribute("pacientesPendientes",datosHospital.getPacientesPendientes());
        return "atencion";
    }
    
    /**
     * Despliega las consultas con mayor cantidad de pacientes atendidos.
     * @param model
     * @return
     */
    @GetMapping(value="/consulta")
    public String consultaMayorAtencion(Model model) {
    	ArrayList<Consulta> consultas = service.obtenerConsultasMayorAtencion(datosHospital.getConsultas());
    	model.addAttribute("consultas",consultas);
    	return "mayor-atencion";
    }
    
    /**
     * Obtiene pacientes fumadores de urgencia.
     * @param model
     * @return
     */
    @GetMapping(value="/fumadores")
    public String obtieneFumadoresUrgencia(Model model) {
    	model.addAttribute("fumadores",datosHospital.obtenerFumadores());
    	return "fumadores";
    }
    
    @GetMapping(value="/optimizar")
    public String optimizarPacientes(Model model) {
    	datosHospital.optimizarAtencion();
    	model.addAttribute("consultas",datosHospital.getConsultas());
    	model.addAttribute("pacientesEspera",datosHospital.getPacientesEnEspera());
    	model.addAttribute("pacientesPendientes",datosHospital.getPacientesPendientes());
        return "atencion";
    }
}
