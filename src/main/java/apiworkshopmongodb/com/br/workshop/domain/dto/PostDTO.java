package apiworkshopmongodb.com.br.workshop.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private String title;
    private String body;
    private String idAuthor;
}
