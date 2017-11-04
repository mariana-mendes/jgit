package org.eclipse.jgit.org.eclipse.jgit;

import org.eclipse.jgit.lib.Repository;

/**
 * Hello world!
 *
 */
public class ListGit {
	

	
	static RepositorioExistente t = new RepositorioExistente();
	
    public static void main( String[] args )  {
    	String directory = "/home/mariana/homemade-dynamite";
    	String branch = "refs/heads/master";
    
    	
    	try {
    		Repository repository = t.openRepository(directory);
    		t.revWalk(repository, branch);
    		t.gitBlame(repository);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
