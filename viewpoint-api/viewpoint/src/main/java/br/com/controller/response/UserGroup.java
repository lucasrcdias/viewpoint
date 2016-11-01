package br.com.controller.response;

import java.util.Set;

public class UserGroup {
    public UserGroup(Set<String> groups) {
        this.setGroups(groups);
    }

    private Set<String> groups;

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
