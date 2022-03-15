package application.factory;

import application.classes.Book;

import java.util.ArrayList;

public class BookFactory {
    public static ArrayList<Book> getNewBook(ArrayList<Book> list) {
        Book createBook = new Book();
        list.add(createBook.createNewBook()); //Add the method "createNewBook" from the "Book" class ->
        return list;                          // -> into tje ArrayList<Book>
    }
}