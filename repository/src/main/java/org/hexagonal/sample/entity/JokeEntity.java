package org.hexagonal.sample.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hexagonal.sample.domain.Joke;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@Builder
@ToString
public class JokeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String text;
    @Column(nullable = false)
    @NonNull
    private String author;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column
    @ColumnDefault("'UNKNOWN'")
    private Joke.Type type = Joke.Type.UNKNOWN;

    @Setter
    @ColumnDefault("false")
    private boolean funny;

    public static JokeEntityBuilder builder(String text, String author) {
        return new JokeEntityBuilder().text(text).author(author);
    }
}
