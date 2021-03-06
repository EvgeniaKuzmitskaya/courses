package by.myProject.model.domain.enums;

public enum TypeRole {

    ROLE_ADMIN("ADMIN"),
    ROLE_TEACHER("TEACHER"),
    ROLE_STUDENT("STUDENT");

    String roleType;

    private TypeRole(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
