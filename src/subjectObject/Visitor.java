package subjectObject;

public class Visitor {
    private String id; // �ÿ�֤����
    private String name; // �ÿ�����
    private String relationship; // ���ܷ��߹�ϵ
    private String enterTime; // ����ʱ��
    private String leaveTime; // �뿪ʱ��
    private String remark; // ��ע
    
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
