package gamifikator.business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Send emails to users
 *
 * */
@Stateless
public class EmailUtils {

	@Resource(name = "mail/amt")
	Session session;

	public void sendPasswordByEmail(String userEmail, String password) throws MessagingException, UnsupportedEncodingException {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "gamifikator.noreply");
		props.put("mail.smtp.password", "gamifikator");
		props.put("mail.smtp.port", "466");
		props.put("mail.smtp.auth", true);

		// Session session = Session.getInstance(props,null);

		Message mail = new MimeMessage(session);

		mail.setSubject("Your password has been reset.");
		mail.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail, "username"));
		mail.setContent(EmailTemplate.getNewPasswordEmail(password),"text/html; charset=utf-8");

		Transport transport = session.getTransport("smtp");
		// transport.sendMessage(mail, new InternetAddress[]{new InternetAddress(userEmail)});

		Transport.send(mail);
	}
}
