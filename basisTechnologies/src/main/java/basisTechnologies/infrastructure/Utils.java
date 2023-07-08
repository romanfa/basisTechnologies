package basisTechnologies.infrastructure;

import java.io.File;
import java.net.URL;

public class Utils {
 
	
	public void changeFocusWindowDriver() {
		
		String currentTab = WebDriverConf.driver.getWindowHandle();
		for (String tab : WebDriverConf.driver.getWindowHandles()) {
		    if (!tab.equals(currentTab)) {
		    	WebDriverConf.driver.switchTo().window(tab); 
		    }       
		}
		
	}
	
	public File getResourceFile(String fileName) 
    {
        URL url = this.getClass()
            .getClassLoader()
            .getResource(fileName);
        
        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }
        
        File file = new File(url.getFile());
        
        return file;
    }
}
