package model;

public enum Status {

    UNUSED(0, "unused"),
    OK(1, "ok"),
    DELETED(2, "deleted")
    ;

    private int id;
    private String value;

    Status(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int id() {
        return this.id;
    }

    public String value() {
        return this.value;
    }
}
