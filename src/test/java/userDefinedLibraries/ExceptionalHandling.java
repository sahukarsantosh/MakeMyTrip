package userDefinedLibraries;

@SuppressWarnings("serial")
public class ExceptionalHandling extends Exception{
	public void FileNotFoundException(Exception error) {
		error.printStackTrace();
	}
}
