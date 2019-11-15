package bg.sofia.uni.fmi.mjt.jira.issues;

import bg.sofia.uni.fmi.mjt.jira.ActionLog;
import bg.sofia.uni.fmi.mjt.jira.enums.IssuePriority;
import bg.sofia.uni.fmi.mjt.jira.enums.IssueResolution;
import bg.sofia.uni.fmi.mjt.jira.enums.IssueStatus;
import bg.sofia.uni.fmi.mjt.jira.enums.WorkAction;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Issue {

    private static final int MAX_ACTIONS = 20;
    private static int numberForIssue = 0;
    private int actionSize = 0;
    private String issueId;
    private String description;
    private IssuePriority priority;
    private IssueResolution resolution = IssueResolution.UNRESOLVED;
    private IssueStatus status = IssueStatus.OPEN;
    private Component component;
    private String[] actionLog = new String[MAX_ACTIONS];
    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    public Issue(IssuePriority priority, Component component, String description) {
        this.priority = priority;
        this.component = component;
        this.description = description;
        issueId = component.getShortName() + "-" + numberForIssue;
        numberForIssue++;
        creationTime = LocalDateTime.now();
        modificationTime = creationTime;
    }

    public String getIssueID() {
        return issueId;
    }

    public String getDescription() {
        return description;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public IssueResolution getResolution() {
        return resolution;
    }

    public void setResolution(IssueResolution resolution) {
        this.resolution = resolution;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Component getComponent() {
        return component;
    }

    public LocalDateTime getCreatedOn() {
        return creationTime;
    }

    public LocalDateTime getLastModifiedOn() {
        return modificationTime;
    }

    public String[] getActionLog() {
        return actionLog;
    }

    public void addAction(WorkAction action, String description) {
        if (action == null || description == null) {
            throw new RuntimeException();
        }
        if (actionSize < 20) {
            ActionLog log = new ActionLog(action, description);
            actionLog[actionSize++] = log.toString();
        } else {
            throw new RuntimeException("Action size is equal to 20 and you can not add more actions");
        }
    }

    public abstract void resolve(IssueResolution resolution);

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return issueId.equals(issue.issueId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(issueId);
    }
}
