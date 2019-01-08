package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class FzdbyzycltzBean {
    @SmartColumn(id = 1, name = "zdtj")
    private String zdtj; //中段台阶
    @SmartColumn(id = 2, name = "kt")
    private String kt; //矿体
    @SmartColumn(id = 3, name = "kslx")
    private String kslx; //矿石类型
    @SmartColumn(id = 4, name = "bz")
    private String bz;
    @SmartColumn(type = ColumnType.ArrayChild)//矿石类型
    private List<KslxBean> kslxList;

    public FzdbyzycltzBean(String zdtj, String kt, String kslx, String bz, List<KslxBean> kslxList) {
        this.zdtj = zdtj;
        this.kt = kt;
        this.kslx = kslx;
        this.bz = bz;
        this.kslxList = kslxList;
    }


    public String getZdtj() {
        return zdtj;
    }

    public void setZdtj(String zdtj) {
        this.zdtj = zdtj;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getKslx() {
        return kslx;
    }

    public void setKslx(String kslx) {
        this.kslx = kslx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public List<KslxBean> getKslxList() {
        return kslxList;
    }

    public void setKslxList(List<KslxBean> kslxList) {
        this.kslxList = kslxList;
    }
}
