package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseModel {

    private String userID;
    private String username;
    private List<BookResponseModel> Books;

}
