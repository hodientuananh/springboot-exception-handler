package benkinmat.github.io.springbootexceptionhandler.controller;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import benkinmat.github.io.springbootexceptionhandler.dto.mapper.ContractMapper;
import benkinmat.github.io.springbootexceptionhandler.dto.mapper.GeekMapper;
import benkinmat.github.io.springbootexceptionhandler.dto.mapper.ProjectMapper;
import benkinmat.github.io.springbootexceptionhandler.dto.model.ContractDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.ProjectDto;
import benkinmat.github.io.springbootexceptionhandler.dto.model.WorkSpaceDto;
import benkinmat.github.io.springbootexceptionhandler.dto.request.ContractRegistrationRequest;
import benkinmat.github.io.springbootexceptionhandler.dto.request.ProjectRequest;
import benkinmat.github.io.springbootexceptionhandler.service.ContractService;
import benkinmat.github.io.springbootexceptionhandler.service.GeekService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contracts")
@Validated
public class ContractController {
	@Autowired
	GeekService geekService;

	@Autowired
	ContractService contractService;

	@Autowired
	GeekMapper geekMapper;

	@Autowired
	ProjectMapper projectMapper;

	@Autowired
	ContractMapper contractMapper;

	@PostMapping
	public ResponseEntity<?> registration(@Valid @RequestBody ContractRegistrationRequest contractRegistrationRequest) {

		ContractDto contractDto = new ContractDto().setStatus(contractRegistrationRequest.getStatus());

		GeekDto geekDto = new GeekDto().setFirstName(contractRegistrationRequest.getFirstName())
				.setLastName(contractRegistrationRequest.getLastName()).setEmail(contractRegistrationRequest.getEmail())
				.setUserId(contractRegistrationRequest.getUserId());

		contractDto.setGeek(geekDto);

		if (contractRegistrationRequest.getWorkspace() != null) {
			WorkSpaceDto workSpaceDto = new WorkSpaceDto().setIde(contractRegistrationRequest.getWorkspace().getIde())
					.setOs(contractRegistrationRequest.getWorkspace().getOs())
					.setCode(contractRegistrationRequest.getWorkspace().getCode());

			contractDto.setWorkspace(workSpaceDto);

			if (contractRegistrationRequest.getWorkspace().getProjects() != null) {
				Set<ProjectDto> projectDto = new HashSet<>();

				for (ProjectRequest projectRequest : contractRegistrationRequest.getWorkspace().getProjects()) {
					projectDto.add(new ProjectDto().setTitle(projectRequest.getTitle())
							.setDescription(projectRequest.getDescription())
							.setProgrammingLanguage(projectRequest.getProgrammingLanguage()));
				}

				contractDto.getWorkspace().setProjects(projectDto);
			}
		}

//		Contract contract = contractRepository.save(entity)

		return ResponseEntity.ok().body(contractDto);
	}

	@GetMapping
	public ResponseEntity<List<ContractDto>> getContracts(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) throws FileNotFoundException {
		log.info("Start call api contract getContracts");

		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("createdDate").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("createdDate").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		return ResponseEntity.ok().body(contractService.getAllContracts(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContractDto> getContract(@NotNull @PathVariable String id) {
		log.info("Start call api contract getContract");
		ContractDto contractDto = contractService.getContractById(id);

		return ResponseEntity.ok().body(contractDto);
	}
}