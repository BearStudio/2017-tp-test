package fr.rouen.mastergil.user;



/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository {

    Authority findOne(String user);
}
