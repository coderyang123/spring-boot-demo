package com.demo.email.service.impl;

import cn.hutool.core.io.resource.ResourceUtil;
import com.demo.email.service.MailService;
import java.net.URL;
import javax.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@SpringBootTest
class MailServiceImplTest {

  @Autowired private MailService mailService;
  @Autowired private TemplateEngine templateEngine;
  @Autowired ApplicationContext context;

  @Test
  void sendSimpleMail() {
    mailService.sendSimpleMail("1023178796@qq.com", "这是一封简单邮件4", "这是一封普通的SpringBoot测试邮件");
  }

  @Test
  void sendHtmlMail() throws MessagingException {
    Context context = new Context();
    context.setVariable("project", "SpringBootDemo");
    context.setVariable("author", "yueyang");
    context.setVariable("url", "https://gitee.com/coderyang456/SpringBootDemo");

    String emailTemplate = templateEngine.process("welcome", context);
    mailService.sendHtmlMail("1023178796@qq.com", "这是一封模板HTML邮件", emailTemplate);
  }

  @Test
  void sendHtmlMail2() throws MessagingException {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(context);
    templateResolver.setCacheable(false);
    templateResolver.setPrefix("classpath:/email/");
    templateResolver.setSuffix(".html");

    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();
    context.setVariable("project", "SpringBootDemo");
    context.setVariable("author", "yueyang");
    context.setVariable("url", "https://gitee.com/coderyang456/SpringBootDemo");

    String emailTemplate = templateEngine.process("test", context);
    mailService.sendHtmlMail("1023178796@qq.com", "这是一封模板HTML邮件", emailTemplate);
  }

  /**
   * 测试附件邮件
   *
   * @throws MessagingException 邮件异常
   */
  @Test
  public void sendAttachmentsMail() throws MessagingException {
    URL resource = ResourceUtil.getResource("static/Linux.png");
    mailService.sendAttachmentsMail(
        "1023178796@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", resource.getPath());
  }

  @Test
  void sendResourceMail() throws MessagingException {
    String rscId = "Linux";
    String content = "<html><body>这是带静态资源的邮件<br/><img src='cid:" + rscId + "' ></body></html>";
    URL resource = ResourceUtil.getResource("static/Linux.png");
    mailService.sendResourceMail(
        "1023178796@qq.com", "这是一封带静态资源的邮件", content, resource.getPath(), rscId);
  }
}
