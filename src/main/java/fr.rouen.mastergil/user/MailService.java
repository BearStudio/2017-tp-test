package fr.rouen.mastergil.user;


/**
 * Created by rudy on 04/03/17.
 */
public interface MailService {

    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

}
