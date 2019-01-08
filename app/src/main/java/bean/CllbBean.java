package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class CllbBean {
    @SmartColumn(id = 3, name = "cllb_name")
    private String cllb_name;
    @SmartColumn(id = 4, name = "111b")
    private String yyyb;
    @SmartColumn(id = 5, name = "333")
    private String sss;
    @SmartColumn(id = 6, name = "122b")
    private String yeeb;
    @SmartColumn(type = ColumnType.ArrayChild)
    private List<KzBean> kzList;

    public CllbBean(String cllb_name, List<KzBean> kzList) {
        this.cllb_name = cllb_name;
        this.kzList = kzList;
    }

    public String getCllb_name() {
        return cllb_name;
    }

    public void setCllb_name(String cllb_name) {
        this.cllb_name = cllb_name;
    }

    public String getYyyb() {
        return yyyb;
    }

    public void setYyyb(String yyyb) {
        this.yyyb = yyyb;
    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }

    public String getYeeb() {
        return yeeb;
    }

    public void setYeeb(String yeeb) {
        this.yeeb = yeeb;
    }

    public List<KzBean> getKzList() {
        return kzList;
    }

    public void setKzList(List<KzBean> kzList) {
        this.kzList = kzList;
    }
}
