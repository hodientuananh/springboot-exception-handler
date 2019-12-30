package benkinmat.github.io.springbootexceptionhandler.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.error.contract.ContractRegistrationException;
import benkinmat.github.io.springbootexceptionhandler.model.Geek;
import benkinmat.github.io.springbootexceptionhandler.repository.GeekRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GeekServiceImpl implements GeekService {
	@Autowired
	GeekRepository geekRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public GeekDto register(GeekDto geekDto) {
		log.info("Begin call register");
		Optional<Geek> geek = Optional.ofNullable(geekRepository.findByEmail(geekDto.getEmail()));

		if (!geek.isPresent()) {
			Geek geekModel = new Geek().setFirstName(geekDto.getFirstName()).setLastName(geekDto.getLastName())
					.setPhone(geekDto.getPhone()).setEmail(geekDto.getEmail()).setUserId(geekDto.getUserId());

			return modelMapper.map(geekRepository.save(geekModel), GeekDto.class);
		}
		throw new ContractRegistrationException("geek-email-exist", new Object[] { geekDto.getEmail() });
	}

	public GeekDto getGeekByEmail(String email) {
		log.info("Begin call getGeekByEmail");
		Optional<Geek> geek = Optional.ofNullable(geekRepository.findByEmail(email));

		if (!geek.isPresent()) {
			return modelMapper.map(geek.get(), GeekDto.class);
		}
		throw new ContractRegistrationException("geek-email-not-exist", new Object[] { email });
	}

	public GeekDto getGeekById(String id) {
		log.info("Begin call getGeekById");
		Optional<Geek> geek = geekRepository.findById(id);

		if (geek.isPresent()) {
			return modelMapper.map(geek.get(), GeekDto.class);
		}
		throw new ContractRegistrationException("geek-id-not-exist", new Object[] { id });
	}

	public List<GeekDto> getAllGeek() {
		log.info("Begin call getAllGeek");
		return geekRepository.findAll().parallelStream().map(geek -> modelMapper.map(geek, GeekDto.class))
				.collect(toList());
	}
}
