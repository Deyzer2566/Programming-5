package command;

import data.StudyGroup;
import io.Writer;
import storage.Database;

import java.util.LinkedList;

/**
 * Команда вывода уникальных значений админов групп
 */
public class PrintUniqueGroupAdminCommand implements Command{

    private Database db;
    private Writer writter;

    public PrintUniqueGroupAdminCommand(Database db, Writer writter) {
        this.db = db;
        this.writter = writter;
    }

    @Override
    public String description() {
        return "вывести уникальные значения поля groupAdmin всех элементов в коллекции";
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        LinkedList<String> uniqueNames = new LinkedList<>();
        for(StudyGroup group: db.getCollection()){
            if(!uniqueNames.contains(group.getGroupAdmin().getName())){
                uniqueNames.add(group.getGroupAdmin().getName());
                writter.writeln(group.getGroupAdmin().getName());
            }
        }
    }
}
