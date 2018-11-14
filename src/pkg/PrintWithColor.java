package pkg;

public class PrintWithColor {

    /**
     * Bright Black: \u001b[30;1m
     * Bright Red: \u001b[31;1m
     * Bright Green: \u001b[32;1m
     * Bright Yellow: \u001b[33;1m
     * Bright Blue: \u001b[34;1m
     * Bright Magenta: \u001b[35;1m
     * Bright Cyan: \u001b[36;1m
     * Bright White: \u001b[37;1m
     */
    private static final String ANSI_RESET = "\u001B[0m"; //used in all sysouts
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String BRIGHT_BLACK = "\u001b[30;1m";
    private static final String BRIGHT_RED = "\u001b[31;1m";
    private static final String BRIGHT_GREEN = "\u001b[32;1m";
    private static final String BRIGHT_YELLOW = "\u001b[33;1m";
    private static final String BRIGHT_BLUE = "\u001b[34;1m";
    private static final String BRIGHT_MAGENTA = "\u001b[35;1m";
    private static final String BRIGHT_CYAN = "\u001b[36;1m";
    private static final String BRIGHT_WHITE = "\u001b[37;1m";

    //experimentals



    static void black (String input){
        System.out.println(ANSI_BLACK + input+ ANSI_RESET);
    }

    static void red (String input){
        System.out.println(ANSI_RED + input+ ANSI_RESET);
    }

    static void green (String input){
        System.out.println(ANSI_GREEN + input+ ANSI_RESET);
    }

    static void yellow (String input){
        System.out.println(ANSI_YELLOW + input+ ANSI_RESET);
    }

    static void blue (String input){
        System.out.println(ANSI_BLUE + input+ ANSI_RESET);
    }

    static void purple (String input){
        System.out.println(ANSI_PURPLE + input+ ANSI_RESET);
    }

    static void cyan (String input){
        System.out.println(ANSI_CYAN + input+ ANSI_RESET);
    }

    static void white (String input){
        System.out.println(ANSI_WHITE + input+ ANSI_RESET);
    }

    static void brightBlack (String input){
        System.out.println(BRIGHT_BLACK + input+ ANSI_RESET);
    }

    static void brightRed (String input){
        System.out.println(BRIGHT_RED + input+ ANSI_RESET);
    }

    static void brightGreen (String input){
        System.out.println(BRIGHT_GREEN + input+ ANSI_RESET);
    }

    static void brightYellow (String input){
        System.out.println(BRIGHT_YELLOW + input+ ANSI_RESET);
    }

    static void brightBlue (String input){
        System.out.println(BRIGHT_BLUE + input+ ANSI_RESET);
    }

    static void brightMagenta (String input){
        System.out.println(BRIGHT_MAGENTA + input + ANSI_RESET);
    }

    static void brightCyan (String input){
        System.out.println(BRIGHT_CYAN + input+ ANSI_RESET);
    }

    static void brightWhite (String input){
        System.out.println(BRIGHT_WHITE + input+ ANSI_RESET);
    }

}

