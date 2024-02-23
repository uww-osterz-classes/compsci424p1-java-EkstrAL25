/* COMPSCI 424 Program 1
 * Name: Alek Ekstrand
 */
package compsci424.p1.java;
import java.util.LinkedList;


/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    int parent;
    LinkedList<Integer> children;

    public Version1PCB() {
        this.parent = -1;
        this.children = new LinkedList<>();
    }

}
