package Musician.Portfolio.Musician.Portfolio.domain.entities.photo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    private String name;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUploaded;

    private String filePath;
}
