package model;

public enum OperationEnum {

    PAYMENT ("PAYMENT"),
    REFUND ("REFUND");

    private String operationName;

    OperationEnum(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }
}
