package ca.cal.tp2;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.repository.*;
import ca.cal.tp2.service.EmprunteurService;
import ca.cal.tp2.service.PreposeService;
import ca.cal.tp2.utils.TcpServer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException, DatabaseException {
        TcpServer.createTcpServer();

        PreposeService ps = new PreposeService(new PreposeRepositoryJPA(),
                                                new EmprunteurRepositoryJPA(),
                                                new CdRepositoryJPA(),
                                                new DvdRepositoryJPA(),
                                                new LivreRepositoryJPA());

        ps.createPrepose("Edouard", "Roger", "edouard@gmail.com",
                "4389694949", "3453");
        ps.createEmprunteur("Lois", "Lane", "lois@hotmail.com",
                "5141234567", 56.54, null, null);

        System.out.println(ps.findPrepose(1));
        System.out.println(ps.findEmprunteur(1));

        ps.saveCd("Thriller", "Michael Jackson", 42, "Pop", 5);
        ps.saveCd("The Wall", "Pink Floyd", 42, "Rock", 6);
        ps.saveDvd("The Dark Knight", "Christopher Nolan", 42, "Action", 3);
        ps.saveDvd("Inception", "Christopher Nolan", 42, "Action", 1);
        ps.saveLivre("Harry Potter", "J.K. Rowling", 2001, 4);
        ps.saveLivre("The Hobbit", "J.R.R. Tolkien", 2012, 2);
        System.out.println(ps.findCd(1));


        System.out.println(ps.findPrepose(1));
        Thread.currentThread().join();
    }
}
