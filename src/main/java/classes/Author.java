package classes;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findAuthorByID", query = "SELECT a FROM Author a where a.personID=?1")
@NamedQuery(name= "getAllAuthors",query = "SELECT a FROM Author a")
@Entity
@Table(name="Author")
public class Author extends Person {

    @ManyToMany(mappedBy = "createdByAuthors")

    private List<Book> ContributedToTheseBooks;

    public Author()
    {
        ContributedToTheseBooks= new ArrayList<Book>();
    }

    public List<Book> getContributedToTheseBooks() {
        return ContributedToTheseBooks;
    }

    public void setContributedToTheseBooks(List<Book> contributedToTheseBooks) {
        ContributedToTheseBooks = contributedToTheseBooks;
    }
    public List<Book> addBook(Book bookvalue)
    {
        ContributedToTheseBooks.add(ContributedToTheseBooks.size(),bookvalue);
        return ContributedToTheseBooks;
    }


}
