package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class ByzyclZylbBean {
    @SmartColumn(id = 6, name = "zylbName")
    private String zylbName;
    @SmartColumn(type = ColumnType.ArrayChild)
    private List<ByzyclKzBean> kzList;

    public ByzyclZylbBean(String zylbName, List<ByzyclKzBean> kzList) {
        this.zylbName = zylbName;
        this.kzList = kzList;
    }

    public String getZylbName() {
        return zylbName;
    }

    public void setZylbName(String zylbName) {
        this.zylbName = zylbName;
    }

    public List<ByzyclKzBean> getKzList() {
        return kzList;
    }

    public void setKzList(List<ByzyclKzBean> kzList) {
        this.kzList = kzList;
    }
}
