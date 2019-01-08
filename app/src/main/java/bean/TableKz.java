package bean;

import com.bin.david.form.annotation.SmartColumn;

public class TableKz {
    @SmartColumn(id = 2, name = "kzName")
    private String kzName;
    @SmartColumn(id = 3, name = "kcjsl")
    private String kcjsl;
    @SmartColumn(id = 4, name = "ssjsl")
    private String ssjsl;
    @SmartColumn(id = 5, name = "kczjjsl")
    private String kczjjsl;
    @SmartColumn(id = 6, name = "cs")
    private String cs;
    @SmartColumn(id = 7, name = "zjssl")
    private String zjssl;

    public TableKz(String kzName, String kcjsl, String ssjsl, String kczjjsl, String cs, String zjssl) {
        this.kzName = kzName;
        this.kcjsl = kcjsl;
        this.ssjsl = ssjsl;
        this.kczjjsl = kczjjsl;
        this.cs = cs;
        this.zjssl = zjssl;
    }

    public String getKzName() {
        return kzName;
    }

    public void setKzName(String kzName) {
        this.kzName = kzName;
    }

    public String getKcjsl() {
        return kcjsl;
    }

    public void setKcjsl(String kcjsl) {
        this.kcjsl = kcjsl;
    }

    public String getSsjsl() {
        return ssjsl;
    }

    public void setSsjsl(String ssjsl) {
        this.ssjsl = ssjsl;
    }

    public String getKczjjsl() {
        return kczjjsl;
    }

    public void setKczjjsl(String kczjjsl) {
        this.kczjjsl = kczjjsl;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getZjssl() {
        return zjssl;
    }

    public void setZjssl(String zjssl) {
        this.zjssl = zjssl;
    }
}
