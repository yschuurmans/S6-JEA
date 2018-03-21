package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PermissionGroup {
    public static final String ADMIN_GROUP="AdminGroup", MODERATOR_GROUP="ModeratorGroup", USER_GROUP="UserGroup";
    public static final String[] AllGroups = {"AdminGroup", "ModeratorGroup", "UserGroup"};

    @Id
    private String groupName;

    public PermissionGroup() {
    }

    public PermissionGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
