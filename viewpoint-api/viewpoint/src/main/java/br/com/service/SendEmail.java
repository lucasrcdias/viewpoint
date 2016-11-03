package br.com.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {

    public void sendEmail() throws EmailException {

        SimpleEmail email = new SimpleEmail();
        //Utilize o hostname do seu provedor de email
        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(465);
        //Adicione os destinatários
        email.addTo("viniciuslopeslps@gmail.com", "Vinicius");
        //Configure o seu email do qual enviará
        email.setFrom("team.viewpoint45@gmail.com", "Teste email");
        //Adicione um assunto
        email.setSubject("Teste de email");
        //Adicione a mensagem do email
        email.setMsg("Esse é um teste de email");
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setSSL(true);
        email.setAuthentication("team.viewpoint45@gmail.com", "viewpointfatec");
        email.send();
    }

    public static void main(String[] args) throws EmailException {
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendEmail();
    }
}
