package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class KslxBean {
    @SmartColumn(id = 5, name = "kslxName")
    private String kslxName;
    @SmartColumn(type = ColumnType.ArrayChild)
    private List<KzBean> kzList;

    public KslxBean(String kslxName, List<KzBean> kzList) {
        this.kslxName = kslxName;
        this.kzList = kzList;
    }

    public String getKslxName() {
        return kslxName;
    }

    public void setKslxName(String kslxName) {
        this.kslxName = kslxName;
    }

    public List<KzBean> getKzList() {
        return kzList;
    }

    public void setKzList(List<KzBean> kzList) {
        this.kzList = kzList;
    }
}
