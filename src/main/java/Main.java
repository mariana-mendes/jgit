import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import javaparse.Analyze;

public class Main {
	

	private static Class aluno;

	public static void main(String[] args) throws IOException, ParseException {
		Analyze a = new Analyze();
		
		List<CompilationUnit> compU = a.analise();
		
		for (Iterator iterator = compU.iterator(); iterator.hasNext();) {
			CompilationUnit compilationUnit = (CompilationUnit) iterator.next();
			//System.out.println(compilationUnit);
		}
		
		a.getContentType();
		
	    File projectDir = new File("/home/marianamendes/jgit-and-javaparser/src/main/java/codeExample");
		a.listClasses(projectDir);
		
		
	}
}
