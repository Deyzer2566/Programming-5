package command;

import io.Writer;

import java.util.Map;

/**
 * Команда, выводящая справку по командам
 */
public class HelpCommand implements Command{
    private final CommandHandler commandHandler;
    Writer writter;

    public HelpCommand(CommandHandler commandHandler, Writer writter){
        this.commandHandler=commandHandler;
		this.writter = writter;
    }

    @Override
    public void execute(String [] args) {
        for(Map.Entry<String, Command> command : commandHandler.getCommands().entrySet())
            writter.writeln(command.getKey() +": "+ command.getValue().description());
    }

    @Override
    public String description() {
        return "вывести справку по доступным командам";
    }
}
