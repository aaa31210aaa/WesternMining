package bean;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;

import java.util.List;

public class FzdcszjzycltzBean {
    @SmartColumn(id = 1, name = "zdtj") //中段台阶
    private String zdtj;
    @SmartColumn(id = 2, name = "bz")
    private String bz;
    @SmartColumn(type = ColumnType.ArrayChild)//矿石类型
    private List<FzdKslxBean> fzdKslxList;

    public FzdcszjzycltzBean(String zdtj, String bz, List<FzdKslxBean> fzdKslxList) {
        this.zdtj = zdtj;
        this.bz = bz;
        this.fzdKslxList = fzdKslxList;
    }

    public String getZdtj() {
        return zdtj;
    }

    public void setZdtj(String zdtj) {
        this.zdtj = zdtj;
    }


    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public List<FzdKslxBean> getFzdKslxList() {
        return fzdKslxList;
    }

    public void setFzdKslxList(List<FzdKslxBean> fzdKslxList) {
        this.fzdKslxList = fzdKslxList;
    }
}
