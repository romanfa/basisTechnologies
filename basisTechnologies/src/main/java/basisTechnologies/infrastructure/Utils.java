package basisTechnologies.infrastructure;

import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<String> getFileListFromResourceDirectory(String directory) 
    {
		List<String> filesList = new ArrayList<>();
        try {
            // Directory path
            Path dir = Paths.get("src/main/resources/"+directory+"");
            filesList = Files.list(dir)
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filesList;
    }
}
