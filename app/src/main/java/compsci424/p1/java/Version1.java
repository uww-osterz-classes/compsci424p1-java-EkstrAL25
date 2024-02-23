/* COMPSCI 424 Program 1
 * Name: Alek Ekstrand
 */
package compsci424.p1.java;
import java.util.LinkedList;

/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
    private Version1PCB[] pcbArray;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1(int n) {
        pcbArray = new Version1PCB[n];
        for (int i = 0; i < n; i++) {
            pcbArray[i] = new Version1PCB();
        }
        // System.out.print(pcbArray.toString());
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects
        int newPid = findFreePid();
        if (newPid == -1) {
            System.out.println("No free PCBs available.");
            return -1;
        }

        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children
        pcbArray[newPid].parent = parentPid;
        if (parentPid >= 0 && parentPid < pcbArray.length)
            pcbArray[parentPid].children.add(newPid);

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    int destroy (int targetPid) {
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt

         if (targetPid < 0 || targetPid >= pcbArray.length) {
            System.out.println("Invalid process ID.");
            return -1;
        }

         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)

         LinkedList<Integer> descendants = new LinkedList<>();
        collectDescendants(targetPid, descendants);
        for (int pid : descendants) {
            pcbArray[pid] = null;
        }

         // 2. Remove targetPid from its parent's list of children

         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    public void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] != null) {
                if (pcbArray[i].parent != -1) {
                    System.out.print("Process " + i + ": parent is " + pcbArray[i].parent);
                    if (!pcbArray[i].children.isEmpty()) {
                        System.out.print(" and children are ");
                        for (int childPid : pcbArray[i].children) {
                            System.out.print(childPid + " ");
                        }
                    } else {
                        System.out.print(" and has no children");
                    }
                    System.out.println();
                }
            }
        }
    }

    /* If you need or want more methods, feel free to add them. */
    /**
     * Finds a free process ID in the PCB array.
     *
     * @return The index of a free process ID if found, or -1 if no free process ID is available.
     */
    private int findFreePid() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i].parent == -1) {
                return i;
            }
        }
        return -1;
    }

    /**
    * Recursively collects the descendants of a given process ID.
    *
    * @param pid         The process ID whose descendants are to be collected.
    * @param descendants The list to store the collected descendant process IDs.
    */
    private void collectDescendants(int pid, LinkedList<Integer> descendants) {
        if (pcbArray[pid] == null) {
            // Handle the case where pcbArray[pid] is null
            return;
        }
        
        descendants.add(pid);
        
        if (pcbArray[pid].children == null) {
            // Handle the case where pcbArray[pid].children is null
            return;
        }
        
        for (int childPid : pcbArray[pid].children) {
            collectDescendants(childPid, descendants);
        }
    }
    
    
}
