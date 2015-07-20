package com.sirui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Utils {
	private static StringBuffer sb;
	private static List<String> remove = new ArrayList<String>();
	static{
		remove.add("include");
		remove.add("LinearLayout");
		remove.add("RelativeLayout");
		remove.add("FrameLayout");
		remove.add("AbsoluteLayout");
		remove.add("TableLayout");
	}
	
	public static void createFileds(JTextArea t1, JTextArea t2) {
		sb = new StringBuffer();
		String text = t1.getText();
		readStringXml(text);
		t2.setText(sb.toString());
	}

	public static void initFileds(JTextArea t1, JTextArea t2) {
		//StringBuffer sb = new StringBuffer();
		String text = t2.getText().replace("\n", "");
		String [] lines = text.split(";");
		for(String str : lines){
			String [] s = str.split(" ");
			String r = "this."+s[2] +" = ("+s[1]+") this.findViewById(R.id."+s[2]+");\n";
			sb.append(r);
		}
		t2.setText(sb.toString());
		
	}
	
	public static void readStringXml(String xml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element root = doc.getRootElement();
			readElement(root);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private static void readElement(Element root) {
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			String name = e.getName();
			
			String id = e.attributeValue("id");
			if(id != null && remove.indexOf(name) == -1){
				if (id.startsWith("@+id")) {
					sb.append("private "+name+" "+id.split("/")[1]+";\n");
				}
			}
			if(e.elements().size() != 0){
				readElement(e);
			}
			
		}
	}

}
