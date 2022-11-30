package ufpb.clinica.crm.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ufpb.clinica.crm.entities.Cliente;
import ufpb.clinica.crm.entities.enums.Status;
import ufpb.clinica.crm.repositories.ClienteRepository;
import ufpb.clinica.exceptions.ResourceNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	
	public Cliente cadastraLead(Cliente cliente) {
		cliente.setStatus(Status.LEAD);
		return clienteRepository.save(cliente);
	}
	
	public Collection<Cliente> RecuperaClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente encontraClienteId(Long id) {
		Optional<Cliente> obj =  clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public Cliente atualizaCadastroCliente(Long id, Cliente cliente) {
		Cliente atualizaCliente = encontraClienteId(id);
		
		String atualizaNome = cliente.getNome();
		String atualizaDataNascimento = cliente.getDataNascimento();
		String atualizaEndereco = cliente.getEndereco();
		String atualizaProfisao = cliente.getProfissao();
		
		atualizaCliente.setNome(atualizaNome);
		atualizaCliente.setDataNascimento(atualizaDataNascimento);
		atualizaCliente.setEndereco(atualizaEndereco);
		atualizaCliente.setProfissao(atualizaProfisao);
		
		return clienteRepository.save(atualizaCliente);
	}
	
	public Cliente atualizaEstagioAgendaMarcada(Long id) {
		Cliente cliente = encontraClienteId(id);
		cliente.setStatus(Status.AGENDA_MARCADA);
		return cliente;
	}
	
	public Cliente atualizaEstagioAgendaRealizada(Long id) {
		Cliente cliente = encontraClienteId(id);
		cliente.setStatus(Status.AGENDA_REALIZADA);
		return cliente;
	}
	
	public Cliente atualizaEstagioOrcamentoRealizado(Long id) {
		Cliente cliente = encontraClienteId(id);
		cliente.setStatus(Status.ORÇAMENTO_REALIZADO);
		return cliente;
	}
	
	public Cliente atualizaEstagioOrcamentoFechado(Long id) {
		Cliente cliente = encontraClienteId(id);
		cliente.setStatus(Status.ORÇAMENTO_FECHADO);
		return cliente;
	}
	
	
	
	
}
