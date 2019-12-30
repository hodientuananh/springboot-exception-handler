package benkinmat.github.io.springbootexceptionhandler.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.dto.model.ContractDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.WorkSpaceDto;
import benkinmat.github.io.springbootexceptionhandler.model.Contract;

@Component
public class ContractMapper {
	
	@Autowired
	ModelMapper modelMapper;
	
	public ContractDto toContractDto(Contract contract) {
		return new ContractDto()
				.setGeek(modelMapper.map(contract.getGeek(), GeekDto.class))
				.setWorkspace(modelMapper.map(contract.getWorkspace(), WorkSpaceDto.class));
	}
}
