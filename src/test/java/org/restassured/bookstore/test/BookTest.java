package org.restassured.bookstore.test;

import client.BookClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.response.BookResponseModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestListener;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static story.BookStory.*;

@Epic(EPIC_BOOK)
@Story(USER_STORY_BOOK_GET)
@DisplayName("Endpoint de Listar todos os livros")
@ExtendWith(TestListener.class)
public class BookTest {

    private static BookClient bookClient = new BookClient();

    @Test
    @Description(CT_BOOK_000)
    @Tag("Contract")
    public void testValidarContratoListBooks() {
        bookClient.getAllBooks()
                .then()
                    .body(matchesJsonSchemaInClasspath("schemas/book-get.json"))
                ;
    }

    @Test
    @Tag("Regression")
    @Tag("Functional")
    @Description(CT_BOOK_001)
    public void testValidarListarTodosLivrosComSucesso() {

        List<BookResponseModel> bookList = bookClient.getAllBooks()
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("books")
                ;

        Assertions.assertFalse(bookList.isEmpty());
    }

}
