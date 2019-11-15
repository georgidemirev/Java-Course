package bg.sofia.uni.fmi.mjt.jira.issues;

import bg.sofia.uni.fmi.mjt.jira.enums.IssuePriority;
import bg.sofia.uni.fmi.mjt.jira.enums.IssueResolution;

public class Feature extends Issue {
    public Feature(IssuePriority priority, Component component, String description) {
        super(priority, component, description);
    }

    @Override
    public void resolve(IssueResolution resolution) {
        if (resolution == null) {
            throw new RuntimeException();
        }
        boolean f = false, i = false, t = false;
        for (String q : getActionLog()) {
            if (q.charAt(0) == 'f' || q.charAt(0) == 'F') {
                f = true;
            }
            if (q.charAt(0) == 'i' || q.charAt(0) == 'I') {
                i = true;
            }
            if (q.charAt(0) == 't' || q.charAt(0) == 'T') {
                t = true;
            }
        }
        if (i && t && f) {
            setResolution(resolution);
        } else {
            throw new RuntimeException();
        }
    }
}
