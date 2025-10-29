package apiworkshopmongodb.com.br.workshop.domain;

import apiworkshopmongodb.com.br.workshop.domain.dto.AuthorDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String text;
    private LocalDate date;
    private AuthorDTO author;
}
