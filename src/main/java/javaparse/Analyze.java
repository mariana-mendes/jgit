package javaparse;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;
import com.google.common.base.Strings;

import util.DirExplorer;


public class Analyze {
	
	CompilationUnit compilationUnit = JavaParser.parse("class Aluno { }");
	
	String dir = "/home/mariana/Documents/jgit-and-javaparser/src/main/java/codeExample";
    Path path = FileSystems.getDefault().getPath(dir);
	

    ParserConfiguration pc = new ParserConfiguration();
	
	public List<CompilationUnit> analise() throws IOException {
		
		SourceRoot sourceRoot = new SourceRoot(path);
		sourceRoot.setParserConfiguration(pc);
		List<ParseResult<CompilationUnit>> parseResults = sourceRoot.tryToParse("");

		// Now get all compilation unitsList 
		List<CompilationUnit> allCus = parseResults.stream()        
		        .filter(ParseResult::isSuccessful)        
		        .map(r -> r.getResult().get())        
		        .collect(Collectors.toList());
		return allCus;
		
	} 
	
	public void getContentType() {
		this.compilationUnit.getNodesByType(FieldDeclaration.class).stream().
	    filter(f -> f.getModifiers().contains("PUBLIC") && 
	            !f.getModifiers().contains("STATIC")).
	    forEach(f -> System.out.println("Check field at line " + f.getBegin().get().line));
	}

  public static void listClasses(File projectDir) throws ParseException {
	        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
	            System.out.println(path);
	            System.out.println(Strings.repeat("=", path.length()));
	            try {
	                new VoidVisitorAdapter<Object>() {
	                    @Override
	                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
	                        super.visit(n, arg);
	                        System.out.println(" * " + n.getName());
	                    }
	                }.visit(JavaParser.parse(file), null);
	                System.out.println(); // empty line
	            } catch (IOException e) {
	                new RuntimeException(e);
	            }
	        }).explore(projectDir);
	    }

	
	}
	

