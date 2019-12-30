package benkinmat.github.io.springbootexceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import benkinmat.github.io.springbootexceptionhandler.model.Geek;

@Repository
public interface GeekRepository extends JpaRepository<Geek, String>{
	
	Geek findByEmail(String email);
	
}
