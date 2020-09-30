package pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		//args[0] = nombre imagen
		if(args.length < 0)
			System.out.println("Sin nombre de archivo no se puede proceder");
		else {
			String origen = args[0];
			FileInputStream fis = new FileInputStream(origen);
			
			String[] splitted = origen.split("\\.");
			String outName = "";
			if(splitted.length < 2){
				//archivo sin extension
			}
			else if(splitted.length > 2) {
				outName += splitted[0];
				for (int i = 1; i < splitted.length - 1; i++) {
					outName += "." + splitted[i];
				}
				outName += "_CPY." + splitted[splitted.length - 1];
			}
			else {
				outName += splitted[0] + "_CPY." + splitted[1];
			}
			
			FileOutputStream fos = new FileOutputStream(outName);
			
			int actual = fis.read();
			while(actual != -1) {
				fos.write(actual);
				actual = fis.read();
			}
			fos.close();
			fis.close();
		}
	
	}
}
