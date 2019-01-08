package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class ByzyclKslxBean {
    @SmartColumn(id = 4,name = "kslxName")
    private String kslxName;
    @SmartColumn(type = ColumnType.ArrayChild)//矿石类型
    private List<ByzyclKslBean> kslList;

    public ByzyclKslxBean(String kslxName, List<ByzyclKslBean> kslList) {
        this.kslxName = kslxName;
        this.kslList = kslList;
    }

    public String getKslxName() {
        return kslxName;
    }

    public void setKslxName(String kslxName) {
        this.kslxName = kslxName;
    }

    public List<ByzyclKslBean> getKslList() {
        return kslList;
    }

    public void setKslList(List<ByzyclKslBean> kslList) {
        this.kslList = kslList;
    }
}
