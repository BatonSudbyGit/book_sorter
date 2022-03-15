package application.functions;

import application.factory.AuthorFactory;
import application.factory.BookFactory;
import application.constants.Constants;
import application.classes.Author;
import application.classes.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AddFunction {
    public static ArrayList<Author> listOfAuthors = new ArrayList<>();
    public static ArrayList<Book> listOfBooks = new ArrayList<>();

    //Check input only numbers
    public static int checkIntInput() {
        try {
            Scanner intInput = new Scanner(System.in);
            return intInput.nextInt();
        }catch (InputMismatchException e){
            System.out.println("You entered incorrect symbols!\nPlease, enter only digits. Try again.");
            return checkIntInput();
        }
    }

    //Check that was inputted words and special symbols
    public static String checkStringInput() {
        Scanner stringInput = new Scanner(System.in);
        return stringInput.nextLine();
    }

    //Check that was inputted one word
    public static String checkOneWordStringInput() {
        Scanner stringInput = new Scanner(System.in);
        String regexString = stringInput.nextLine();
        if ((regexString.matches("[a-zA-Z]+")) || (regexString.matches("[а-яА-Я]+"))) {
        }else {
            System.out.println("You can enter oly one word(On English or Russian). Try again!");
            return checkStringInput();
        }
        return regexString;
    }

    //Method to check that has inputted two words
    public static String checkTwoWordsStringInput() {
        Scanner stringInput = new Scanner(System.in);
        String regexString = stringInput.nextLine();
        if (regexString.matches("[a-zA-Z]+\\s[a-zA-Z]+") || (regexString.matches("[а-яА-Я]+\\s[а-яА-Я]+"))){
        }else {
            System.out.println("You can enter only two words(On English or Russian). Try again!");
            return checkTwoWordsStringInput();
        }
        return regexString;
    }

    //Start read the information from the files
    public static void startApp() {
        System.out.println("This is a list of books: ");
        FileHandler.addIntoBookArrayListFromFile(listOfBooks);
        AddFunction.listOfBooks.sort(Comparator.comparing(Book::getBookID));
        listOfBooks.forEach(System.out::println);
        System.out.println("\nThis is a list of authors: ");
        FileHandler.addIntoAuthorArrayListFromFile(listOfAuthors);
        AddFunction.listOfAuthors.sort(Comparator.comparing(Author::getFirstName));
        listOfAuthors.forEach(System.out::println);
        mainMenu();
    }

    //Main menu to move to other menus
    public static void mainMenu() {
        System.out.println("\n" +
                           "This is a main menu of program. Enter one of number using your keyboard:\n" +
                           "1.Add a new book\n" +
                           "2.Add a new author\n" +
                           "3.Find a needs book\n" +
                           "4.Remove an object\n" +
                           "5.Sort the books\n" +
                           "6.Close the app and save the information into the file");
        switch (checkIntInput()) {
            case 1:
                booksCreator(listOfBooks);
                break;
            case 2:
                authorCreator(listOfAuthors);
                break;
            case 3:
                menuToFind();
                break;
            case 4:
                menuToRemove();
                break;
            case 5:
                sortBooks();
                break;
            case 6:
                System.out.println("Save the information");
                save(listOfAuthors, listOfBooks);
                break;
            default:
                System.out.println("You entered incorrect number. Try again!");
                mainMenu();
                break;
        }
    }

    //Menu to find the books by Title, Author's name or The year of writing the book
    public static void menuToFind() {
        System.out.println("\nHere you can find the book by Title, Author's name or The year of writing the book.\n" +
                           "Enter one of number using your keyboard:\n" +
                           "1.Find book by Title\n" +
                           "2.Find book by Author's name\n" +
                           "3.Find book by The year of writing the book\n" +
                           "4.Come back to the main menu");
        switch (checkIntInput()) {
            case 1:
                findBookByTitle();
                break;
            case 2:
                findBookByAuthor();
                break;
            case 3:
                findBookByYear();
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("You entered incorrect number. Try again!");
                menuToFind();
                break;
        }
    }

    //Menu for remove the authors or the books
    public static void menuToRemove() {
        System.out.println("Here you can remove a book or an author.\n" +
                           "Enter one of number using your keyboard:\n" +
                           "1.Remove a book\n" +
                           "2.Remove an author\n" +
                           "3.Come back to the main menu");
        switch (checkIntInput()) {
            case 1:
                removeBook();
                break;
            case 2:
                removeAuthor();
                break;
            case 3:
                mainMenu();
                break;
            default:
                System.out.println("You entered incorrect number. Try again!");
                menuToRemove();
                break;
        }
    }

    //Create new book and add it to the ArrayList
    public static void booksCreator(ArrayList<Book> libraryOfBooks) {
        System.out.println("Here you can create a new book. The book will be automatically add to the author." +
                "\nEnter the data correctly.");
        BookFactory.getNewBook(libraryOfBooks); //Move to the "Factory" method
        save(listOfAuthors,listOfBooks);
        libraryOfBooks.forEach(System.out::println);
        mainMenu();
    }

    //Create new author and add it to the ArrayList
    public static void authorCreator(ArrayList<Author> libraryOfBooks) {
        System.out.println("Here you can create a new author. Enter the data correctly.");
        AuthorFactory.getNewAuthor(libraryOfBooks); //Move to the "Factory" method
        save(listOfAuthors,listOfBooks);
        libraryOfBooks.forEach(System.out::println);
        mainMenu();
    }

    //Save the information from the ArrayList to the files (Authors.txt and Books.txt)
    public static void save(ArrayList<Author> listOfAuthors, ArrayList<Book> listOfBooks){
        FileHandler.writeFile(listOfAuthors.toString() , Constants.AUTHOR);
        FileHandler.writeFile(listOfBooks.toString() , Constants.BOOKS);
    }

    //Find the book by the title of book
    public static void findBookByTitle() {
        System.out.println("This is a list of books: ");
        listOfBooks.forEach(System.out::println);
        System.out.println("Enter the title of book: ");
        String bookTitle = checkStringInput();                                   //Enter the book's title
        for (int i = 0; i < listOfBooks.size(); i++) {
            if (((listOfBooks.get(i)).getTitle()).equalsIgnoreCase(bookTitle)){ //Compare all the book's title ->
                System.out.println("Here are the books you were looking for:");
                System.out.println((listOfBooks.get(i)).toString());            //-> with entered "bookTitle"
                System.out.println(" ");
            } else if ((i == listOfBooks.size() - 1) && !bookTitle.equalsIgnoreCase(listOfBooks.get(i).getTitle())) {
                System.out.println("There is no book with this title! Do you want to try again?\n" +
                                   "1.Yes, I do\n" +
                                   "No, I don't. Come back to the find menu(Click on any number)");
                if (checkIntInput() == 1) {
                    findBookByTitle();
                }
            }
        }
        menuToFind();
    }

    //Find the book by the name of author
    public static void findBookByAuthor() {
        System.out.println("This is a list of books: ");
        listOfBooks.forEach(System.out::println);
        System.out.println("You need to write two words!\nEnter the name of author: ");
        String authorName = checkTwoWordsStringInput();                             //Enter the author's name
        for (int i = 0; i < listOfBooks.size(); i++) {
            if (((listOfBooks.get(i)).getAuthor()).equalsIgnoreCase(authorName)) { //Compare all the author's name ->
                System.out.println("Here are the books you were looking for:");
                System.out.println((listOfBooks.get(i)).toString());             //-> with entered "authorName"
                System.out.println(" ");
            }else if ((i == listOfBooks.size() - 1) && !authorName.equalsIgnoreCase(listOfBooks.get(i).getAuthor())) {
                System.out.println("There is no book with this author's name! Do you want to try again?\n" +
                                   "1.Yes, I do\n" +
                                   "No, I don't. Come back to the find menu(Click on any number)");
                if (checkIntInput() == 1) {
                    findBookByAuthor();
                }
            }
        }
        menuToFind();
    }

    //Find the book by the year of writing
    public static void findBookByYear() {
        System.out.println("This is a list of books: ");
        listOfBooks.forEach(System.out::println);
        System.out.println("Enter the year of writing the book :");
        int yearOfWriting = checkIntInput();                              //Enter the books' year of writing
        for (int i = 0; i < listOfBooks.size(); i++) {
            if ((listOfBooks.get(i)).getYearWriting() == yearOfWriting){ //Compare all the book's year of writing ->
                System.out.println("Here are the books you were looking for:");
                System.out.println((listOfBooks.get(i)).toString());//-> with entered "yearOfWriting"
                System.out.println(" ");
            }else if ((i == listOfBooks.size() - 1) && yearOfWriting !=(listOfBooks.get(i).getYearWriting())) {
                System.out.println("There is no book with this year of writing! Do you want to try again?\n" +
                                   "1.Yes, I do\n" +
                                   "No, I don't. Come back to the find menu(Click on any number)");
                if (checkIntInput() == 1) {
                    findBookByYear();
                }
            }
        }
        menuToFind();
    }

    //Remove the book
    public static void removeBook() {
        System.out.println("This is a list of books: ");
        listOfBooks.forEach(System.out::println);
        System.out.println("Enter the title of book who you want to remove: ");
        String bookTitle = checkStringInput();                                  //Enter book's title
        for (int i = 0; i < listOfBooks.size(); i++) {
            if (((listOfBooks.get(i)).getTitle()).equalsIgnoreCase(bookTitle)) {//Compare all the book's titles with ->
                listOfBooks.remove(i);                                          //-> entered "bookTitle"
                save(listOfAuthors, listOfBooks);
                listOfBooks.forEach(System.out::println);
                System.out.println("The book is deleted! Look up");
            }else if ((i == listOfBooks.size() - 1) && !bookTitle.equalsIgnoreCase(listOfBooks.get(i).getTitle())){
                System.out.println("There is no book with this title! Do you want to try again?\n" +
                                   "1.Yes, I do\n" +
                                   "No, I don't. Come back to the remove menu(Click on any number)");
                if (checkIntInput() == 1) {
                    removeBook();
                }
            }

        }
        menuToRemove();
    }

    //Remove the author
    public static void removeAuthor() {
        System.out.println("This is a list of authors: ");
        listOfAuthors.forEach(System.out::println);
        System.out.println("Enter the firstname and lastname of author who you want to remove:");
        String authorName = checkTwoWordsStringInput();          //Enter the author's name
        for (int i = 0; i < listOfAuthors.size(); i++) {
            if ((((listOfAuthors.get(i)).getFirstName()) + " " + ((listOfAuthors.get(i)).getLastName()))
                    .equalsIgnoreCase(authorName)) {            //Concatenate the strings "firstname" and "lastname" ->
                listOfAuthors.remove(i);                        //-> compare with entered "authorName"
                save(listOfAuthors, listOfBooks);
                listOfAuthors.forEach(System.out::println);
                System.out.println("The author is deleted! Look up");
            }else if ((i == listOfAuthors.size() - 1) && !authorName
                    .equalsIgnoreCase(((listOfAuthors.get(i))
                            .getFirstName())+ " " + ((listOfAuthors.get(i)).getLastName()))) {
                System.out.println("There is no author with this name! Do you want to try again?\n" +
                                   "1.Yes, I do\n" +
                                   "No, I don't. Come back to the remove menu(Click on any number)");
                switch (checkIntInput()) {
                    case 1:
                        removeAuthor();
                        break;
                    default:
                        System.out.println("You entered incorrect number!\nSort will be to the books' ID");
                        break;
                }
            }
        }
        menuToRemove();
    }

    //Sort books by author, year of writing and title
    public static void sortBooks() {
        System.out.println("Here you can sort the books.\nChange one of numbers:\n1.Sort books by Authors" +
                "\n2.Sort books by Year of writing\n3.Sort books by Title");
        switch (checkIntInput()) {
            case 1:
                listOfBooks.sort(Comparator.comparing(Book::getAuthor));
                break;
            case 2:
                listOfBooks.sort(Comparator.comparing(Book::getYearWriting));
                break;
            case 3:
                listOfBooks.sort(Comparator.comparing(Book::getTitle));
                break;
            default:
                System.out.println("You entered incorrect number!\nSort will be to the books' ID");
                break;
        }
        save(listOfAuthors,listOfBooks);
        listOfBooks.forEach(System.out::println);
        System.out.println("The books was sorted! Look up");
        mainMenu();
    }
}