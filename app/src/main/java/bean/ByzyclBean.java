package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class ByzyclBean {
    @SmartColumn(id = 1,name = "kqmc")
    private String kqmc;
    @SmartColumn(id = 2,name = "tbdw")
    private String tbdw;
    @SmartColumn(id = 3,name = "jzrq")
    private String jzrq;
    @SmartColumn(type = ColumnType.ArrayChild)//矿石类型
    private List<ByzyclKslxBean> kslxList;

    public ByzyclBean(String kqmc, String tbdw, String jzrq, List<ByzyclKslxBean> kslxList) {
        this.kqmc = kqmc;
        this.tbdw = tbdw;
        this.jzrq = jzrq;
        this.kslxList = kslxList;
    }

    public String getKqmc() {
        return kqmc;
    }

    public void setKqmc(String kqmc) {
        this.kqmc = kqmc;
    }

    public String getTbdw() {
        return tbdw;
    }

    public void setTbdw(String tbdw) {
        this.tbdw = tbdw;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public List<ByzyclKslxBean> getKslxList() {
        return kslxList;
    }

    public void setKslxList(List<ByzyclKslxBean> kslxList) {
        this.kslxList = kslxList;
    }
}
