package application.classes;

import application.factory.IAuthorFactory;
import application.functions.AddFunction;

public class Author implements IAuthorFactory {
    private String firstName;
    private String lastName;
    private String city;
    private String listOfBooks;
    private int yearBirth;

    public Author(String firstName,String lastName, String city, String listOfBooks, int yearBirth) {
        this.listOfBooks = listOfBooks;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.yearBirth = yearBirth;
    }

    public Author() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBooks(String listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getListOfBooks() {
        return listOfBooks;
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", list of books='" + listOfBooks + '\'' +
                ", yearBirth=" + yearBirth +
                '}';
    }

    //Create a new author
    @Override
    public Author createNewAuthor() {
        System.out.println("Firstname: ");                          //Fill in the information fields about an author
        String tempFirstname = AddFunction.checkOneWordStringInput();
        System.out.println("Lastname: ");
        String tempLastname = AddFunction.checkOneWordStringInput();
        System.out.println("City: ");
        String tempCity = AddFunction.checkStringInput();        //Concatenate the strings "firstname" and "lastname"->
        String tempListOfBooks = "";                             //-> compare with all author's names  ->
        System.out.println("Year of birth: ");                   //-> add the book's name to the author's list of books
        int tempYearBirth = AddFunction.checkIntInput();

        for (int i = 0; i < AddFunction.listOfBooks.size(); i++) {
            if (((AddFunction.listOfBooks.get(i).getAuthor())
                    .equalsIgnoreCase(tempFirstname + " " + tempLastname))) {
                tempListOfBooks =   "; " + (AddFunction.listOfBooks.get(i).getTitle());
            }
        }
        return new Author(tempFirstname, tempLastname, tempCity, tempListOfBooks, tempYearBirth);
    }
}