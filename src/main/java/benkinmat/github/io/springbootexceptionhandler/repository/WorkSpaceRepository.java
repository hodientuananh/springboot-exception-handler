package benkinmat.github.io.springbootexceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import benkinmat.github.io.springbootexceptionhandler.model.WorkSpace;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, String>{

	WorkSpace findByCode(String code);
	
}
