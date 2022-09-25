package one.appscale.relayclientapi.infra.mail;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.infra.mail.type.RelayMimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class RelayMailSender {
    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    // TODO RA-96
    @SneakyThrows
    public void sendMimeMessage(final RelayMimeMessage mimeMessage) {
        final MimeMessage message = sender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mimeMessage.title());
        helper.setTo(mimeMessage.to());
        Context context = new Context();
        mimeMessage.variables().forEach(context::setVariable);

        String html = templateEngine.process(mimeMessage.templateName(), context);
        helper.setText(html, true);

        sender.send(message);
    }
}
