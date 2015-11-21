package mop;

import java.io.IOException;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class IGD {

	private static String SPLIT = " ";

	public void setSplit(String split) {
		SPLIT = split;
	}

	public static List<double[]> loadPfront(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String s ;
		String[] ss;
		List<double[]> ps = new ArrayList<double[]>(1500);
		while( null != (s = br.readLine()) ) {
			ss = s.split(SPLIT);
			double[] d = new double[ss.length];
			for(int i = 0 ; i < ss.length; i ++) {
				d[i] = Double.parseDouble(ss[i]);
			}
			ps.add(d);
		}
		br.close();
		fr.close();
		return ps;
	}
}
