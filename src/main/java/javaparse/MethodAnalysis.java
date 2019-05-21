package javaparse;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodAnalysis {
	
	private List<MethodDeclaration> list;
	
	public MethodAnalysis(List<MethodDeclaration> list) {
		this.list = list;
	}
	
	public void printMethods() {
		System.out.println("------ INFO ABOUT METHODS -------");
		for (MethodDeclaration methodDeclaration : list) {
			System.out.println();
		}
	}
	
	
	
	

}
