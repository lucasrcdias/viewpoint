package br.com.controller.response;

import java.util.List;

public class UserGroup {
    public UserGroup(List<String> groups) {
        this.setGroups(groups);
    }

    private List<String> groups;

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
