/* COMPSCI 424 Program 1
 * Name: Alek Ekstrand
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    int parent;
    int first_child;
    int younger_sibling;
    int older_sibling;

    public Version2PCB() {
        this.parent = -1;
        this.first_child = -1;
        this.younger_sibling = -1;
        this.older_sibling = -1;
    }
}