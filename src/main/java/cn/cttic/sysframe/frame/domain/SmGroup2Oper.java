package cn.cttic.sysframe.frame.domain;

public class SmGroup2Oper extends SmGroup2OperKey {
    private String state;

    private String leaderState;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getLeaderState() {
        return leaderState;
    }

    public void setLeaderState(String leaderState) {
        this.leaderState = leaderState == null ? null : leaderState.trim();
    }
}