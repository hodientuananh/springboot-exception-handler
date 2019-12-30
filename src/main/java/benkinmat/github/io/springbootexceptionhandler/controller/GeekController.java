package benkinmat.github.io.springbootexceptionhandler.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.dto.request.GeekRegistrationRequest;
import benkinmat.github.io.springbootexceptionhandler.service.GeekService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/geeks")
@Validated
public class GeekController {

	@Autowired
	GeekService geekService;

	@GetMapping
	public ResponseEntity<List<GeekDto>> getGeeks() {
		log.info("Start call api getGeeks");

		return ResponseEntity.ok().body(geekService.getAllGeek());
	}

	@PostMapping
	public ResponseEntity<?> addGeek(@Valid @RequestBody GeekRegistrationRequest geekRegistrationRequest) {
		log.info("Start call api addGeek");
		GeekDto geekDto = new GeekDto().setFirstName(geekRegistrationRequest.getFirstName())
				.setLastName(geekRegistrationRequest.getLastName()).setEmail(geekRegistrationRequest.getEmail())
				.setPhone(geekRegistrationRequest.getPhone()).setUserId(geekRegistrationRequest.getUserId());
		GeekDto geekDtoResult = geekService.register(geekDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(geekDtoResult.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<GeekDto> getGeek(@NotNull @PathVariable String id) {
		log.info("Start call api getGeek");
		GeekDto geekDto = geekService.getGeekById(id);

		return ResponseEntity.ok().body(geekDto);
	}

}
