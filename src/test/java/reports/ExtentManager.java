package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("test-output/extent-report.html");

            reporter.config().setReportName("API Automation Report");
            reporter.config().setDocumentTitle("Rest Assured Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}