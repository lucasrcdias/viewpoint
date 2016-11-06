package br.com.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ActionGroup {
    private String name;
    private String group;
    private Integer total;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date createdAt;

    public ActionGroup(String name, String group, Integer total, Date createAt) {
        this.name = name;
        this.group = group;
        this.total = total;
        this.createdAt = createAt;
    }

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
