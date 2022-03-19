package dev.jt.community.model;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;

    public PostDto() {
    }

    public PostDto(
            Long id,
            String title,
            String content,
            Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long boardId) {
        this.userId = boardId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardId=" + userId +
                '}';
    }
}

