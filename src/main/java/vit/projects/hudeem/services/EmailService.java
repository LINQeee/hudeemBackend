package vit.projects.hudeem.services;

import com.mailgun.client.MailgunClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.model.message.Message;

@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendEmail(String to, String subject, String text, String html) {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config("https://api.eu.mailgun.net/", "ec9b24a1a7f69dde690b2db426ea11d0-413e373c-b5f41cd8")
                .createApi(MailgunMessagesApi.class);

        Message message = Message.builder()
                .from("hudeem.info <mailgun@hudeem.info>")
                .to(to)
                .subject(subject)
                .text(text)
                .html(html)
                .build();

        mailgunMessagesApi.sendMessage("hudeem.info", message);
    }
}
