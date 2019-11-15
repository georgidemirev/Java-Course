package bg.sofia.uni.fmi.mjt.jira;

import bg.sofia.uni.fmi.mjt.jira.enums.IssueResolution;
import bg.sofia.uni.fmi.mjt.jira.enums.WorkAction;
import bg.sofia.uni.fmi.mjt.jira.interfaces.Filter;
import bg.sofia.uni.fmi.mjt.jira.interfaces.Repository;
import bg.sofia.uni.fmi.mjt.jira.issues.Issue;

public class Jira implements Filter, Repository {
    private static final int MAX_ISSUES = 100;

    int size = 0;

    Issue[] issues = new Issue[MAX_ISSUES];

    public Jira() {
    }

    @Override
    public Issue find(String issueID) {

        return null;
    }

    @Override
    public void addIssue(Issue issue) {

    }

    public void addActionToIssue(Issue issue, WorkAction action, String actionDescription) {
    }

    public void resolveIssue(Issue issue, IssueResolution resolution) {
    }
}
