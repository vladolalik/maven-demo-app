package app.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id = null;

    private String title = null;

    private String author = null;
  
    private Integer published = null;
}