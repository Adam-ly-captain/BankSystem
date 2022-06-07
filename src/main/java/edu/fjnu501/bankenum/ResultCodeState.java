package edu.fjnu501.bankenum;

public enum ResultCodeState {

    SUCCESS(200), INVALID(400), FAILED(500), PASSWORD(405), UNLOGIN(501);

    private int state;

    ResultCodeState(int code) {
        this.state = code;
    }

    public int getState() {
        return state;
    }

}
