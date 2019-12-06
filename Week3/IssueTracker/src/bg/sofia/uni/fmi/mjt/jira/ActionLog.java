package bg.sofia.uni.fmi.mjt.jira;

import bg.sofia.uni.fmi.mjt.jira.enums.WorkAction;

public class ActionLog {

    private WorkAction action;
    private String description;

    public ActionLog(WorkAction action, String description) {
        this.action = action;
        this.description = description;
    }

    @Override
    public String toString() {
        return action + ": " + description;
    }
}
