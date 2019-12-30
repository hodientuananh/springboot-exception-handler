package benkinmat.github.io.springbootexceptionhandler.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "contract")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Contract {
    @Id
	@GeneratedValue(generator = "uuid")
	@Column(unique = true)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geek_id")
	private Geek geek;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "workspace_id")
	private WorkSpace workspace;

	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date lastModifiedDate;
}
