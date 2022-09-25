package one.appscale.relayclientapi.domain.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RelayMailSender {
    @Value("${app.mail.user}")
    private String from;

    private final JavaMailSender sender;

    public void sendMessage(final String mailTo, final String subject, final String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
        log.info("mail sent to:{}", mailTo);
    }
}
