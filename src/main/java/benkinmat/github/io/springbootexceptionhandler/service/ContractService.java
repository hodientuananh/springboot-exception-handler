package benkinmat.github.io.springbootexceptionhandler.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import benkinmat.github.io.springbootexceptionhandler.dto.model.ContractDto;

public interface ContractService {

	public ContractDto createNewContract(ContractDto contractDto);
	
	public List<ContractDto> getAllContracts(Pageable pageable);
	
	public ContractDto getContractById(String id);
	
}
