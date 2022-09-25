package one.appscale.relayclientapi.infra.mail;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.infra.mail.exception.RelayMessageException;
import one.appscale.relayclientapi.infra.mail.type.RelayMimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class RelayMailSender {
    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    public void sendMimeMessage(final RelayMimeMessage mimeMessage) {
        final MimeMessage message = sender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mimeMessage.title());
            helper.setTo(mimeMessage.to());

            final Context context = new Context();
            mimeMessage.variables().forEach(context::setVariable);

            final String html = templateEngine.process(mimeMessage.templateName(), context);
            helper.setText(html, true);
            sender.send(message);
        } catch (final MessagingException e) {
            final String errorMessage = String.format("email send fail. to:{%s}, title:{%s}", mimeMessage.to(), mimeMessage.title());
            throw new RelayMessageException(errorMessage, e);
        }
    }
}
