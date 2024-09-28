package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseModel {

    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publishDate;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;

}
