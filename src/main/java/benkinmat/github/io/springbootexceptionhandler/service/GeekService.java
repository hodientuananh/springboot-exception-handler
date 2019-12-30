package benkinmat.github.io.springbootexceptionhandler.service;

import java.util.List;

import benkinmat.github.io.springbootexceptionhandler.dto.model.GeekDto;

public interface GeekService {

	public GeekDto register(GeekDto geekDto);

	public GeekDto getGeekById(String id);

	public GeekDto getGeekByEmail(String email);

	public List<GeekDto> getAllGeek();

}
