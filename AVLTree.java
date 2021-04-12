// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.


    
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree node= new AVLTree(address, size, key);                  
        AVLTree x=this.getHead();
        AVLTree y=x;
        x=x.right;
        while(x!=null){
            y=x;
            if (key<x.key){
                x=x.left;
            }else if (key==x.key){
                if (address<=x.address) x=x.left;
                else x=x.right;
            }else{
                x=x.right;
            }
        }
        if (y.parent==null){
            y.right=node;
            node.parent=y;
        }else if (key<y.key){
            y.left=node;
            node.parent=y;
        }else if (key==y.key){
            if (address<=y.address){
                y.left=node;
                node.parent=y;
            }else{
                y.right=node;
                node.parent=y;
            }
        }else{
            y.right=node;
            node.parent=y;
        }
        y=node;
        while(y.parent!=null){
            int balance=getBalance(y);
            if (balance>1){
                if (key<y.right.key){
                    y.right=rightRotate(y.right);
                    y=leftRotate(y);
                }else if (key==y.right.key){
                    if (address<=y.right.address){
                        y.right=rightRotate(y.right);
                        y=leftRotate(y);
                    }else{
                        y=leftRotate(y);
                    }
                }else{
                    y=leftRotate(y);
                }
            }
            if (balance<-1){
                if (key<y.left.key){
                    y=rightRotate(y);
                }else if (key==y.left.key){
                    if (address<=y.left.address){
                        y=rightRotate(y);
                    }else{
                        y.left=leftRotate(y.left);
                        y=rightRotate(y);
                    }
                }else{
                    y.left=leftRotate(y.left);
                    y=rightRotate(y);
                }
            }
            y.height=Math.max(getHeight(y.left), getHeight(y.right))+1;
            y=y.parent;
        }    
        return node;
    }

    private AVLTree rightRotate(AVLTree node){
        AVLTree n1=node.left;
        node.left=n1.right;
        if (n1.right!=null) n1.right.parent=node;
        if (node.parent.right==node){
            node.parent.right=n1;
        }else{
            node.parent.left=n1;
        }
        n1.parent=node.parent;
        node.parent=n1;
        n1.right=node;
        node.height=Math.max(getHeight(node.left), getHeight(node.right))+1;
        n1.height=Math.max(getHeight(n1.left), getHeight(n1.right))+1;
        return n1;
    }

    private AVLTree leftRotate(AVLTree node){
        AVLTree n1=node.right;
        node.right=n1.left;
        if (n1.left!=null) n1.left.parent=node;
        if (node.parent.right==node){
            node.parent.right=n1;
        }else{
            node.parent.left=n1;
        }
        n1.parent=node.parent;
        node.parent=n1;
        n1.left=node;
        node.height=Math.max(getHeight(node.left), getHeight(node.right))+1;
        n1.height=Math.max(getHeight(n1.left), getHeight(n1.right))+1;
        return n1;
    }

    private AVLTree getHead(){
        AVLTree node=this;
        while(node.parent!=null) node=node.parent;
        return node;
    }

    private int getHeight(AVLTree node){
        if (node==null) return 0;
        return node.height;        
    }

    private int getBalance(AVLTree node){
        if (node==null) return 0;
        return getHeight(node.right)-getHeight(node.left);
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree x=this.getHead();   
        AVLTree y=x;
        x=x.right;    // root
        while(x!=null){
            if (e.key==x.key && e.address==x.address && e.size==x.size){
                if (x.right==null && x.left==null){
                    if (y.right==x) y.right=null;
                    else y.left=null;
                    x.parent=null;
                    x.height=0;
                }else if (x.right==null){
                    if (y.right==x){
                        y.right=x.left;
                        x.left.parent=y;
                        x.parent=null;
                        x.left=null;
                        x.height=0;
                    }else{
                        y.left=x.left;
                        x.left.parent=y;
                        x.parent=null;
                        x.left=null;
                        x.height=0;
                    }
                }else if (x.left==null){
                    if (y.right==x){
                        y.right=x.right;
                        x.right.parent=y;
                        x.parent=null;
                        x.right=null;
                        x.height=0;
                    }else{
                        y.left=x.right;
                        x.right.parent=y;
                        x.parent=null;
                        x.right=null;
                        x.height=0;
                    }
                }else {                                           // both not null
                    AVLTree z=x.getNext();
                    Delete(z);
                    AVLTree node=new AVLTree(z.address, z.size, z.key);
                    y=x.parent;
                    if (y.right==x){
                        y.right=node;
                    }else{
                        y.left=node;
                    }
                    node.parent=y;
                    node.left=x.left;
                    node.right=x.right;
                    node.height=x.height;
                    x.height=0;
                    if (x.left!=null) x.left.parent=node;
                    if (x.right!=null) x.right.parent=node;
                }
                while(y.parent!=null){                              // making tree balance
                    int balance=getBalance(y);
                    if (balance>1){
                        if (getBalance(y.right)<0){
                            y.right=rightRotate(y.right);
                            y=leftRotate(y);
                        }else{
                            y=leftRotate(y);
                        }
                    }
                    if (balance<-1){
                        if (getBalance(y.left)<=0){
                            y=rightRotate(y);
                        }else{
                            y.left=leftRotate(y.left);
                            y=rightRotate(y);
                        }
                    }
                    y.height=Math.max(getHeight(y.left), getHeight(y.right))+1;
                    y=y.parent;
                }
                return true;
            }
            y=x;
            if (e.key<x.key){                           
                x=x.left;
            }else if (e.key==x.key){
                if (e.address<=x.address) x=x.left;                     // check
                else x=x.right;
            }else x=x.right;      
        }
        return false;
    }
        
    public AVLTree Find(int key, boolean exact)
    { 
        AVLTree x=this.getHead();             
        x=x.right;       
        if (exact){
            AVLTree y=null;
            while(x!=null){
                if (key<x.key){
                    x=x.left;
                }else if (key==x.key){
                    y=x;                                            // minimum address
                    x=x.left;
                }else{
                    x=x.right;
                }
            }
            return y;
        }else{
            AVLTree y=null;
            while(x!=null){
                if (key<x.key){
                    y=x;
                    x=x.left;
                }else if (key==x.key){
                    y=x;                            // minimum address
                    x=x.left;
                }else{
                    x=x.right;
                }
            }
            return y;
        }
    }

    private AVLTree getMin(){                                   // min of the following subtree
        AVLTree node=this;
        while(node.left!=null) node=node.left;
        return node;
    }

    public AVLTree getFirst()
    { 
        AVLTree node=this.getHead();
        node=node.right;
        if (node==null) return node;                             // empty
        while(node.left!=null) node=node.left;
        return node;
    }

    public AVLTree getNext()
    {
        if (this.parent==null) return null;                    // head sentinal
        AVLTree node=this;
        if (node.right!=null){
            node=node.right;
            node=node.getMin();                                 // minumum in that subtree
            return node;
        }else{
            AVLTree nodep=node.parent;                                 
            while(nodep != null && node==nodep.right){
                node=nodep;
                nodep=node.parent;
            }
            return nodep;
        }
    }

    private boolean checkCycle(AVLTree node){
        if (node==null) return true;
        if (node.left!=null && node.left.parent!=node ) return false;
        if (node.right!=null && node.right.parent!=node ) return false;
        if (node.parent!=null){
            if (node.parent.right!=node && node.parent.left!=node) return false;
        }
        return checkCycle(node.left) && checkCycle(node.right);
    }

    private boolean checkSamePointer(AVLTree node){
        if (node==null) return true;
        if (node.left!=null && node.right!=null && node.right==node.left) return false;
        return checkSamePointer(node.left) && checkSamePointer(node.right);
    }
    private AVLTree var;
    private boolean checkBST(AVLTree node){
        if (node==null) return true;
        if (!checkBST(node.left)) return false;
        if (node.key<var.key || (node.key==var.key && node.address<var.address)) return false;
        var=node;
        return checkBST(node.right);
    }

    private boolean checkHeight(AVLTree node){
        if (node==null) return true;
        if (!checkHeight(node.left)) return false;
        if (!checkHeight(node.right)) return false;
        
        if (node.height!=Math.max(getHeight(node.left), getHeight(node.right))+1) return false;
        return true;
        
    }

    private boolean checkBalance(AVLTree node){
        if (node==null) return true;
        int balance=getBalance(node);
        if (balance>1 || balance<-1) return false;
        return checkBalance(node.left) && checkBalance(node.right);
        
    }

    public boolean sanity()
    { 
        AVLTree a1=this;
        AVLTree a2=this;
        while(a2!=null){                                   
            a2=a2.parent;
            if (a2==null) break;
            a2=a2.parent;
            a1=a1.parent;
            if (a1==a2) return false;
        }
        AVLTree x=this.getHead();         
        if (x.key!=-1 || x.address!=-1 || x.size!=-1) return false;
        if (x.left!=null) return false;
        if (!checkCycle(x)) return false;
        if (!checkSamePointer(x)) return false;
        var= new AVLTree(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (!checkBST(x.right)) return false; 

        if (!checkBalance(x.right)) return false;
        if (!checkHeight(x.right)) return false;


        return true;
    }
}


