// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if (type==2){
            Dictionary bad= new BSTree();
            Dictionary a=freeBlk.getFirst();
            while(a!=null){
                bad.Insert(a.address, a.size, a.address);
                a=a.getNext();
            }
            Dictionary current=bad.getFirst();
            while(current!=null){
                Dictionary next=current.getNext();
                if (next==null) break;
                if (current.address+current.size==next.address){
                    Dictionary cf=new BSTree(current.address, current.size, current.size);
                    Dictionary nf=new BSTree(next.address, next.size, next.size);
                    freeBlk.Delete(cf);
                    freeBlk.Delete(nf);
                    freeBlk.Insert(current.address, current.size+next.size, current.size+next.size);
                    bad.Delete(current);
                    bad.Delete(next);
                    current=bad.Insert(current.address, current.size+next.size, current.address); 
                }else current=current.getNext();
            }
            bad=null;               // delete the dictionary
        }else{
            Dictionary bad= new AVLTree();
            Dictionary a=freeBlk.getFirst();
            while(a!=null){
                
                bad.Insert(a.address, a.size, a.address);    
                a=a.getNext();
            }
            Dictionary current=bad.getFirst();
            while(current!=null){
                Dictionary next=current.getNext();
                if (next==null) break;
                if (current.address+current.size==next.address){
                    Dictionary cf=new AVLTree(current.address, current.size, current.size);
                    Dictionary nf=new AVLTree(next.address, next.size, next.size);
                    freeBlk.Delete(cf);
                    freeBlk.Delete(nf);
                    freeBlk.Insert(current.address, current.size+next.size, current.size+next.size);
                    bad.Delete(current);
                    bad.Delete(next);
                    current=bad.Insert(current.address, current.size+next.size, current.address); 
                }else current=current.getNext();
            }
            bad=null;
        }
        return ;
    }
    public static void main(String[] args){
        A2DynamicMem obj = new A2DynamicMem(100,3);
        System.out.println(obj.Allocate(10));
        System.out.println(obj.Allocate(10));
        /*System.out.println(obj.Allocate(20));
        System.out.println(obj.Allocate(5));
        System.out.println(obj.Allocate(10));
        System.out.println(obj.Allocate(15));
        System.out.println(obj.Allocate(15));
        System.out.println(obj.Allocate(20));
        System.out.println(obj.Allocate(3));
        System.out.println(obj.Free(0));
        System.out.println(obj.Free(20));
        System.out.println(obj.Free(25));
        System.out.println(obj.Free(35));
        System.out.println(obj.Free(50));
        System.out.println(obj.Free(65));
        obj.Defragment();
        System.out.println(obj.Free(85));*/
    }
}