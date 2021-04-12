public class TestA1List{
	public static void print(A1List d){
        //A1List current=this.getFirst();
		int count = 0; 
		System.out.print("elements : ");
        for (d =d.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.key+" ");
            count = count + 1;
        }
        System.out.println();
        System.out.println("size of list: "+count);
    }
	public static void main(String[] args){
		A1List d=new A1List();
		d=d.Insert(0,100,100);
		print(d);
		A1List a=d.Find(-1,true);
		if(a==null){
			System.out.println("Value of a is:null");
		}
		else{
			System.out.println("Value of a is:"+a.key);
		}
		d=d.Insert(-1,-1,-1);
		print(d);
		//Dictionary s=new Dictionary 
		A1List b=d.Find(-1,true);
		if(b==null){
			System.out.println("Value of b is:null");
		}
		else{
			System.out.println("Value of b is:"+b.key);
		}
		Dictionary c= new A1List(-1,-1,-1);
		boolean res=d.Delete(b);
		// System.out.println("Value of d:"d.key);
		b = d.Find(-1,true);
		if(b==null){
			System.out.println("Value of b is:null");
		}
		else{
			System.out.println("Value of b is:"+b.key);
		}
		d.Delete(c);
		
		print(d);
		// System.out.println(d.key);
		// System.out.println(d.next.key);
		// // d = d.getNext();
		// d = d.next;
		// System.out.println(d.key);
		d=d.Insert(0,10,10);
		System.out.println(d.key);
		// System.out.println(d.next.key);
		// Dictionary c=new A1List(-1,-1,-1);
		b = d.Find(-1, true);
		if(b!=null){boolean res1=d.Delete(b);
		System.out.println(res1);}
		else {System.out.println("Value of b is: null");}
		print(d);
	}
	/*public static void main(String[] args){
		A1List a= new A1List();
		a.Insert(0,20,20);
		print(a);
		a.Insert(20,80,60);
		print(a);
	}*/
}