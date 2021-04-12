// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree node= new BSTree(address, size, key);                  
        BSTree x=this.getHead();
        BSTree y=x;
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
        return node;
    }
    public boolean Delete(Dictionary e)
    { 
        BSTree x=this.getHead();
        BSTree y=x;
        x=x.right;    // root
        while(x!=null){
            if (e.key==x.key && e.address==x.address && e.size==x.size){
                if (x.right==null && x.left==null){
                    if (y.right==x) y.right=null;
                    else y.left=null;
                    x.parent=null;
                    return true;
                }
                if (x.right==null){
                    if (y.right==x){
                        y.right=x.left;
                        x.left.parent=y;
                        x.parent=null;
                        x.left=null;
                    }else{
                        y.left=x.left;
                        x.left.parent=y;
                        x.parent=null;
                        x.left=null;
                    }
                    return true;
                }
                if (x.left==null){
                    if (y.right==x){
                        y.right=x.right;
                        x.right.parent=y;
                        x.parent=null;
                        x.right=null;
                    }else{
                        y.left=x.right;
                        x.right.parent=y;
                        x.parent=null;
                        x.right=null;
                    }
                    return true;
                }
                BSTree z=x.getNext();
                Delete(z);
                BSTree node=new BSTree(z.address, z.size, z.key);
                if (y.right==x){
                    y.right=node;
                }else{
                    y.left=node;
                }
                node.parent=y;
                node.left=x.left;
                node.right=x.right;
                if (x.left!=null) x.left.parent=node;
                if (x.right!=null) x.right.parent=node;
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
        
    public BSTree Find(int key, boolean exact)                      
    { 
        BSTree x=this.getHead();             
        x=x.right;       
        if (exact){
            BSTree y=null;
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
            BSTree y=null;
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

    private BSTree getHead(){
        BSTree node=this;
        while(node.parent!=null) node=node.parent;
        return node;
    }

    private BSTree getMin(){                                   // min of the following subtree
        BSTree node=this;
        while(node.left!=null) node=node.left;
        return node;
    }

    public BSTree getFirst()
    { 
        BSTree node=this.getHead();
        node=node.right;
        if (node==null) return node;                             // empty
        while(node.left!=null) node=node.left;
        return node;
    }

    public BSTree getNext()
    { 
        if (this.parent==null) return null;                    // head sentinal
        BSTree node=this;
        if (node.right!=null){
            node=node.right;
            node=node.getMin();                                 // minumum in that subtree
            return node;
        }else{
            BSTree nodep=node.parent;                                 
            while(nodep != null && node==nodep.right){
                node=nodep;
                nodep=node.parent;
            }
            return nodep;
        }
    }

    private boolean checkCycle(BSTree node){
        if (node==null) return true;
        if (node.left!=null && node.left.parent!=node ) return false;
        if (node.right!=null && node.right.parent!=node ) return false;
        return checkCycle(node.left) && checkCycle(node.right);
    }

    private boolean checkSamePointer(BSTree node){
        if (node==null) return true;
        if (node.left!=null && node.right!=null && node.right==node.left) return false;
        return checkSamePointer(node.left) && checkSamePointer(node.right);
    }
    private BSTree var;
    private boolean checkBST(BSTree node){
        if (node==null) return true;
        if (!checkBST(node.left)) return false;
        if (node.key<var.key || (node.key==var.key && node.address<var.address)) return false;
        var=node;
        return checkBST(node.right);
    }

    public boolean sanity()
    {
        BSTree a1=this;
        BSTree a2=this;
        while(a2!=null){                                   
            a2=a2.parent;
            if (a2==null) break;
            a2=a2.parent;
            a1=a1.parent;
            if (a1==a2) return false;
        }
        BSTree x=this.getHead();         
        if (x.key!=-1 || x.address!=-1 || x.size!=-1) return false;
        if (x.left!=null) return false;
        if (!checkCycle(x)) return false;
        if (!checkSamePointer(x)) return false;
        var= new BSTree(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (!checkBST(x.right)) return false;                               

        return true;
    }
}


 


