package subjectObject;

public class Repair {
    private String dormitory; // 寝室门牌号
    private String reporter; // 报修人
    private String content; // 报修内容
    private String repairDate; // 报修日期
    private String handling; // 处理情况
    
    public String getDormitory() {
        return dormitory;
    }
    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }
    public String getReporter() {
        return reporter;
    }
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRepairDate() {
        return repairDate;
    }
    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }
    public String getHandling() {
        return handling;
    }
    public void setHandling(String handling) {
        this.handling = handling;
    }
}
