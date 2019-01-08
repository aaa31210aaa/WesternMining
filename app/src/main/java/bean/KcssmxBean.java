package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class KcssmxBean {
    @SmartColumn(id = 1, name = "ksmc") //矿山名称
    private String ksmc;
    @SmartColumn(type = ColumnType.ArrayChild)//矿种
    private List<TableKz> tableKzList;

    public KcssmxBean(String ksmc, List<TableKz> tableKzList) {
        this.ksmc = ksmc;
        this.tableKzList = tableKzList;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public List<TableKz> getTableKzList() {
        return tableKzList;
    }

    public void setTableKzList(List<TableKz> tableKzList) {
        this.tableKzList = tableKzList;
    }
}
