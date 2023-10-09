package quynh.java.webapp.pm.util.plantuml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

public class DiagramUtil {
	public static boolean createImage(String diagramText, String folderPath, String fileName) {
		boolean result = true;
		File f = new File(folderPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		File file = new File(folderPath + fileName);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String source = diagramText;
		SourceStringReader reader = new SourceStringReader(source);
		try {
			DiagramDescription des = reader.outputImage(outputStream);
			//String desc = reader.outputImage(outputStream).getDescription();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getInitText(String type) {
		switch (type) {
			case "ERD":
				return "@startuml  @enduml";
			case "CLASS":
				return "@startuml  @enduml";
			case "ACTIVITY":
				return "@startuml  @enduml";
			case "SEQUENCE":
				return "@startuml  @enduml";
			case "WIREFRAME":
				return "@startsalt {  } @endsalt";
			case "USECASE":
				return "@startuml  @enduml";
			case "WBS":
				return "";
			case "GANTT":
				return "";		
			default: 
				return "";
		}
	}
}
