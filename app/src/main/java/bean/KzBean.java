package bean;

import com.bin.david.form.annotation.SmartColumn;

public class KzBean {
//    /** 可采矿石量(111b,331) */
//    private String kcksl = "";
//    /** 可采品位(111b,331) */
//    private String kcpw = "";
//    /** 可采金属量(111b,331) */
//    private String kcjsl = "";
//    /** 控制矿石量(122b,332) */
//    private String controlbasevalue = "";
//    /** 控制品位(122b,332) */
//    private String controlbasegrade = "";
//    /** 控制金属量(122b,332) */
//    private String controlbasemares = "";
//    /** 推断矿石量(333) */
//    private String deducevalue = "";
//    /** 推断品位(333) */
//    private String deducegrade = "";
//    /** 推断金属量(333) */
//    private String deduceminerals = "";
//    /** 合计矿石量 */
//    private String resourcevalue = "";
//    /** 合计品位 */
//    private String resourcegrade = "";
//    /** 合计金属量 */
//    private String resourceminerals = "";

    @SmartColumn(id = 6, name = "kzName")
    private String kzName;
    /**
     * 可采矿石量(111b,331)
     */
    @SmartColumn(name = "kcksl")
    private String kcksl;
    /**
     * 可采品位(111b,331)
     */
    @SmartColumn(name = "kcpw")
    private String kcpw;
    /**
     * 可采金属量(111b,331)
     */
    @SmartColumn(name = "kcjsl")
    private String kcjsl;
    /**
     * 控制矿石量(122b,332)
     */
    @SmartColumn(name = "kzksl")
    private String kzksl;
    /**
     * 控制品位(122b,332)
     */
    @SmartColumn(name = "kzpw")
    private String kzpw;
    /**
     * 控制金属量(122b,332)
     */
    @SmartColumn(name = "kzjsl")
    private String kzjsl;
    /**
     * 推断矿石量(333)
     */
    @SmartColumn(name = "tdksl")
    private String tdksl;
    /**
     * 推断品位(333)
     */
    @SmartColumn(name = "tdpw")
    private String tdpw;
    /**
     * 推断金属量(333)
     */
    @SmartColumn(name = "tdjsl")
    private String tdjsl;
    /**
     * 合计矿石量
     */
    @SmartColumn(name = "hjksl")
    private String hjksl;
    /**
     * 合计品位
     */
    @SmartColumn(name = "hjpw")
    private String hjpw;
    /**
     * 合计金属量
     */
    @SmartColumn(name = "hjjsl")
    private String hjjsl;


    public KzBean(String kzName, String kcksl, String kcpw, String kcjsl, String kzksl,
                  String kzpw, String kzjsl, String tdksl, String tdpw, String tdjsl,
                  String hjksl, String hjpw, String hjjsl) {
        this.kzName = kzName;
        this.kcksl = kcksl;
        this.kcpw = kcpw;
        this.kcjsl = kcjsl;
        this.kzksl = kzksl;
        this.kzpw = kzpw;
        this.kzjsl = kzjsl;
        this.tdksl = tdksl;
        this.tdpw = tdpw;
        this.tdjsl = tdjsl;
        this.hjksl = hjksl;
        this.hjpw = hjpw;
        this.hjjsl = hjjsl;
    }

    public KzBean(String kzName, String kcksl, String kcpw, String kcjsl) {
        this.kzName = kzName;
        this.kcksl = kcksl;
        this.kcpw = kcpw;
        this.kcjsl = kcjsl;
    }

    public String getKzName() {
        return kzName;
    }

    public void setKzName(String kzName) {
        this.kzName = kzName;
    }

    public String getKcksl() {
        return kcksl;
    }

    public void setKcksl(String kcksl) {
        this.kcksl = kcksl;
    }

    public String getKcpw() {
        return kcpw;
    }

    public void setKcpw(String kcpw) {
        this.kcpw = kcpw;
    }

    public String getKcjsl() {
        return kcjsl;
    }

    public void setKcjsl(String kcjsl) {
        this.kcjsl = kcjsl;
    }

    public String getKzksl() {
        return kzksl;
    }

    public void setKzksl(String kzksl) {
        this.kzksl = kzksl;
    }

    public String getKzpw() {
        return kzpw;
    }

    public void setKzpw(String kzpw) {
        this.kzpw = kzpw;
    }

    public String getKzjsl() {
        return kzjsl;
    }

    public void setKzjsl(String kzjsl) {
        this.kzjsl = kzjsl;
    }

    public String getTdksl() {
        return tdksl;
    }

    public void setTdksl(String tdksl) {
        this.tdksl = tdksl;
    }

    public String getTdpw() {
        return tdpw;
    }

    public void setTdpw(String tdpw) {
        this.tdpw = tdpw;
    }

    public String getTdjsl() {
        return tdjsl;
    }

    public void setTdjsl(String tdjsl) {
        this.tdjsl = tdjsl;
    }

    public String getHjksl() {
        return hjksl;
    }

    public void setHjksl(String hjksl) {
        this.hjksl = hjksl;
    }

    public String getHjpw() {
        return hjpw;
    }

    public void setHjpw(String hjpw) {
        this.hjpw = hjpw;
    }

    public String getHjjsl() {
        return hjjsl;
    }

    public void setHjjsl(String hjjsl) {
        this.hjjsl = hjjsl;
    }
}
