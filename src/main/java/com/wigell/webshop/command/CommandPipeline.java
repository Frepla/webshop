package com.wigell.webshop.command;

import java.util.ArrayList;
import java.util.List;

import com.wigell.webshop.model.observer.ProductUpdateNotifier;

public class CommandPipeline {
    private final List<Command> commands;
    private final ProductUpdateNotifier productUpdateNotifier;

    public CommandPipeline() {
        this.commands = new ArrayList<>();
        this.productUpdateNotifier = ProductUpdateNotifier.getInstance();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    private void sleepWithHandling() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void execute() {
        System.out.println("Executing command pipeline to finish your product(s)...");

        for (Command command : commands) {
            command.execute();
            productUpdateNotifier.productReady(command.getProduct().getName());
            sleepWithHandling();
        }
    }
}
