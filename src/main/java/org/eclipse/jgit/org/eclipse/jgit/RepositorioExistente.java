package org.eclipse.jgit.org.eclipse.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class RepositorioExistente {
	
	public String contributor1 = "joao.carvalho@ccc.ufcg.edu.br";

	public Repository openRepository(String directory) {
		Repository repository;	
		try {
			 Git git = Git.open(new File(directory)); 
			 repository = git.getRepository();
			 return repository;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	};
	
	  public void revWalk(Repository repository, String branch) throws Exception {
	        RevWalk revWalk;
	        ObjectId commitId;
	       
	        revWalk = new RevWalk(repository);
	        commitId = repository.resolve(branch);
	        revWalk.markStart( revWalk.parseCommit( commitId ) );
	        for( RevCommit commit : revWalk ) {
	          System.out.println( "Author: "+commit.getAuthorIdent().getEmailAddress() + ". Message: " + commit.getFullMessage() );
	        }
	    } 
	  
	  
	  public void gitBlame(Repository repository) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException, GitAPIException {
		  final int teste = 3;
		  
		  BlameCommand blame = new BlameCommand(repository);
		  ObjectId commitID = repository.resolve("HEAD");
		  blame.setStartCommit(commitID);
		  blame.setFilePath("src/album/Album.java");
		  BlameResult blameResult = blame.call();
		  System.out.println(blameResult);
		  System.out.println(blame.getRepository());
		
		  for (int i = 0; i < teste; i++) {
			  RevCommit commit = blameResult.getSourceCommit(i);
			  System.out.println("Line: " + i + ": " + commit.getAuthorIdent().getName());
		}
		  
		  
	  }
};
