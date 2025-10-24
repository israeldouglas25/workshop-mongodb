package apiworkshopmongodb.com.br.workshop.domain;

import apiworkshopmongodb.com.br.workshop.domain.dto.UserPostResponseDTO;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDate date;
    private String title;
    private String body;
    private UserPostResponseDTO author;
}
