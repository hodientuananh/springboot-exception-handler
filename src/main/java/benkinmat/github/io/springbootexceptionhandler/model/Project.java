package benkinmat.github.io.springbootexceptionhandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "project")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Project {

	@Id
	@GeneratedValue(generator = "uuid")
	@Column(unique = true)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String title;
	
	private String description;
	
	private String programmingLanguage;
	
	@ManyToOne
	@JoinColumn(name = "work_space_id")
	private WorkSpace workspace;
	
}
