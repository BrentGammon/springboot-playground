package uk.co.brentgammon.webservice.src.api.User;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(Long id,String name, String email) {
        this(name, email);
        this.id = id;
    }
}
