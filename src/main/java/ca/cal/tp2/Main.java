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

        EmprunteurService es = new EmprunteurService(
                new EmprunteurRepositoryJPA(),
                new DocumentRepositoryJPA());

        ps.createPrepose("Edouard", "Roger", "edouard@gmail.com",
                "4389694949", "3453");
        ps.createEmprunteur("Lois", "Lane", "lois@hotmail.com",
                "5141234567", 56.54, null, null);
        ps.createEmprunteur("Jean", "Louis", "jean@hotmail.com",
                "5141234567", 3.42, null, null);

        ps.saveCd("Thriller", "Michael Jackson", 42, "Pop", 5);
        ps.saveCd("The Wall", "Pink Floyd", 20, "Rock", 6);
        ps.saveDvd("The Dark Knight", "Christopher Nolan", 123, "Action", 3);
        ps.saveDvd("Inception", "Christopher Nolan", 142, "Action", 1);
        ps.saveLivre("Harry Potter and The Deathly Hallows", "J.K. Rowling", 2001, 4);
        ps.saveLivre("The Hobbit", "J.R.R. Tolkien", 2012, 2);
        System.out.println(ps.findCd(1));

        es.emprunteDocument(2,1);
        es.emprunteDocument(2, 3);
        es.emprunteDocument(2, 4);

        // Cette ligne donne une erreur car le document id 4 n'a qu'un seul exemplaire
        //es.emprunteDocument(2, 4);

        System.out.println(ps.findEmprunteur(2));
        System.out.println(ps.findPrepose(1));

        System.out.println(es.getRapportsEmprunts(2));

        System.out.println(ps.findCdByTitle("Wall"));
        System.out.println(ps.findLivreByAuthor("Rowling"));

        Thread.currentThread().join();
    }
}
