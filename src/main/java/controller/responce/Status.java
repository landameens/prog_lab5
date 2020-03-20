package controller.responce;

import domain.studyGroup.Semester;

public enum Status {
    SUCCESSIVELY("200"),
    BAD_REQUEST("400"),
    INTERNAL_ERROR("500"),
    NOT_IMPLEMENTED("501");

    private String code;
    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Status getStatusEnum(String code){
        Status[] allStatuses = Status.values();

        for (Status status : allStatuses) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }

        return null;
    }
}
