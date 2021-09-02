package life.majiang.community2.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer view_count;
    private Integer like_count;
    private Integer comment_count;
}
