package benkinmat.github.io.springbootexceptionhandler.dto.mapper;

import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;
import benkinmat.github.io.springbootexceptionhandler.model.Geek;

@Component
public class GeekMapper {
	
	public GeekDto toGeekDto(Geek geek) {
		return new GeekDto()
				.setFirstName(geek.getFirstName())
				.setLastName(geek.getLastName())
				.setEmail(geek.getEmail())
				.setUserId(geek.getUserId())
				.setPhone(geek.getPhone());
	}
}
