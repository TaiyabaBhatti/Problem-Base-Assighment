public class Queue {

private NodeCrop head=new NodeCrop(null);  //dummy node
     private int size;
     // we cants add nodes as head.next=new node
     // this will lead in every entry the first they will be the recent one
     // but in case it should be the last, so we move back then add.
     // we have to do this head.previous.n
     public void addObjects(CropData ob) {
          head.previous.next=new NodeCrop(ob,head.previous,head);
          head.previous=head.previous.next;
          size++;
     }

     public int size() {
          return size;
     }
     public Object First() {
          isEmpty();
          return head.next.next.data;
     }

     public Object last() {
          isEmpty();
          return head.previous.data;
     }
     public Object removeFirst() {
          isEmpty();
          NodeCrop temp=head.next;
          head.next=temp.next;
          head.next.previous=head;
          size--;
          return temp.data;
     }
     public Object removeLast(){
          isEmpty();
          NodeCrop temp=head.previous;  //last element
          head.previous=temp.previous;
          head.previous.next=head;
          return temp.data;
     }
     public Object removeSecondNode() {
          isEmpty();

          NodeCrop temp=head.next.next;
          head.next.next=temp.next;
          temp.next.previous=head.next;
          return temp.data;

     }
     public void forwardTraversing() {
          isEmpty();
          NodeCrop temp=head.next;
          while (temp.data!=head.data){

               System.out.println(temp.data.cropName+"\t\t\tdistrict :"+temp.data.district+" year :"+temp.data.cropYear);
               temp=temp.next;
          }
     }
     public void backwardTraversing() {
          isEmpty();
          NodeCrop temp=head.previous;
          while (temp.data!=head.data){
               System.out.print(temp.data+" ");
               temp=temp.previous;
          }
     }
     public Object searchNodeByPosition(int position) {
          isEmpty();
          NodeCrop temp=head.next;
          int counter=1;
          while(temp.data!=head.data){

               if (counter==position){
                    return temp.data;
               }
               temp=temp.next;
               counter++;
          }
          return -1;
     }
     public boolean serachNodeByData(Object data) {
          isEmpty();
          NodeCrop temp=head.next;
          while(temp.data!=head.data){

               if(temp.data==data){
                    return true;
               }
               temp=temp.next;
          }
          return false;
     }
     public void isEmpty(){
          if (size==0){
               throw new IllegalStateException("Queue is empty");
          }
     }
}

class NodeCrop{
     public NodeCrop next=this;        //next should be Node
     public NodeCrop previous=this;      //previous should be node
     public CropData data;
     public NodeCrop(CropData data) {
          this.data=data;
     }
     public NodeCrop(CropData data, NodeCrop next){
          this.data=data;
          this.next=next;
     }
     public NodeCrop(CropData data, NodeCrop previous, NodeCrop next){
          this.data=data;
          this.previous=previous;
          this.next=next;
     }
}