package command;

import data.StudyGroup;
import io.Writer;
import storage.Database;

import java.util.LinkedList;

/**
 * Команда для вывода всех элементов базы данных, отсортированных по возрастанию поля expelledStudents
 */
public class PrintFieldAscendingExpelledStudentsCommand implements Command{

    private Database db;
    private Writer writter;

    public PrintFieldAscendingExpelledStudentsCommand(Database db, Writer writter) {
        this.db = db;
        this.writter = writter;
    }

    @Override
    public String description() {
        return "вывести значения поля expelledStudents всех элементов в порядке возрастания";
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        LinkedList<Long> expelledStudents = new LinkedList<>();
        for(StudyGroup group: db.getCollection()){
            expelledStudents.add(group.getExpelledStudents());
        }
        expelledStudents.sort((o1, o2) -> o1.compareTo(o2));
        for(Long l:expelledStudents){
            writter.writeln(l.toString());
        }
    }
}
