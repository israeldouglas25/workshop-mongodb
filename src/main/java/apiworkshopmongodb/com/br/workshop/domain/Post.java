package apiworkshopmongodb.com.br.workshop.domain;

import apiworkshopmongodb.com.br.workshop.domain.dto.AuthorDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
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
    private AuthorDTO author;

    @DBRef(lazy = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(String id, LocalDate date, String title, String body, AuthorDTO author) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
    }
}
