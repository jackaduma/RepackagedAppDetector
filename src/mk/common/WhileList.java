package mk.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kun
 *
 */
public class WhileList {
	public static List<String> whiteList= new ArrayList<String>();
	static {
		whiteList.add("com.google.");
		whiteList.add("com.android.");
	}

}
