package com.vakhnenko.bookmanager.dao;

import com.vakhnenko.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.openSession();
        session.persist(book);
        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        session.update(book);
        logger.info("Book successfully updated. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.openSession();
        Book book = (Book) session.load(Book.class, new Integer(id));

        if (book != null) {
            session.delete(book);
            logger.info("Book successfully deleted. Book details: " + book);
        } else {
            logger.info("Book not found!");
        }
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Book book = (Book) session.load(Book.class, new Integer(id));

        if (book != null) {
            logger.info("Book successfully loaded. Book details: " + book);
        } else {
            logger.info("Book not found!");
        }

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.openSession();
        List<Book> bookList = session.createQuery("from Book").list();

        for (Book book : bookList) {
            logger.info("Book list: " + book);
        }

        return bookList;
    }
}
