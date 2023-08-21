package exceptions;

public class FileNotFindedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FileNotFindedException(String msg) {
		super(msg);
	}

}
