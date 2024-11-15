/**
 * A simple binary search tree
 */
public class BST {
    /** The entry point to the tree */
    private TreeNode root;
    /** Count of nodes in the tree */
    private int numberOfNodes;
    /** Longest and shortest words stored in the tree */
    private String longest;
    private String shortest;

    /** Default constructor */
    public BST() {
        this.root = null;
        this.numberOfNodes = 0;
        this.shortest = null;
        this.longest = null;
    } // default constructor

    /**
     * Overloaded add to take a string, wrap it into a TreeNode object, and invoke
     * the principal method that adds a note to the tree.
     * 
     * @param word String to add, as a node, to the tree
     * 
     */
    public void add(String word) {
        this.add(new TreeNode(word));
    } // method add

    /**
     * Insert a new node into the tree; the method takes no action if a node with
     * the same payload already exists in the tree.
     * 
     * @param newNode node to insert
     */
    public void add(TreeNode newNode) {
        if (this.root == null) {
            this.root = newNode;
            this.numberOfNodes = 1;
            this.shortest = newNode.getWord();
            this.longest = newNode.getWord();
        } else {
            TreeNode cursor = this.root;
            TreeNode parent = null;
            boolean duplicate = false;
            while (cursor != null && !duplicate) {
                parent = cursor;
                duplicate = newNode.compareTo(cursor) == 0;
                if (newNode.compareTo(cursor) < 0) {
                    cursor = cursor.getLeft();
                } else {
                    cursor = cursor.getRight();
                }
            }
            // The while loop ends when it finds a spot for the new node or when discovering
            // a duplicate entry. If there is a duplicate entry, there will be no insertion.
            if (!duplicate) {
                if (newNode.compareTo(parent) < 0) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                // Update the number of nodes in the tree
                this.numberOfNodes++;
                // Check if new node contains a string longer than the longest string
                if (newNode.getWord().length() > this.longest.length()) {
                    this.longest = newNode.getWord();
                }
                // Check if new node has a string shorter than the shortest string
                if (newNode.getWord().length() < this.shortest.length()) {
                    this.shortest = newNode.getWord();
                }
            }
        }
    } // method add

