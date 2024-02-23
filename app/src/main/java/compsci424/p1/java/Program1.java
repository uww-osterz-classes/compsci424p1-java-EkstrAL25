/* COMPSCI 424 Program 1
 * Name: ALek Ekstrand
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> commands = new LinkedList<>();

        // 1. Ask the user to enter commands of the form "create N",
        //    "destroy N", or "end", where N is an integer between 0 
        //    and 15.
        System.out.println("Enter commands of the form 'create N', 'destroy N', or 'end', where N is an integer between 0 and 15:");

        // 2. While the user has not typed "end", continue accepting
        //    commands. Add each command to a list of actions to take 
        //    while you run the simulation.
        // 3. When the user types "end" (or optionally any word that 
        //    is not "create" or "destroy"), stop accepting commands 
        //    and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("end")) {
                break;
            }
            commands.add(command);
        }

        // 4. Create an object of the Version 1 class and an object of
        //    the Version 2 class.
        Version1 version1 = new Version1(16);
        Version2 version2 = new Version2(16);

        // 5. Run the command sequence once with the Version 1 object, 
        //    calling its showProcessTree method after each command to
        //    show the changes in the tree after each command.
        System.out.println("Version 1:");
        for (String command : commands) {
            String[] parts = command.split(" ");
            if (parts[0].equals("create")) {
                int parentPid = Integer.parseInt(parts[1]);
                if (version1.create(parentPid) == 0) {
                    version1.showProcessInfo();
                }
            } else if (parts[0].equals("destroy")) {
                int targetPid = Integer.parseInt(parts[1]);
                if (version1.destroy(targetPid) == 0) {
                    version1.showProcessInfo();
                }
            }
        }

        // 6. Repeat step 5, but with the Version 2 object.
        System.out.println("Version 2:");
        for (String command : commands) {
            String[] parts = command.split(" ");
            if (parts[0].equals("create")) {
                int parentPid = Integer.parseInt(parts[1]);
                if (version2.create(parentPid) == 0) {
                    version2.showProcessInfo();
                }
            } else if (parts[0].equals("destroy")) {
                int targetPid = Integer.parseInt(parts[1]);
                if (version2.destroy(targetPid) == 0) {
                    version2.showProcessInfo();
                }
            }
        }

        // 7. Store the current system time in a variable

        // ... then run the command sequence 200 times with Version 1.

        // ... After this, store the new current system time in a
        //     second variable. Subtract the start time from the end 
        //     time to get the Version 1 running time, then display 
        //     the Version 1 running time.

        long startTimeV1 = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            //System.out.println("Version 1 run " + i);
            Version1 version1Multi = new Version1(16);

            for (String command : commands) {
                String[] parts = command.split(" ");
                if (parts[0].equals("create")) {
                    int parentPid = Integer.parseInt(parts[1]);
                    version1Multi.create(parentPid);
                } else if (parts[0].equals("destroy")) {
                    int targetPid = Integer.parseInt(parts[1]);
                    version1Multi.destroy(targetPid);
                }
            }
        }
        long endTimeV1 = System.nanoTime();
        long runningTimeV1 = endTimeV1 - startTimeV1;
        System.out.println("Version 1 running time: " + runningTimeV1 + " nanoseconds");


        // 8. Repeat step 7, but with the Version 2 object.

        long startTimeV2 = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            //System.out.println("Version 2 run " + i);
            Version2 version2Multi = new Version2(16);

            for (String command : commands) {
                String[] parts = command.split(" ");
                if (parts[0].equals("create")) {
                    int parentPid = Integer.parseInt(parts[1]);
                    version2Multi.create(parentPid);
                } else if (parts[0].equals("destroy")) {
                    int targetPid = Integer.parseInt(parts[1]);
                    version2Multi.destroy(targetPid);
                }
            }
        }
        long endTimeV2 = System.nanoTime();
        long runningTimeV2 = endTimeV2 - startTimeV2;
        System.out.println("Version 2 running time: " + runningTimeV2 + " nanoseconds");

        scanner.close();
    }
}
