package one.appscale.relayclientapi.infra.mail;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSender {
    @Value("${app.mail.user}")
    private String from;

    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    // TODO RA-96
    @SneakyThrows
    public void sendMimeMessage(final String mailTo,
                                final String title,
                                final String templateName,
                                Map<String, String> values) {
        final MimeMessage message = sender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(title);
        helper.setTo(mailTo);
        Context context = new Context();
        values.forEach(context::setVariable);

        String html = templateEngine.process(templateName, context);
        helper.setText(html, true);

        sender.send(message);
    }
}
