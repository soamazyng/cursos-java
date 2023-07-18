import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features=".\\src\\test\\resources\\Feature\\aprender_cucumber.feature",
    glue={"StepDefinition"},
    plugin = {
        "pretty",
        "json:target/cucumber-reports/reports.json",
        "junit:target/cucumber-reports/Cucumber.xml",
        "html:target/cucumber-reports/reports2.html"},
    monochrome = true,
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    dryRun = false)
public class Runner {
}
