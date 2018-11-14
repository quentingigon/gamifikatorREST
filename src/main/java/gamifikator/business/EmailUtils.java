package gamifikator.business;

import gamifikator.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Stateless
public class EmailUtils {

	@Resource(name = "java/mail/newpass")
	Session session;

	public void sendPasswordByEmail(User user, String password) throws MessagingException, UnsupportedEncodingException {

		Message mail = new MimeMessage(session);

		mail.setSubject("Your password has been reset.");
		mail.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername()));
		mail.setContent(EmailTemplate.getNewPasswordEmail(password),"text/html; charset=utf-8");

		Transport.send(mail);
	}
}
