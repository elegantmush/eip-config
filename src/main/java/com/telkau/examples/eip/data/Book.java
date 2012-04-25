package com.telkau.examples.eip.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Value type representing a book. Used as an example of sending more complex
 * Object content as the payload of a message rather than the preferable and
 * simpler interchangeable formats such as XML or JSON. If you have any control
 * over payload type, DO choose an interchangeable format over a proprietary/
 * programming language specific one. A Java Object type is used for this
 * example as it's more error-prone, and we want to prove it can be done!
 * 
 * @author elegantmush
 * 
 */
public class Book implements Serializable {

	private static final long serialVersionUID = 2670209370258958929L;

	private final String title;
	private final String author;
	private final Date publicationDate;

	public Book(String title, String author, Date publicationDate) {
		this.title = title;
		this.author = author;
		// defensively copy mutable Date
		this.publicationDate = new Date(publicationDate.getTime());
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((publicationDate == null) ? 0 : publicationDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (publicationDate == null) {
			if (other.publicationDate != null)
				return false;
		} else if (!publicationDate.equals(other.publicationDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Book [title=%s, author=%s, publicationDate=%s]", title, author,
				publicationDate);
	}
}
