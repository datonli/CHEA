package mop;

import utilities.WrongRemindException;

public interface DataOperator {
	public String mop2Str();
	public void str2Mop(String popStr);
	public boolean write2FileTime(String filename,String str,int writeTime) throws IOException;
}
