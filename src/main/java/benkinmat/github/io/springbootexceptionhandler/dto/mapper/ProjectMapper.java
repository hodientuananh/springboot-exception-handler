package benkinmat.github.io.springbootexceptionhandler.dto.mapper;

import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.dto.model.ProjectDto;
import benkinmat.github.io.springbootexceptionhandler.model.Project;

@Component
public class ProjectMapper {
	
	public ProjectDto toProjectDto(Project project) {
		return new ProjectDto()
				.setTitle(project.getTitle())
				.setDescription(project.getDescription())
				.setProgrammingLanguage(project.getProgrammingLanguage());
	}
	
}
