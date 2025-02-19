package web.controller.commands;

import web.controller.commands.actions.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {

        commands.put(CommandName.DO_AUTH, new DoAuth());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.GO_TO_LOGIN, new GoToLogin());
        commands.put(CommandName.GO_TO_HOME, new GoToHome());

        // News
        commands.put(CommandName.GET_NEWS, new GetNews());
        commands.put(CommandName.ADD_NEWS, new AddNews());
        commands.put(CommandName.DELETE_NEWS, new DeleteNews());
        commands.put(CommandName.UPDATE_NEWS, new UpdateNews());

    }

    public Command getCommand(String userCommand) {
        CommandName commandName;
        Command command;

        commandName = CommandName.valueOf(userCommand.toUpperCase());
        command = commands.get(commandName);
        return command;
    }
}
