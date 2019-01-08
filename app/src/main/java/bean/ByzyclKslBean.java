package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class ByzyclKslBean {
    @SmartColumn(id = 5,name = "kslName")
    private String kslName;
    @SmartColumn(type = ColumnType.ArrayChild)
    private List<ByzyclZylbBean> zylbList;

    public ByzyclKslBean(String kslName, List<ByzyclZylbBean> zylbList) {
        this.kslName = kslName;
        this.zylbList = zylbList;
    }

    public String getKslName() {
        return kslName;
    }

    public void setKslName(String kslName) {
        this.kslName = kslName;
    }

    public List<ByzyclZylbBean> getZylbList() {
        return zylbList;
    }

    public void setZylbList(List<ByzyclZylbBean> zylbList) {
        this.zylbList = zylbList;
    }
}
