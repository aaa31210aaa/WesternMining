package bean;

import com.bin.david.form.annotation.SmartColumn;

public class ByzyclKzBean {
    @SmartColumn(id = 7, name = "kzName")
    private String kzName;
    @SmartColumn(id = 8, name = "hj")
    private String hj;

    public ByzyclKzBean(String kzName, String hj) {
        this.kzName = kzName;
        this.hj = hj;
    }

    public String getKzName() {
        return kzName;
    }

    public void setKzName(String kzName) {
        this.kzName = kzName;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }
}
