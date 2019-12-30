package benkinmat.github.io.springbootexceptionhandler.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "work_space")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class WorkSpace {

	@Id
	@GeneratedValue(generator = "uuid")
	@Column(unique = true)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String os;
	
	private String ide;
	
	private String code;
	
	@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Project> projects;
	
	@OneToOne(mappedBy = "workspace")
	private Contract contract;
	
}
