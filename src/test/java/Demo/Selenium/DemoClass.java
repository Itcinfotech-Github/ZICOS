package Demo.Selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DemoClass {
	
	@Test
	public void method1() throws IOException {
		
		/*StringBuilder builder = new StringBuilder();
		builder.append("<html><head><title>Test Result</title></head>");
		builder.append("<body>");
		builder.append("Hi Page");
		builder.append("</body>");
		StringBuffer builder = new StringBuffer();
		builder.append("<html><head><title>Test Result</title></head>");
		builder.append("<body>");
		builder.append("Test Results");
		builder.append("<table border=\"1\" bordercolor=\"Blue\">");
		builder.append("<tr><td><b>TestCases</b></td><td><b>Passed</b></td><td><b>Failed</b></td><td><b>Skipped</b></td></tr>");
		builder.append("<tr><td>245</td><td>220</td><td>15</td><td>10</td></tr>");
		builder.append("<tr><td><a href='http://www.google.com'>Google</a></td><td><a href='http://www.flipkart.com'>Flipkart</a></td><td><a href='http://www.twitter.com'>Twitter</a></td><td><a href='http://www.facebook.com'>Facebook</a></td></tr>");
		builder.append("</table>");
		builder.append("<img src=\"C:\\Users\\22706\\Desktop\\EclipseError.png\">");
		builder.append("</body></html>");
		File file = new File("C:\\ReportFiles\\first.html");
		OutputStream output = new FileOutputStream(file);
		Writer writer = new OutputStreamWriter(output);
		writer.write(builder.toString());
		writer.close();
		FileOutputStream out = new FileOutputStream(file);
		Writer write = new OutputStreamWriter(out);
		write.write(builder.toString());
		write.close();*/
		
		WebDriver driver = new FirefoxDriver();
	}

}
