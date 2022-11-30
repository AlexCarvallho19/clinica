package ufpb.clinica.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufpb.clinica.crm.entities.Cliente;
import ufpb.clinica.crm.entities.Consulta;
import ufpb.clinica.crm.entities.enums.AgendaStatus;
import ufpb.clinica.crm.entities.enums.OrcamentoStatus;
import ufpb.clinica.crm.repositories.ConsultaRepository;
import ufpb.clinica.exceptions.ResourceNotFoundException;


@Service
public class ConsultaService {
	@Autowired
	private ConsultaRepository consultaRepository; 
	
	@Autowired
	private ClienteService clienteService;

	public Consulta agendaConsulta(Long id, Consulta consulta) {
		Cliente cliente = clienteService.encontraClienteId(id);
		consulta.setCliente(cliente);
		consulta.setAgendaStatus(AgendaStatus.MARCADO);
		
		cliente = clienteService.atualizaEstagioAgendaMarcada(id);
		
		return consultaRepository.save(consulta);
	}
	
	public Consulta encontraConsultaId(Long id) {
		Optional<Consulta> obj =  consultaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Consulta agendaRealizada(Long id, Cliente cliente) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		consulta.setAgendaStatus(AgendaStatus.REALIZADO);
		atualizaCliente = clienteService.atualizaCadastroCliente(atualizaCliente.getId(), cliente);
		atualizaCliente = clienteService.atualizaEstagioAgendaRealizada(atualizaCliente.getId());
		
		return consultaRepository.save(consulta);
	}
	
	
	public Consulta realizaOrcamento(Long id, Double valor) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		consulta.setValor(valor);
		
		List<Consulta> consultas = atualizaCliente.getConsultas();
		Double valorTotal = valorTotalCliente(consultas);
		
		
		
		consulta.setOrcamentoStatus(OrcamentoStatus.REALIZADO);
		atualizaCliente.setValorTotal(valorTotal);
		atualizaCliente = clienteService.atualizaEstagioOrcamentoRealizado(atualizaCliente.getId());
		
		return consultaRepository.save(consulta);
	}
	
	public Consulta fechaOrcamento(Long id, String dataPagamento) {
		Consulta consulta = encontraConsultaId(id);
		Cliente atualizaCliente = consulta.getCliente();
		
		consulta.setDataDePagamento(dataPagamento);
		consulta.setOrcamentoStatus(OrcamentoStatus.FECHADO);
		
		atualizaCliente = clienteService.atualizaEstagioOrcamentoFechado(atualizaCliente.getId());
		
		return consultaRepository.save(consulta);
	}
	
	public Double valorTotalCliente (List<Consulta> consultas) {
		Double valorTotal = 0.0;
		
		for (int i = 0; i < consultas.size(); i++) {
			valorTotal = valorTotal + consultas.get(i).getValor();
		}
		
		return valorTotal;
	}
}
