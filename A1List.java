// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if (this.next==null) return null;
        A1List node=new A1List(address,size,key);                // just one the next node
        this.next.prev=node;
        node.next=this.next;
        node.prev=this;
        this.next=node;
        return node;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List node=this.getFirst();                 // search from the first node
        while(node!=null){       
            if (node.key==d.key && node.address==d.address && node.size==d.size){                 
                node.prev.next=node.next;
                node.next.prev=node.prev;
                return true;
            }
            node=node.getNext();     
        }
        return false;
    }

    public A1List Find(int k, boolean exact)     
    { 
        A1List node=this.getFirst();                                        // find from the first node
        if (exact){
            while(node!=null){              // for doing free        
                if (node.key==k){
                    return node;
                }
                node=node.getNext();
            }            
        }else{
            while(node!=null){                 // for allocating        
                if (node.key>=k){
                    return node;
                }
                node=node.getNext();
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List node=this;
        while(node.prev!=null){                 
            node=node.prev;
        }
        node=node.next;
        if (node.next==null) return null;    // so that I dont return tail
        return node;          // return first element of dll

    }
    
    public A1List getNext() 
    {
        if (this.next==null || this.next.next==null) return null;
        return this.next;                                         // cant return null for tail
    }

    public boolean sanity()
    {
        A1List a1=this;                               
        A1List a2=this;                          
        while(a2!=null){                                    // cycle infront of this due to next
            a2=a2.next;
            if (a2==null) break;
            a2=a2.next;
            a1=a1.next;
            if (a1==a2) return false;
        }
        a1=this;
        a2=this;
        while(a2!=null){                                  // cycle behind this due to prev
            a2=a2.prev;
            if (a2==null) break;
            a2=a2.prev;
            a1=a1.prev;
            if (a1==a2) return false;
        }
        a1=this;
        a2=this;
        while(a1.prev!=null){
            a1=a1.prev;
            a2=a2.prev;
        }
        while(a2!=null){                                       // cycle ahead of head due to next
            a2=a2.next;
            if (a2==null) break;
            a2=a2.next;
            a1=a1.next;
            if (a1==a2) return false;
        }
        a1=this;
        a2=this;
        while(a1.next!=null) {
            a1=a1.next;
            a2=a2.next;
        }
        while(a2!=null){                                  // cycle ahead of tail due to prev
            a2=a2.prev;
            if (a2==null) break;
            a2=a2.prev;
            a1=a1.prev;
            if (a1==a2) return false;
        }

        A1List node=this;                                  // to check misplaced pointers
        while(node.prev!=null) node=node.prev;
        while(node.next!=null){
            if (node.next.prev!=node) return false;
            node=node.next;
        }

        node=this;
        while(node.next!=null) node=node.next;
        while(node.prev!=null){
            if (node.prev.next!=node) return false;
            node=node.prev;
        }



        node=this;
        while(node.prev!=null) node=node.prev;                           // any node which has its previous or next null should be head or tail senital 
        if (node.key!=-1 || node.address!=-1 || node.size!=-1) return false;
        while(node.next!=null) node=node.next;
        if (node.key!=-1 || node.address!=-1 || node.size!=-1) return false;

        

        return true;
    }


    
	

    

}

