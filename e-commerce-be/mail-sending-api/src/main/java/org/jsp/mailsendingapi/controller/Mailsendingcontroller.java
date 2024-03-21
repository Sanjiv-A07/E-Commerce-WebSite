package org.jsp.mailsendingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class Mailsendingcontroller {

	@Autowired
	private JavaMailSender javamailsender;
	@Value(value = "abcde")
	private String token;

	@PostMapping("/send-mail")
	public String sendmail(HttpServletRequest request, @RequestParam String email) {

		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "/verify") + "?token=" + token;

		MimeMessage message = javamailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setSubject("Testing mail sending api");
			helper.setText(url);
			helper.setTo(email);

			javamailsender.send(message);

			return "mail has been send";
		} catch (MessagingException e) {

			e.printStackTrace();
			return "Connot send a main";
		}

	}
@GetMapping("/verify")
	public String verify(@RequestParam String token) {
		if (this.token.equals(token))
			return "verification sucessfull";
		else
			return "Connot verify";
	}

}
