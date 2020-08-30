package co.musinsa.log;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Setter;

@Entity
@Table(name="test_logs")
@Setter
public class Logs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String originUrl;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
}
