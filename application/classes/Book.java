package application.classes;

import application.factory.IBookFactory;
import application.functions.AddFunction;

public class Book implements IBookFactory {
    private String title;
    private String author;
    private String description;
    private int yearWriting;
    private int bookID;

    public Book(String title,String author, String description, int yearWriting, int bookID) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.yearWriting = yearWriting;
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYearWriting(int yearWriting) {
        this.yearWriting = yearWriting;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearWriting() {
        return yearWriting;
    }

    public int getBookID() {
        return bookID;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", yearWriting=" + yearWriting +
                ", bookID=" + bookID +
                '}';
    }

    //Method to create a new book
    @Override
    public Book createNewBook() {
        System.out.println("Title: ");                              //Fill in the information fields about an author
        String tempTitle = AddFunction.checkStringInput();
        System.out.println("The \"Author\" field must me only two word.");
        System.out.println("Author: ");
        String tempAuthor = AddFunction.checkTwoWordsStringInput();
        System.out.println("Description of book: ");
        String tempDescription = AddFunction.checkStringInput()
                .replace(",","")
                .replace("'","\"")
                .replace("&","");
        System.out.println("Year of writing: ");                 //Concatenate the strings "firstname" and "lastname"->
        int tempYear = AddFunction.checkIntInput();              //-> compare with entered "tempAuthor" ->
        int tempID = AddFunction.listOfBooks.size() + 1;         //-> add the book's name to the author's list of books
        for (int i = 0; i < AddFunction.listOfAuthors.size(); i++) {
            if ((((AddFunction.listOfAuthors.get(i)).getFirstName()) + " " + ((AddFunction.listOfAuthors.get(i))
                    .getLastName())).equalsIgnoreCase(tempAuthor)) {
                AddFunction.listOfAuthors.get(i)
                        .setBooks(AddFunction.listOfAuthors.get(i).getListOfBooks() + "; "+ tempTitle);
            }
        }
        return new Book(tempTitle, tempAuthor, tempDescription, tempYear, tempID);
    }
}