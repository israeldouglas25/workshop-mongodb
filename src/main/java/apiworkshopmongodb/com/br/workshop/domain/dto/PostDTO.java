package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private String title;
    private String body;
    private String idAuthor;
}
