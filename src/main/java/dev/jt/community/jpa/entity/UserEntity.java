package dev.jt.community.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "community_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPosts;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, List<PostEntity> writtenPosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.writtenPosts = writtenPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getWrittenPosts() {
        return writtenPosts;
    }

    public void setWrittenPosts(List<PostEntity> writtenPosts) {
        this.writtenPosts = writtenPosts;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", writtenPosts=" + writtenPosts +
                '}';
    }
}
