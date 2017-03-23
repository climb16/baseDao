package cn.com.listener;

import java.util.EventObject;

public class DemoEvent extends EventObject {	

	private static final long serialVersionUID = -8352051534142982085L;
	private Object obj;
	private String sName;
	
	public DemoEvent(Object source, String sName) {
		super(source);
		obj = source;
		this.sName = sName;
	}
	
	public Object getSource() {
		return obj;
	}	
	
	public String getName() {
		return sName;
	}
	
	
}
