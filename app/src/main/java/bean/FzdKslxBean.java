package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class FzdKslxBean {
    @SmartColumn(id = 4, name = "FzdKslxName")
    private String FzdKslxName;
    @SmartColumn(type = ColumnType.ArrayChild)
    private List<CllbBean> cllbList;

    public FzdKslxBean(String FzdKslxName, List<CllbBean> cllbList) {
        this.FzdKslxName = FzdKslxName;
        this.cllbList = cllbList;
    }

    public String getFzdKslxName() {
        return FzdKslxName;
    }

    public void setFzdKslxName(String fzdKslxName) {
        FzdKslxName = fzdKslxName;
    }

    public List<CllbBean> getCllbList() {
        return cllbList;
    }

    public void setCllbList(List<CllbBean> cllbList) {
        this.cllbList = cllbList;
    }
}
