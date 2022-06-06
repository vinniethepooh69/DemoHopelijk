package classes;

import jakarta.xml.bind.annotation.XmlAttribute;

public class BookInformation {
    @XmlAttribute(name = "title_of_Book",required = true)
    public String title_of_Book;
    @XmlAttribute
    public boolean awardWon;
}