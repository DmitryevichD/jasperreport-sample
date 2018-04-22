package by.dm13y.sketch.jasper.subreport;

import by.dm13y.sketch.jasper.subreport.reportBeans.GeneralReportBean;
import by.dm13y.sketch.jasper.subreport.reportBeans.SubReportBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ReportHelper {
    private static Random rand = new Random();

    public static List<SubReportBean> genSubReportBean(String mailPrefix){
        int count = rand.nextInt() % 40;
        List<SubReportBean> subBeans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String mail = String.format("%s@%d.%s", mailPrefix, rand.nextInt(), ".sd");
            String phone = String.format("ph:%s-%d", mailPrefix, rand.nextInt());
            subBeans.add(new SubReportBean(mail, phone));
        }
        return subBeans;
    }

    public static List<GeneralReportBean> genReportBeans(Integer count){
        Objects.requireNonNull(count);
        List<GeneralReportBean> beans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String personName = String.format("user_%d", i);
            beans.add(new GeneralReportBean(personName, genSubReportBean(personName)));
        }
        return beans;
    }
}
