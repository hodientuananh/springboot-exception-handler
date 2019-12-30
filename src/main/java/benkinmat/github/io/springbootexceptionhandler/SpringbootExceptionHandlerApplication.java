package benkinmat.github.io.springbootexceptionhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootExceptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExceptionHandlerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(GeekRepository geekRepository, ProjectRepository projectRepository,
//			WorkSpaceRepository workspaceRepository, ContractRepository contractRepository, ModelMapper modelMapper) {
//		return args -> {
//			Set<Project> projects = new HashSet<>();
//			WorkSpace workspace = new WorkSpace();
//			Geek geek = new Geek();
//			Contract contract = new Contract();
//					
//			projects.add(new Project()
//					.setTitle("Study")
//					.setDescription("esis")
//					.setProgrammingLanguage("Java")
//					.setWorkspace(workspace));
//			
//			projects.add(new Project()
//					.setTitle("Work")
//					.setDescription("esis")
//					.setProgrammingLanguage("Java")
//					.setWorkspace(workspace));
//			
//			workspace.setIde("Eclipse")
//					.setOs("Windows")
//					.setProjects(projects);
//			
//			geek.setFirstName("test")
//					.setLastName("test")
//					.setEmail("test")
//					.setPhone("test")
//					.setUserId("test");
//			
//			contract.setStatus("test")
//					.setGeek(geek)
//					.setWorkspace(workspace);
//			
//			Contract result = contractRepository.save(contract);
//			System.out.println(modelMapper.map(result, ContractDto.class));
//		};
//	}

}
