package benkinmat.github.io.springbootexceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import benkinmat.github.io.springbootexceptionhandler.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

}
