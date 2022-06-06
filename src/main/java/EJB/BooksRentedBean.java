package EJB;

import classes.Book;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;

import java.util.List;

@jakarta.ejb.Singleton(name = "BooksRentedEJB")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class BooksRentedBean {
    public BooksRentedBean() {
    }
    private List<Book> BooksRentedOut;
    @Lock(LockType.WRITE)
    public void rentBook(Book book){
        BooksRentedOut.add(book);
    }
    @Lock(LockType.READ)
    public boolean bookAvailable(Book book){
        return BooksRentedOut.contains(book);
    }
    @Lock(LockType.WRITE)
    public void bookReturned(Book book){
        BooksRentedOut.remove(book);
    }
}