package command;
import storage.Database;
import io.Writer;
import data.StudyGroup;

/**
 * Команда вывода всех групп базы
 */
public class ShowCommand implements Command{
	private Database db;
	private Writer writter;
	
	public ShowCommand(Database db, Writer writter){
		this.db = db;
		this.writter = writter;
	}
	
	/**
     * выводит информацию о коллекции
     */
	private void show(){
		for(StudyGroup group: db.getCollection()) {
			writter.writeln(group.toString());
		}
	}

	@Override
	public void execute(String [] args){
		show();
	}

	@Override
	public String description() {
		return "вывести все элементы коллекции в строковом представлении";
	}
}