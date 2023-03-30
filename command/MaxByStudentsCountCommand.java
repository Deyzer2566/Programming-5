package command;

import data.StudyGroup;
import io.Writer;
import storage.Database;

/**
 * Команда вывода элемента базы, значение studentCount которого максимально
 */
public class MaxByStudentsCountCommand implements Command{

    private Database db;
    private Writer writter;

    public MaxByStudentsCountCommand(Database db, Writer writter) {
        this.db = db;
        this.writter = writter;
    }

    @Override
    public String description() {
        return "вывести любой объект из коллекции, значение поля studentsCount которого является максимальным";
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        StudyGroup max = db.getCollection().stream()
                .max((o1, o2) -> o1.getStudentsCount()>o2.getStudentsCount()?1:0).get();
        writter.writeln(max.toString());
    }
}
