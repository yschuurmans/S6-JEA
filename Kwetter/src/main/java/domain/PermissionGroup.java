package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PermissionGroup {
    public static final String ADMIN_GROUP_NAME ="AdminGroup", MODERATOR_GROUP_NAME="ModeratorGroup", USER_GROUP_NAME="UserGroup";
    public static final PermissionGroup ADMIN_GROUP=new PermissionGroup(ADMIN_GROUP_NAME), MODERATOR_GROUP = new PermissionGroup(MODERATOR_GROUP_NAME), USER_GROUP = new PermissionGroup(USER_GROUP_NAME);
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
