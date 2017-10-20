package org.eclipse.jgit.org.eclipse.jgit;

/**
 * Hello world!
 *
 */
public class App {
	
	static Testing t = new Testing();
	
    public static void main( String[] args )
    {
    	t.criaRepo();
    	try {
			t.revWalk();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println( "Hello World!" );
    }
}
