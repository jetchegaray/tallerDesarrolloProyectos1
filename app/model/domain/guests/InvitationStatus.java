package model.domain.guests;

public enum InvitationStatus{

     NOASISTE("canceled"), NOINVITADO("not-invited"), INVITADO("invited"), ASISTE("confirmed");

    private String code;

    public String getCode() {
        return code;
    }

    private InvitationStatus(String code) {
        this.code = code;
    }
}
