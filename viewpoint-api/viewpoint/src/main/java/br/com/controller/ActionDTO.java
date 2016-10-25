package br.com.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class ActionDTO {

    @Length(max = 60, message = "O nome do evento deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do nome é obrigatório")
    private String name;

    @Length(max = 60, message = "O grupo do evento deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do nome é obrigatório")
    private String group;

    private List<ParamDTO> parameters;

    @Length(max = 40, message = "O ip que enviou o evento deve conter no máximo 40 caracteres")
    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<ParamDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParamDTO> parameters) {
        this.parameters = parameters;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
