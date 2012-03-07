package in.zerob13.CodeBrowser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

public class CodeBrowser extends Activity {
	/** Called when the activity is first created. */
	// WebView browser;
	WebView browser;
	private int flag;
	private String mPath;
	private Map<Character, String> transMap=new HashMap<Character, String>();
	Context me;
	String[] cppAliases = { "cpp", "cc", "h", "hpp", "cxx", "hxx", "c", "c++" };
	String[] csharpAliases = { "cs", "c#", "c-sharp", "csharp" };
	String[] cssAliases = { "css" };
	String[] javaAliases = { "jav", "java" };
	String[] phpAliases = { "php", "php4" };
	String[] pythonAliases = { "py", "python" };
	String[] bashAliases = { "sh", "ksh", "csh", "shell","rc" ,"init"};
	String[] sqlAliases = { "4gl", "proc", "sql" };
	String[] vbAliases = { "bas", "frm", "cls", "vbs", "ctl", "vb", "vb.net" };
	String[] xmlAliases = { "asp", "jsp", "aspx", "htt", "htx", "phtml", "wml",
			"rss", "xhtml", "shtml", "dhtml", "dtd", "html", "htm", "xhtml",
			"xml", "xsd", "xsl", "xslt", "config"};
	String[] jsAliases = { "js", "jscript", "javascript"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Arrays.sort(cppAliases);
		Arrays.sort(csharpAliases);
		Arrays.sort(javaAliases);
		Arrays.sort(phpAliases);
		Arrays.sort(pythonAliases);
		Arrays.sort(bashAliases);
		Arrays.sort(sqlAliases);
		Arrays.sort(vbAliases);
		Arrays.sort(xmlAliases);
		Arrays.sort(jsAliases);
		transMap.put(' ', "&nbsp;");
		transMap.put('<', "&lt;");
		transMap.put('&', "&amp;");
		transMap.put('>', "&gt;");
		me = this.getApplicationContext();
		String[] file = me.fileList();
		Arrays.sort(file);
		int res = Arrays.binarySearch(file, "temp.html");
		if (res >= 0) {
			me.deleteFile("temp.html");
		}
		setTitle("CodeBrowser By zerob13(www.zerob13.in)");

		setContentView(R.layout.main);
		browser = (WebView) findViewById(R.id.widget31);
		StringBuffer temp2 = new StringBuffer();
		browser.clearCache(true);
		browser.getSettings().setBuiltInZoomControls(true);
		
		Bundle bundle = this.getIntent().getExtras();
		mPath = bundle.getString("filename");
		for (int i = mPath.length() - 1; i >= 0; i--) {
			if (mPath.charAt(i) != '.') {

				if (mPath.charAt(i) >= 'A' && mPath.charAt(i) <= 'Z') {
					temp2.append(mPath.charAt(i) - 'A' + 'a');
				} else {
					temp2.append(mPath.charAt(i));
				}
			} else 
				break;
		}
		temp2.reverse();
		String aa = temp2.toString().toLowerCase();
		temp2.delete(0, temp2.length());

		try {
			// 构建一个带缓冲的字符型输入流
			String line = null;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(mPath)));

			PrintWriter html = new PrintWriter(
					"/data/data/in.zerob13.CodeBrowser/files/temp.html");
			html.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			html.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"gb2312\" dir=\"ltr\">");
			html.println("<head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>CoderBrowser by zerob13(www.zerob13.in)</title>");
			

			html.println("<script type=\"text/javascript\" src=\"shCore.js\"></script>");
			
			String type="xml";
			if (Arrays.binarySearch(cppAliases, aa)>=0){
				type="cpp";
				html.println("<script type=\"text/javascript\" src=\"cpp.js\"></script>");
			}
			if (Arrays.binarySearch(csharpAliases, aa)>=0){
				type="csharp";
				html.println("<script type=\"text/javascript\" src=\"csharp.js\"></script>");
			}
			if (Arrays.binarySearch(cssAliases, aa)>=0){
				type="css";
				html.println("<script type=\"text/javascript\" src=\"css.js\"></script>");
			}
			if (Arrays.binarySearch(javaAliases, aa)>=0){
				type="java";
				html.println("<script type=\"text/javascript\" src=\"java.js\"></script>");
			}
			if (Arrays.binarySearch(phpAliases, aa)>=0){
				type="php";
				html.println("<script type=\"text/javascript\" src=\"php.js\"></script>");
			}
			if (Arrays.binarySearch(pythonAliases, aa)>=0){
				type="python";
				html.println("<script type=\"text/javascript\" src=\"python.js\"></script>");
			}
			if (Arrays.binarySearch(bashAliases, aa)>=0){
				type="bash";
				html.println("<script type=\"text/javascript\" src=\"bash.js\"></script>");
			}
			if (Arrays.binarySearch(sqlAliases, aa)>=0){
				type="sql";
				html.println("<script type=\"text/javascript\" src=\"sql.js\"></script>");
			}
			if (Arrays.binarySearch(vbAliases, aa)>=0){
				type="vb";
				html.println("<script type=\"text/javascript\" src=\"vb.js\"></script>");
			}
			if (Arrays.binarySearch(xmlAliases, aa)>=0){
				type="xml";
				html.println("<script type=\"text/javascript\" src=\"xml.js\"></script>");
			}
			if(Arrays.binarySearch(jsAliases, aa)>=0){
				type="js";
				html.println("<script type=\"text/javascript\" src=\"js.js\"></script>");
			}
			html.println("<link href=\"shCore.css\" rel=\"stylesheet\" type=\"text/css\" />");
			html.println("<link href=\"shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" />");
			html.println("<script type=\"text/javascript\">");
			html.println(" SyntaxHighlighter.all();");
			html.println("</script>");
			
			html.println("</head>" + "<body>");
				html.println("<pre class=\"brush: "+type+";toolbar: false;\">");
			line = br.readLine();
			while (line != null) {
//				html.println(line);
				temp2.delete(0,temp2.length());
				char chars[] = line.toCharArray();
				for(char c:chars){
					temp2.append(((transMap.get(c) == null) ? c : transMap.get(c)));
				}
				html.println(temp2.toString());
				line = br.readLine();
			}
			// html.println("</script>");
			html.println("</pre>");
		
			html.println("</body>");
			html.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		browser.clearHistory();
		browser.getSettings().setJavaScriptEnabled(true);
		// browser.getSettings().setLoadWithOverviewMode(true);
		browser.loadUrl("file:///data/data/in.zerob13.CodeBrowser/files/temp.html");

		flag = 1;

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		try {
			super.onConfigurationChanged(newConfig);
			if (flag == 1) {
				setTitle("CodeBrowser By zerob13(www.zerob13.in)");
				setContentView(R.layout.main);
				browser = (WebView) findViewById(R.id.widget31);
				browser.getSettings().setBuiltInZoomControls(true);
				browser.getSettings().setJavaScriptEnabled(true);
				browser.loadUrl("file:///data/data/in.zerob13.CodeBrowser/files/temp.html");

			}
		} catch (Exception ex) {
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}