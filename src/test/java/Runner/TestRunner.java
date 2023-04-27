package Runner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import config.ConfigFileReader;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/features/", glue = { "stepdefs" },
tags = "@mobileFeature",
plugin={"pretty", "html:target/HtmlReports.html"
		,"json:target/jsonR"
				+ ""
				+ ""
				+ "eports/report.json"
		,"junit:target/junitReports/report.xml",
})
public class TestRunner 
{
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun = true)

	public void setUpClass() throws Exception 
	{
		ConfigFileReader.ExecutionRoundCount();
		ConfigFileReader.getTargetData();
		
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

	}

//	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
//	public void feature(CucumberFeatureWrapper cucumberFeature) {
//		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
//	}
//
//	@DataProvider(parallel=false)
//	public Object[][] features() {
//		return testNGCucumberRunner.provideFeatures();
//	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	 public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) 
	{
	     testNGCucumberRunner.runScenario(pickle.getPickle());
	 }

	 @DataProvider
	 public Object[][] scenarios() {
		 if(testNGCucumberRunner == null){
			 testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());}
	     return testNGCucumberRunner.provideScenarios();
	 } 
	
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		/*Reporter.loadXMLConfig(new File("extent-config.xml"));
		Reporter.assignAuthor(System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("Windows", "64 bit");
		Reporter.setTestRunnerOutput("Test Case");
		Reporter.setSystemInfo("Selenium", "3.7.0");
	    Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");*/
		testNGCucumberRunner.finish();
		
	}
}