package command;
import storage.Database;
import io.IOHandler;

/**
 * Команда обновления элемента базы данных по его id
 */
public class UpdateByIdCommand implements Command{
	private Database db;
	private IOHandler ioHandler;

	/**
	 *
	 * @param db БД, элементы которого будут обновляться
	 * @param ioHandler обработчик ввода-вывода
	 */
	public UpdateByIdCommand(Database db, IOHandler ioHandler){
		this.db = db;
		this.ioHandler = ioHandler;
	}
	
	@Override
	public void execute(String [] args) throws InvalidCommandArgumentException{
		long id = 0;
		try{
			id = Long.parseLong(args[0]);
		} catch (NumberFormatException|NullPointerException e){
			throw new InvalidCommandArgumentException("Не удалось считать id группы!");
		}
		if(db.getGroup(id)==null)
			throw new InvalidCommandArgumentException("Группа с указанным id не найдена!");
		db.update(id, ioHandler.readStudyGroup());
	}

	@Override
	public String description() {
		return "обновить значение элемента коллекции, id которого равен заданному";
	}
}