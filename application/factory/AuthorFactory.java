package application.factory;
import application.classes.Author;

import java.util.ArrayList;

public class AuthorFactory {
    public static ArrayList<Author> getNewAuthor(ArrayList<Author> list) {
        Author createAuthor = new Author();
        list.add(createAuthor.createNewAuthor()); //Add the method "createNewAuthor" from the "Author" class ->
        return list;                              //-> into the ArrayList<Author>
    }
}