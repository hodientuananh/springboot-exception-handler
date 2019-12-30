package benkinmat.github.io.springbootexceptionhandler.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.dto.model.ContractDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.WorkSpaceDto;
import benkinmat.github.io.springbootexceptionhandler.error.contract.ContractRegistrationException;
import benkinmat.github.io.springbootexceptionhandler.model.Contract;
import benkinmat.github.io.springbootexceptionhandler.model.Geek;
import benkinmat.github.io.springbootexceptionhandler.model.WorkSpace;
import benkinmat.github.io.springbootexceptionhandler.repository.ContractRepository;
import benkinmat.github.io.springbootexceptionhandler.repository.GeekRepository;
import benkinmat.github.io.springbootexceptionhandler.repository.ProjectRepository;
import benkinmat.github.io.springbootexceptionhandler.repository.WorkSpaceRepository;

@Component
public class ContractServiceImpl implements ContractService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	GeekRepository geekRepository;

	@Autowired
	WorkSpaceRepository workspaceRepository;

	@Autowired
	ContractRepository contractRepository;

	@Override
	public ContractDto createNewContract(ContractDto contractDto) {
		GeekDto geekDto = contractDto.getGeek();
		
		Optional<Geek> geek = Optional.ofNullable(geekRepository.findByEmail(geekDto.getEmail()));

		if (geek.isPresent()) {
			Contract contractModel = new Contract()
					.setStatus(contractDto.getStatus());
			
			Geek geekModel = new Geek()
					.setFirstName(geekDto.getFirstName())
					.setLastName(geekDto.getLastName())
					.setPhone(geekDto.getPhone())
					.setEmail(geekDto.getEmail())
					.setUserId(geekDto.getUserId());

			contractModel.setGeek(geekModel);
			geekModel.setContract(contractModel);

			if (contractDto.getWorkspace() != null) {
				WorkSpaceDto workspaceDto = contractDto.getWorkspace();
				
				WorkSpace workspaceModel = new WorkSpace()
						.setCode(workspaceDto.getCode())
						.setIde(workspaceDto.getIde())
						.setOs(workspaceDto.getOs());
			}
		}
		throw new ContractRegistrationException("geek-email-not-exist", new Object[] { geekDto.getEmail() });

	}

	@Override
	public List<ContractDto> getAllContracts(Pageable pageable) {
		return contractRepository.findAll(pageable).getContent().parallelStream()
				.map(contract -> modelMapper.map(contract, ContractDto.class)).collect(toList());
	}

	@Override
	public ContractDto getContractById(String id) {
		Optional<Contract> contract = contractRepository.findById(id);

		if (contract.isPresent()) {
			return modelMapper.map(contract.get(), ContractDto.class);
		}
		throw new ContractRegistrationException("contract-id-not-exist", new Object[] { id });
	}

}
