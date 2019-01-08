package bean;

public class CmkczycltzBean {
    private String id;
    private String ktsj;//勘探时间
    private String ktfw; //勘探范围
    private String kz; //矿种
    private String ksl; //合计矿石量
    private String pw; //合计品味
    private String jsl; //合计金属量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj;
    }

    public String getKtfw() {
        return ktfw;
    }

    public void setKtfw(String ktfw) {
        this.ktfw = ktfw;
    }

    public String getKz() {
        return kz;
    }

    public void setKz(String kz) {
        this.kz = kz;
    }

    public String getKsl() {
        return ksl;
    }

    public void setKsl(String ksl) {
        this.ksl = ksl;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getJsl() {
        return jsl;
    }

    public void setJsl(String jsl) {
        this.jsl = jsl;
    }

    //    @SmartColumn(id = 1, name = "ktsj") //勘探时间
//    private String ktsj;
//    @SmartColumn(id = 2, name = "kccd")//勘查程度
//    private String kccd;
//    @SmartColumn(id = 3, name = "ktfw")//勘探范围
//    private String ktfw;
//    @SmartColumn(id = 4, name = "hj")//合计
//    private String hj;
//    @SmartColumn(type = ColumnType.ArrayChild)//矿石类型
//    private List<KslxBean> kslxList;
//
//
//    public CmkczycltzBean(String ktsj, String kccd, String ktfw, List<KslxBean> kslxList) {
//        this.ktsj = ktsj;
//        this.kccd = kccd;
//        this.ktfw = ktfw;
//        this.kslxList = kslxList;
//    }
//
//    public String getKtsj() {
//        return ktsj;
//    }
//
//    public void setKtsj(String ktsj) {
//        this.ktsj = ktsj;
//    }
//
//    public String getKccd() {
//        return kccd;
//    }
//
//    public void setKccd(String kccd) {
//        this.kccd = kccd;
//    }
//
//    public String getKtfw() {
//        return ktfw;
//    }
//
//    public void setKtfw(String ktfw) {
//        this.ktfw = ktfw;
//    }
//
//    public String getHj() {
//        return hj;
//    }
//
//    public void setHj(String hj) {
//        this.hj = hj;
//    }
//
//    public List<KslxBean> getKslxList() {
//        return kslxList;
//    }
//
//    public void setKslxList(List<KslxBean> kslxList) {
//        this.kslxList = kslxList;
//    }

}
