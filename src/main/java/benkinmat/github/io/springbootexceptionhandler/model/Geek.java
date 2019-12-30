package benkinmat.github.io.springbootexceptionhandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "geek")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Geek {
    @Id
	@GeneratedValue(generator = "uuid")
	@Column(unique = true)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String firstName;

	private String lastName;

	private String phone;

	private String email;

	private String userId;
	
	@OneToOne(mappedBy = "geek")
	private Contract contract;
	
}