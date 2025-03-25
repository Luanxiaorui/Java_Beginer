package subjectObject;

public class Visitor {
    private String id; // 访客证件号
    private String name; // 访客姓名
    private String relationship; // 与受访者关系
    private String enterTime; // 进入时间
    private String leaveTime; // 离开时间
    private String remark; // 备注
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getEnterTime() {
        return enterTime;
    }
    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }
    public String getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
