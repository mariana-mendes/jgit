package jgit;

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
	        RevWalk revWalk   = new RevWalk(repository);
	        ObjectId commitId;
	     
	        commitId = repository.resolve(branch);
	        revWalk.markStart( revWalk.parseCommit( commitId ) );
	        for( RevCommit commit : revWalk ) {
	          System.out.println( "Author: "+commit.getAuthorIdent().getEmailAddress() + ". Message: " + commit.getFullMessage() );
	        }
	    } 
	  
	  
	  public void gitBlame(Repository repository) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException, GitAPIException {

		  /*
		   * '~' especifica qual HEAD vc ta acessando 
		   * por exemplo HEAD é a atual, HEAD~ eh uma anterior e assim por diante
		   *
		   *O que importa é o ultimo estado do arquivo e quem fez essas alterações
		   *que no caso são as alterações 'fixas' e quantas alterações a pessoa fez na classe
		   *
		   * ~ ~~problema: Se a ultima alteração do arquivo foi de linhas (posicionamento do codigo)
		   * 
		   */  
		  BlameCommand blame = new BlameCommand(repository);
		  ObjectId commitID = repository.resolve("HEAD");
		  blame.setStartCommit(commitID);
		  blame.setFilePath("src/album/Album.java");
		  BlameResult blameResult = blame.call();
		  System.out.println(blameResult.getResultContents().size());
		  System.out.println(blame.getRepository());
		  
		  // numero de linhas do arquivo  
		  int lines = blameResult.getResultContents().size();
		  
		 /*  Aqui da pra pegar a linha do arquivo 
		  *  System.out.println(blameResult.getResultContents().getString(2)); 
		  */
		
		  for (int i = 0; i < lines; i++) {
			  RevCommit commit = blameResult.getSourceCommit(i);
			  System.out.println("Line: " + i + " - Author:  " + commit.getAuthorIdent().getName() + " ---- code: " + blameResult.getResultContents().getString(i));

		  }
		 
	}
	  

  
	  
	  
	  
	 
};
