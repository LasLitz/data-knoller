package de.hpi.isg.dataprep.preparators;

import de.hpi.isg.dataprep.components.Preparation;
import de.hpi.isg.dataprep.components.Preparator;
import de.hpi.isg.dataprep.metadata.DINPhoneNumber;
import de.hpi.isg.dataprep.model.repository.ErrorRepository;
import de.hpi.isg.dataprep.model.target.errorlog.ErrorLog;
import de.hpi.isg.dataprep.model.target.system.AbstractPreparation;
import de.hpi.isg.dataprep.preparators.define.ChangePhoneFormat;
import org.junit.Assert;
import org.junit.Test;

import scala.collection.JavaConversions;
import scala.collection.Seq;
import scala.util.matching.Regex;

import java.util.ArrayList;
import java.util.List;

public class ChangePhoneFormatTest extends PreparatorTest {
    @Test
    public void changeFromSourceToTarget() throws Exception {
        ArrayList<String> sourceGroups = new ArrayList<>(); sourceGroups.add("areaCode"); sourceGroups.add("number"); sourceGroups.add("extensionNumber");
        Seq<String> sourceGroupsSeq = JavaConversions.asScalaBuffer(sourceGroups).toSeq();
        Regex sourceRegex = new Regex("(\\d+)\\D+(\\d+)\\D+(\\d*).*", sourceGroupsSeq);
        DINPhoneNumber sourceFormat = new DINPhoneNumber(false, true, false, true, sourceRegex);

        ArrayList<String> targetGroups = new ArrayList<>(); targetGroups.add("areaCode"); targetGroups.add("number"); targetGroups.add("extensionNumber");
        Seq<String> targetGroupsSeq = JavaConversions.asScalaBuffer(targetGroups).toSeq();
        Regex targetRegex = new Regex("(\\d+) (\\d+)-(\\d+)", targetGroupsSeq);
        DINPhoneNumber targetFormat = new DINPhoneNumber(false, true, false, true, targetRegex);

        Preparator preparator = new ChangePhoneFormat("phone", sourceFormat, targetFormat);

        AbstractPreparation preparation = new Preparation(preparator);
        pipeline.addPreparation(preparation);
        pipeline.executePipeline();

        pipeline.getRawData().show();

        List<ErrorLog> errorLogs = new ArrayList<>();
        ErrorRepository errorRepository = new ErrorRepository(errorLogs);

        Assert.assertEquals(errorRepository, pipeline.getErrorRepository());
    }

    @Test
    public void changeFromSourceToTarget2() throws Exception {
        ArrayList<String> sourceGroups = new ArrayList<>(); sourceGroups.add("areaCode"); sourceGroups.add("number"); sourceGroups.add("extensionNumber");
        Seq<String> sourceGroupsSeq = JavaConversions.asScalaBuffer(sourceGroups).toSeq();
        Regex sourceRegex = new Regex("(\\d+)\\D+(\\d+)\\D+(\\d*).*", sourceGroupsSeq);
        DINPhoneNumber sourceFormat = new DINPhoneNumber(false, true, false, true, sourceRegex);

        ArrayList<String> targetGroups = new ArrayList<>(); targetGroups.add("areaCode"); targetGroups.add("number");
        Seq<String> targetGroupsSeq = JavaConversions.asScalaBuffer(targetGroups).toSeq();
        Regex targetRegex = new Regex("(\\d+) (\\d+)", targetGroupsSeq);
        DINPhoneNumber targetFormat = new DINPhoneNumber(false, true, false, false, targetRegex);

        Preparator preparator = new ChangePhoneFormat("phone", sourceFormat, targetFormat);

        AbstractPreparation preparation = new Preparation(preparator);
        pipeline.addPreparation(preparation);
        pipeline.executePipeline();

        pipeline.getRawData().show();

        List<ErrorLog> errorLogs = new ArrayList<>();
        ErrorRepository errorRepository = new ErrorRepository(errorLogs);

        Assert.assertEquals(errorRepository, pipeline.getErrorRepository());
    }

    @Test
    public void changeToTarget() throws Exception {
        ArrayList<String> targetGroups = new ArrayList<>(); targetGroups.add("areaCode"); targetGroups.add("number"); targetGroups.add("extensionNumber");
        Seq<String> targetGroupsSeq = JavaConversions.asScalaBuffer(targetGroups).toSeq();
        Regex targetRegex = new Regex("(\\d+) (\\d+)-(\\d+)", targetGroupsSeq);
        DINPhoneNumber targetFormat = new DINPhoneNumber(false, true, false, true, targetRegex);

        Preparator preparator = new ChangePhoneFormat("phone", targetFormat);

        AbstractPreparation preparation = new Preparation(preparator);
        pipeline.addPreparation(preparation);
        pipeline.executePipeline();

        pipeline.getRawData().show();

        List<ErrorLog> errorLogs = new ArrayList<>();
        ErrorRepository errorRepository = new ErrorRepository(errorLogs);

        Assert.assertEquals(errorRepository, pipeline.getErrorRepository());
    }

    @Test
    public void changeFromInvalidSourceToTarget() throws Exception {
        ArrayList<String> sourceGroups = new ArrayList<>(); sourceGroups.add("invalid");
        Seq<String> sourceGroupsSeq = JavaConversions.asScalaBuffer(sourceGroups).toSeq();
        Regex sourceRegex = new Regex("(\\d+)", sourceGroupsSeq);
        DINPhoneNumber sourceFormat = new DINPhoneNumber(false, false, false, false, sourceRegex);

        ArrayList<String> targetGroups = new ArrayList<>(); targetGroups.add("areaCode"); targetGroups.add("number"); targetGroups.add("extensionNumber");
        Seq<String> targetGroupsSeq = JavaConversions.asScalaBuffer(targetGroups).toSeq();
        Regex targetRegex = new Regex("(\\d+) (\\d+)-(\\d+)", targetGroupsSeq);
        DINPhoneNumber targetFormat = new DINPhoneNumber(false, true, false, true, targetRegex);

        Preparator preparator = new ChangePhoneFormat("phone", sourceFormat, targetFormat);

        AbstractPreparation preparation = new Preparation(preparator);
        pipeline.addPreparation(preparation);
        pipeline.executePipeline();

        pipeline.getRawData().show();
        
        Assert.assertTrue(pipeline.getErrorRepository().getErrorLogs().isEmpty());
    }
}
