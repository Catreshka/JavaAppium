package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        GetStartedTest.class,
        SearchTests.class
})

public class TestSuiteIOS {
}
