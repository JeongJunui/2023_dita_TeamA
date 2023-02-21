package warehouse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSMTP {
	String host = "smtp.naver.com";
	private String user = "deuackr2017@naver.com"; // 발신자 아이디
	private String password = "deu@2017"; // 발신자 비밀번호

	public SendMailSMTP(String toEmail, String toTitle, String setMessage) {
		SMTP(toEmail, toTitle, setMessage);
	}

	public void SMTP(String toEmail, String toTitle, String setMessage) {
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(toTitle);// 메일 주제
			message.setText(setMessage);// 메일 내용

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
