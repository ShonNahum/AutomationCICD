package shonautomations.resources;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) { // test fail come also to this block amd ask (do i need to retry the test again?)
		if(count<maxTry) {
			count++;
			return true; // by saying true it will continue retry
		}
		return false;
	}

}
