package apiworkshopmongodb.com.br.workshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private String text;
    private LocalDate date;
    private AuthorDTO idAuthor;
}