    /**
     * In order traversal of a tree
     * 
     * @return a String[] with the contents of the tree as they appear
     */
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getWord());
            traverseInOrder(node.getRight());
        }
    } // method traverseInOrder

    /**
     * Helper method to start in-order traversal
     */
    public void traverseInOrder() {
        if (this.root != null) {
            this.traverseInOrder(this.root);
        }
    } // helper method traverseInOrder

    /**
     * Helper method that initiates removal of a node with a specific string. The
     * method calls an overloaded version of itself to do the actual digging. The
     * overloaded method can focus on the tree itself (starting from this.root) or
     * any subtree thereof.
     * 
     * @param target string contents of node we wish to remove
     * @return the removed node; if no node found, method returns null
     */
    public TreeNode remove(String target) {
        TreeNode removed = null;
        if (target != null && this.root != null) {
            removed = this.remove(target, this.root);
        }
        return removed;
    } // helper method remove

    /**************************************************************************
     * METHOD STUBS FOR ASSIGNMENT DUE 11/15/24. THESE METHODS ARE INCOMPLETE AND,
     * OBVIOUSLY, LACK DOCUMENTATION. AS PART OF THE ASSIGNMENT, YOU'LL PROVIDE THE
     * NECESSARY COMMENTS AND, OF COURSE, SOME AWESOME CODE.
     **************************************************************************/

    public boolean contains(String target) {
        //Initialize found to false... changes throughout the if loop
        boolean found = false;
        //checks to see if tree and target string not empty
        if (this.root != null && target != null) {
            //starts in the root node
            TreeNode cursor = this.root;
            //traverse the tree while found and cursor are no null
            while (!found && cursor != null) {
                //node word equals target
                found = cursor.getWord().equals(target);
                //if less than child moves to the current node
                if (target.compareTo(cursor.getWord()) < 0) {
                    cursor = cursor.getLeft();
                } else {
                    cursor = cursor.getRight();
                }
            }
        }
        //reurns resul 
        return found;
    } // method contains

    public String toString() {
        //description of the tree
        final String EMPTY = "The tree is empty.";
        final String NODES_FMT = "There are %d nodes in the tree.\n";
        final String ROOT_FMT = "The tree is rooted at %s.\n";
        final String SHORTEST_FMT = "The shortest is %s with %d characters.\n";
        final String LONGEST_FMT = "The longest is %s with %d characters.";
        //used for final string representation
        StringBuilder sb = new StringBuilder();
        //if tree is empty put empty
        if (this.root == null) {
            sb.append(EMPTY);
        } else {
            //tree is not empty, check how many nodes there are 
            sb.append(String.format(NODES_FMT, this.numberOfNodes));
            //get the word in root
            sb.append(String.format(ROOT_FMT, this.root.getWord()));
            //get shortest word of the tree with length
            sb.append(String.format(SHORTEST_FMT, this.shortest, this.shortest.length()));
            //get longest word of the tree with length
            sb.append(String.format(LONGEST_FMT, this.longest, this.longest.length()));
        }
        return sb.toString();
    } // method toString

    public TreeNode remove(String target, TreeNode belowNode) {
        // Initialize removed to null
    TreeNode removed = null;

    if (belowNode != null) {
        //locate node
        TreeNode cursor = belowNode;
        //parent to null if empty
        TreeNode parent = null;
        //initialize found to false 
        boolean found = false;

        // locate node to remove
        while (cursor != null && !found) {
            //update parent
            parent = cursor;
            //see if current node is equal/the target
            found = target.equals(cursor.getWord());
            //if not found
            if (!found) {
                //move left or right until found
                if (target.compareTo(cursor.getWord()) < 0) {
                    //move to left child
                    cursor = cursor.getLeft();
                    //move to right child
                } else {
                    cursor = cursor.getRight();
                }
            }
        }

        // found node and remove it
        if (found) {
            //node to remove
            removed = cursor; 
            //find how many children there are using the Treenode method
            int childrenCount = cursor.countChildren();
            // node has 0 children
            if (childrenCount == 0) {
                //cursor is a left child of parent
                if (parent.getLeft() == cursor) {
                    //parent becomes null
                    parent.setLeft(null);
                } else {
                    //otherwise set parent right to null
                    parent.setRight(null);
                }
            }
            // node has 1 child
            else if (childrenCount == 1) {
                //check if the child is lef or righ
                TreeNode child = (cursor.getLeft() != null) ? cursor.getLeft() : cursor.getRight();
                //if child is left, replace its child
                if (parent.getLeft() == cursor) {
                    parent.setLeft(child);
                } else {
                    //replace it right
                    parent.setRight(child);
                }
            }
            // Node has 2 children
            else {
                // find small.. nextParent to cursor
                TreeNode previousParent = cursor;
                //cursor receives right
                TreeNode nextParent = cursor.getRight();
                //Traverse to find left child in the right subtree
                while (nextParent.getLeft() != null) {
                    //update parent successor's
                    previousParent = nextParent;
                    //move to left child
                     nextParent= nextParent.getLeft();
                }
                
                // Copy successor value to cursor
                cursor.setWord(nextParent.getWord());

                // Remove successor node
                if (previousParent.getLeft() == nextParent) {
                    //bypass successor
                    previousParent.setLeft(nextParent.getRight()); 
                } else {
                    //bypass successor again
                    previousParent.setRight(nextParent.getRight()); 
                }
            }
            // Update the number of nodes
            this.numberOfNodes--;
        }
    }
    return removed;
    } // method remove

    /******************************* Accessors *******************************/

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public String getLongest() {
        return longest;
    }

    public String getShortest() {
        return shortest;
    }
} // class BST
