package application.functions;

import application.constants.Constants;
import application.classes.Author;
import application.classes.Book;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    //Read the information into the file. If there is no file it's created.
    public static String fileReader(String fileName) {
        String everything = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
            br.close();

        } catch (IOException e) {                  //Create a new file if there is none
            new File(fileName + ".txt");
        }
        return everything;
    }

    //Write the information into the file.
    public static void writeFile(String objectToWrite, String fileName) {
        try {
            File file = new File(fileName + ".txt");
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(objectToWrite);

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Add the information from a file to ArrayList.
    public static ArrayList<Book> addIntoBookArrayListFromFile(ArrayList<Book> list) {
        String stringToParse = fileReader(Constants.BOOKS);
        if (stringToParse != null) {
            stringToParse = stringToParse.substring(2, stringToParse.length() - 1);
            stringToParse = stringToParse                                   //Pars the information from "String" type->
                    .replace("title=", "")                  //-> to Array
                    .replace("author=", "")
                    .replace("description=", "")
                    .replace("yearWriting=", "")
                    .replace("bookID=", "")
                    .replace("'", "")
                    .replace("}]", "");
            String[] stringToParseArray = stringToParse.split("}, \\{");
            for (int i = 0; i < stringToParseArray.length; i++) {          //Providing the information to "Book" type//
                Book book = new Book();
                String[] oneBook = stringToParseArray[i].split(",");
                book.setTitle(oneBook[0].strip());
                book.setAuthor(oneBook[1].strip());
                book.setDescription(oneBook[2].strip());
                book.setYearWriting(Integer.parseInt((oneBook[3].strip())));
                book.setBookID(Integer.parseInt(oneBook[4].strip()));
                list.add(book);
            }
        }
        return list;
    }

    //Add the information from a file to ArrayList.
    public static ArrayList<Author> addIntoAuthorArrayListFromFile(ArrayList<Author> list) {
        String stringToParse = fileReader(Constants.AUTHOR);
        if (stringToParse != null) {
            stringToParse = stringToParse.substring(2, stringToParse.length() - 1);
            stringToParse = stringToParse                                  //Pars the information from "String" type ->
                    .replace("firstName=", "")             //-> to Array
                    .replace("lastName=", "")
                    .replace("city=", "")
                    .replace("list of books=", "")
                    .replace("yearBirth=", "")
                    .replace("'", "")
                    .replace("}]", "");
            String[] stringToParseArray = stringToParse.split("}, \\{");
            for (int i = 0; i < stringToParseArray.length; i++) {          //Providing the information to "Book" type//
                Author author = new Author();
                String[] oneBook = stringToParseArray[i].split(",");
                author.setFirstName(oneBook[0].strip());
                author.setLastName(oneBook[1].strip());
                author.setCity(oneBook[2].strip());
                author.setBooks(oneBook[3].strip());
                author.setYearBirth(Integer.parseInt(oneBook[4].strip()));
                list.add(author);
            }
        }
        return list;
    }
}