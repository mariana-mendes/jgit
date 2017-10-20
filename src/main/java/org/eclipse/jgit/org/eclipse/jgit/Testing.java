package org.eclipse.jgit.org.eclipse.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class Testing {
	


	public void criaRepo() {
		try {
			Repository  repository = new FileRepositoryBuilder().findGitDir(new File("/home/marianams/projeto_si")).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	};
	
	
	  static void revWalk() throws Exception {
	        Repository repository;
	        RevWalk revWalk;
	        ObjectId commitId;
	        Git git = Git.open(new File("/home/marianams/projeto_si")); 
	        repository = git.getRepository();
	    
	        revWalk = new RevWalk(repository);
	        commitId = repository.resolve("refs/heads/master");
	       

	        revWalk.markStart( revWalk.parseCommit( commitId ) );
	        for( RevCommit commit : revWalk ) {
	          System.out.println( commit.getFullMessage() );
	        }
	    }
};
