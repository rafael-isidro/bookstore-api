package models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.response.BookResponseModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestModel {

    private String userID;
    private String userName;
    private String password;
    private List<BookResponseModel> Books;

}