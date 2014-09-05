/** 
 * Copyright (c) 2012-2014 B2M Software AG
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.b2m.software.odata.utilities;

import java.util.ArrayList;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.odata4j.producer.jpa.JPAProducer;

import de.b2m.software.entities.Book;
import de.b2m.software.entities.Category;
import de.b2m.software.entities.Library;
import de.b2m.software.entities.Writer;
import de.b2m.software.utilites.Constants;

/**
 * @author sdubey
 *
 */
public class LibraryJPACreator {

	// Some dummy data.
	final static String categories[] = { "Science", "Fiction", "Poetry" };
	
	final static String writers[] = { "Stephen Hawking", "Richard Dawkins",
			"Isaac Newton", "Houghton Mifflin", "Charles Darwin", "Jared Diamond",
			"F. Scott Fitzgerald", "J. R. R. Tolkien", "Vladimir Nabokor", "Charles Dickens", "Jane Austen", "William Golding",
			"Aldous Huxley", "George Orwell", "Dante", "Kahlil Gibran", "Mary Oliver", "John Milton", "Homer"};
	
	final static String scienceBooks[] = { "A%20Brief%20History%20of%20Time", "The%20Selfish%20Gene",
			"Principia%20Mathematica", "Silent%20Spring", "The%20Voyage%20of%20the%20Beagle", "Collapse"};
	
	final static String fictionBooks[] = { "The%20Great%20Gatsby", "The%20Lord%20of%20the%20Rings", "Lolita",
		"Hard%20Times", "Emma", "Lord%20of%20the%20Flies", "Brave%20New%20World", "Animal%20Farm" };

	final static String poetryBooks[] = { "The%20Divine%20Comedy", "The%20Prophet", "Blue%20Horses%20Poems",
		"Paradise%20Lost", "The%20Iliad"};
	
	/**
	 * This method actually creates the JPA Producer
	 * @return	The JPA Producer
	 */
	public static JPAProducer createProducer() {

		System.out.println("Initializing..");
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);
		System.out.println("Creating producer...");
		JPAProducer producer = new JPAProducer(entityManagerFactory,
				Constants.NAMESPACE, 50);

		// Lets create some dummy data.
		System.out.println("Initializing database...");
		initdatabaseRecords(entityManagerFactory);
		System.out.println("Producer created successfully...");
		return producer;

	}

	private static void initdatabaseRecords(
			EntityManagerFactory entityManagerfactory) {

		final EntityManager manager = entityManagerfactory
				.createEntityManager();
		final Random rand = new Random();

		// Add library.
		manager.getTransaction().begin();
		Library libAll = new Library(101, "AllBookLibrary");
		manager.merge(libAll);
		manager.getTransaction().commit();

		// Add Book Categories
		manager.getTransaction().begin();
		ArrayList<Category> listCategories = new ArrayList<Category>();

		for (int i = 0; i < categories.length; i++) {
			Category category = new Category(101 + i, categories[i]);
			listCategories.add(category);
			manager.merge(category);
		}

		manager.getTransaction().commit();

		// Add Book Writers
		manager.getTransaction().begin();
		ArrayList<Writer> listWriters = new ArrayList<Writer>();

		for (int i = 0; i < writers.length; i++) {
			Writer author = new Writer(101 + i, writers[i]);
			Library library = manager.find(Library.class, libAll.getId());
			author.setLibrary(library);
			library.getWriters().add(author);
			listWriters.add(author);
			manager.merge(author);
			manager.merge(library);
		}
		manager.getTransaction().commit();

		// Add science books
		manager.getTransaction().begin();
		for (int i = 0; i < scienceBooks.length; i++) {
			Book book = new Book(scienceBooks[i], rand.nextInt(800));
			Library library = manager.find(Library.class, libAll.getId());
			book.setLibrary(library);
			Category category = manager.find(Category.class, listCategories
					.get(0).getId());
			book.setCategory(category);
			Writer writer = manager.find(Writer.class, listWriters.get(i)
					.getId());
			book.setWriter(writer);
			manager.merge(book);
			category.getBooks().add(book);
			manager.merge(category);
			library.getBooks().add(book);
			manager.merge(library);
			writer.getBooks().add(book);
			manager.merge(writer);
		}

		// Add fictionBooks books
		for (int i = 0; i < fictionBooks.length; i++) {
			Book book = new Book(fictionBooks[i], rand.nextInt(800));
			Library library = manager.find(Library.class, libAll.getId());
			book.setLibrary(library);
			Category category = manager.find(Category.class, listCategories
					.get(1).getId());
			book.setCategory(category);
			Writer writer = manager.find(Writer.class, listWriters.get(i+scienceBooks.length)
					.getId());
			book.setWriter(writer);
			manager.merge(book);
			category.getBooks().add(book);
			manager.merge(category);
			library.getBooks().add(book);
			manager.merge(library);
			writer.getBooks().add(book);
			manager.merge(writer);
		}
		
		// Add  poetryBooks books
		for (int i = 0; i <  poetryBooks.length; i++) {
			Book book = new Book( poetryBooks[i], rand.nextInt(800));
			Library library = manager.find(Library.class, libAll.getId());
			book.setLibrary(library);
			Category category = manager.find(Category.class, listCategories
					.get(2).getId());
			book.setCategory(category);
			Writer writer = manager.find(Writer.class, listWriters.get(i+ (scienceBooks.length + fictionBooks.length))
					.getId());
			book.setWriter(writer);
			manager.merge(book);
			category.getBooks().add(book);
			manager.merge(category);
			library.getBooks().add(book);
			manager.merge(library);
			writer.getBooks().add(book);
			manager.merge(writer);
		}
		
		manager.getTransaction().commit();

	}

}
