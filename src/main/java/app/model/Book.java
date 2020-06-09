package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "BOOKS")
public class Book {

  @Id
  @GeneratedValue
  @Column(name = "id")
	private Long id;

  @JsonProperty("title")
  private String title = null;

  private String author = null;

  private Integer published = null;
}

