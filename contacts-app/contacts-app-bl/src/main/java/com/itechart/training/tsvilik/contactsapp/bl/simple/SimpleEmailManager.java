package com.itechart.training.tsvilik.contactsapp.bl.simple;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;

import com.itechart.training.tsvilik.contactsapp.bl.EmailManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.sun.mail.util.MimeUtil;

public class SimpleEmailManager implements EmailManager {
	private static Logger logger = Logger.getLogger(SimpleEmailManager.class);

	private void sendEmail(String recipient, String subject, String messageText)
			throws MessagingException {
		logger.debug("email start");

		final String username = "contactsapp2014@gmail.com";
		final String password = "appAdmin";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("contactsapp2014@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipient));
		try {
			message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "Q"));
		} catch (UnsupportedEncodingException e) {
			logger.error("Failed to encode email subject to UTF-8", e);
			message.setSubject(subject);
		}
		message.setContent(messageText, "text/plain; charset=UTF-8");

		Transport.send(message);

		logger.debug("sending email to" + recipient + " done");

	}

	@Override
	public void sendEmailTo(List<Contact> recipients, String subject,
			String message) throws ModelException {
		for (Contact recipient : recipients) {
			logger.debug("send message: " + message);
			ST template = new ST(message);
			template.add("contact", recipient);
			message = template.render();
			try {
				sendEmail(recipient.getEmail(), subject, message);
			} catch (MessagingException e) {
				logger.error(
						"Failed to send email to contact " + recipient.getId(),
						e);
				throw new ModelException(e);
			}
		}
	}

}
