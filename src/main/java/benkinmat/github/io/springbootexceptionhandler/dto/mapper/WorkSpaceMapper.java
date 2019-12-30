package benkinmat.github.io.springbootexceptionhandler.dto.mapper;

import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.dto.model.WorkSpaceDto;
import benkinmat.github.io.springbootexceptionhandler.model.WorkSpace;

@Component
public class WorkSpaceMapper {

	public WorkSpaceDto toWorkSpaceDto(WorkSpace workspace) {
		return new WorkSpaceDto()
				.setIde(workspace.getIde())
				.setOs(workspace.getOs())
				.setCode(workspace.getCode());
	}
	
}
