// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if (blockSize<=0) return -1;
        //System.out.println("freeblock ki key"+freeBlk.key);
        /*System.out.println("START");  
        ((AVLTree) freeBlk).printInorder((AVLTree)freeBlk);   
        System.out.println("END");
        System.out.println("START THE ALOOCC");  
        ((AVLTree) allocBlk).printInorder((AVLTree)allocBlk);   
        System.out.println("END THE ALOOOOCC");
        System.out.println("PREEEOORDDERR");
        System.out.println("START");  
        ((AVLTree) freeBlk).printPreorder((AVLTree)freeBlk);   
        System.out.println("END");
        System.out.println("START THE ALOOCC");  
        ((AVLTree) allocBlk).printPreorder((AVLTree)allocBlk);   
        System.out.println("END THE ALOOOOCC");*/
        Dictionary a=freeBlk.Find(blockSize, false);               
        if (a!=null){
            int x=a.address;
            //System.out.println("a ka address"+x);
            //System.out.println("a ka key"+a.key);
            if (a.size!=blockSize){
                freeBlk.Insert(a.address+blockSize, a.size-blockSize, a.size-blockSize);    
                //System.out.println("b ka address"+b.address);
                //System.out.println("b ka key"+b.key);
            }
            
            freeBlk.Delete(a);                             // change karna ye  
            //System.out.println("delete k baad yaha hu me");
            //System.out.println("Alloc me insert"+a.address);
            allocBlk.Insert(a.address,blockSize,a.address);               // ?? reverse both                        
            //System.out.println("Delete hua ki nahi"+var);
                   
            return x;                                              
        }
        //System.out.println("INHERE");
        //((BSTree) freeBlk).printInorder((BSTree)freeBlk);
        return -1;
    } 
    
    public int Free(int startAddr) {
        if (startAddr<0) return -1;
        /*System.out.println("START");  
        ((AVLTree) freeBlk).printInorder((AVLTree)freeBlk);   
        System.out.println("END");
        System.out.println("START THE ALOOCC");  
        ((AVLTree) allocBlk).printInorder((AVLTree)allocBlk);   
        System.out.println("END THE ALOOOOCC");
        System.out.println("PREEEOORDDERR");
        System.out.println("START");  
        ((AVLTree) freeBlk).printPreorder((AVLTree)freeBlk);   
        System.out.println("END");
        System.out.println("START THE ALOOCC");  
        ((AVLTree) allocBlk).printPreorder((AVLTree)allocBlk);   
        System.out.println("END THE ALOOOOCC");*/
        Dictionary a=allocBlk.Find(startAddr, true);                
        if (a!=null){
            freeBlk.Insert(startAddr, a.size, a.size);              
            allocBlk.Delete(a);                           
            return 0;
        }
        return -1;
    }

}
