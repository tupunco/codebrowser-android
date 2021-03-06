﻿package in.zerob13.CodeBrowser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FileChoser extends Activity {
	private File mCurrentDirectory = new File("/");
	ExpBaseAdapter ap;
	ListView lv2;
	Context me;
	String fileEndings[] = { "cpp", "cc", "h", "hpp", "cxx", "hxx", "c", "c++",
			"cs", "c#", "c-sharp", "csharp", "css", "jav", "java", "php",
			"php4", "py", "python", "sh", "ksh", "csh", "shell", "rc", "init",
			"4gl", "proc", "sql", "bas", "frm", "cls", "vbs", "ctl", "vb",
			"vb.net", "asp", "jsp", "aspx", "htt", "htx", "phtml", "wml",
			"rss", "xhtml", "shtml", "dhtml", "dtd", "html", "htm", "xhtml",
			"xml", "xsd", "xsl", "xslt", "config", "js", "jscript",
			"javascript" };

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTitle(R.string.text_selectfile);
		setContentView(R.layout.frm_openfile);
		lv2 = (ListView) findViewById(R.id.of_lv);
		lv2.setCacheColorHint(0x00000000);
		ap = new ExpBaseAdapter(this);
		lv2.setAdapter(ap);

		initFiles();
		ListView.OnItemClickListener lv2click = new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int fid = ap.getItemType((int) id);
				String mPath = "";
				if (fid == 1) {
					String s1 = ap.getItem((int) id).name;
					if (s1.equals("..")) {
						mPath = mCurrentDirectory.getParent();
					} else {
						mPath = mCurrentDirectory.getPath() + "/" + s1 + "/";
					}
					mCurrentDirectory = new File(mPath);
					ListFile(mCurrentDirectory);
				} else {
					Bundle bundle = new Bundle();
					bundle.putString("filename", mCurrentDirectory.getPath()
							+ "/" + ap.getItem((int) id).name);
					Intent mIntent = new Intent();

					mIntent.setClass(FileChoser.this, CodeBrowser.class);
					mIntent.putExtras(bundle);
					startActivity(mIntent);
				}
			}

		};
		ListFile(mCurrentDirectory);
		lv2.setOnItemClickListener(lv2click);
	}

	private void ListFile(File aDirectory) {
		ap.clearItems();
		ap.notifyDataSetChanged();
		lv2.postInvalidate();
		Log.v("vodone", "mpath=" + aDirectory.getPath());

		if (!aDirectory.getPath().equals("/")) {
			fileData fd = new fileData();
			fd.name = "..";
			fd.type = 1;
			ap.addItem(fd);
		}
		for (File f : aDirectory.listFiles()) {
			if (f.isDirectory()) {
				fileData fd = new fileData();
				fd.name = f.getName();
				fd.type = 1;
				ap.addItem(fd);
			} else {
				if (checkEnds(f.getName().toLowerCase())) {
					fileData fd = new fileData();
					fd.name = f.getName();
					fd.type = 0;
					ap.addItem(fd);
				}
			}
		}
		ap.notifyDataSetChanged();
		lv2.postInvalidate();
	}

	private boolean checkEnds(String checkItsEnd) {
		for (String aEnd : fileEndings) {
			if (checkItsEnd.endsWith(aEnd))
				return true;
		}
		return false;

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mCurrentDirectory.getParent() == null) {
				return super.onKeyDown(keyCode, event);
			} else {
				mCurrentDirectory = new File(mCurrentDirectory.getParent());

				ListFile(mCurrentDirectory);

			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.exit(0);
		// 或者下面这种方式
		// android.os.Process.killProcess(android.os.Process.myPid());
	}

	void initFiles() {
		me = this.getApplicationContext();
		String[] file = me.fileList();
		Arrays.sort(file);
		int res = Arrays.binarySearch(file, "shCore.js");
		if (res < 0) {
			// build the file
			try {
				FileOutputStream fout = me.openFileOutput("shCore.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.shcorejs);

				int temp;
				temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}

				in.close();
				fout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		res = Arrays.binarySearch(file, "shThemeDefault.css");
		if (res < 0) {
			// build the file
			try {
				FileOutputStream fout = me.openFileOutput("shThemeDefault.css",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.shthemedefault);

				int temp;
				temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}

				in.close();
				fout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		res = Arrays.binarySearch(file, "shCore.css");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("shCore.css",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.shcorecss);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "bash.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("bash.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.bash);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		res = Arrays.binarySearch(file, "cpp.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("cpp.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.cpp);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "csharp.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("csharp.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.csharp);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "css.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("css.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.css);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "java.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("java.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.java);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "js.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("js.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.js);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "php.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("php.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.php);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "python.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("python.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.python);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "sql.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("sql.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.sql);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "vb.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("vb.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.vb);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		res = Arrays.binarySearch(file, "xml.js");
		if (res < 0) {
			try {
				FileOutputStream fout = me.openFileOutput("xml.js",
						Context.MODE_WORLD_WRITEABLE);
				InputStream in = null;
				in = this.getResources().openRawResource(R.raw.xml);
				int temp = in.read();
				while (temp != -1) {
					fout.write(temp);
					temp = in.read();
				}
				in.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
