package br.com.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmailService {

    public static void sendEmail(String emailTo, String emailName, String subject, String messsage) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        //Utilize o hostname do seu provedor de email
        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(465);
        //Adicione os destinatários
        email.addTo(emailTo, emailName);
        //Configure o seu email do qual enviará
        email.setFrom("team.viewpoint45@gmail.com", "Viewpoint Team");
        //Adicione um assunto
        email.setSubject(subject);
        //Adicione a mensagem do email
        email.setMsg(messsage);
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setSSL(true);
        email.setAuthentication("team.viewpoint45@gmail.com", "viewpointfatec");
        email.send();
    }

}
