package linklist;


public class Test {
    public static void main(String[] args){
//        testRotate();
//        testAddNumber();
//        testReverseLinkList();
//        Solutions.removeNthFromEnd(new ListNode(1), 1);
//        testReverseOfK();
    }

    private static void testRotate() {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for(int i = 2; i< 6; i++){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode temp1= head;
        do{
            System.out.print(temp1.val +",");
            temp1 = temp1.next;
        } while (temp1 != null);
        System.out.println("");
        ListNode  result = Solutions.rotateRight(head, 4);
        do{
            System.out.println(result.val);
            result = result.next;
        } while (result != null);
    }

    private static void testAddNumber(){
        ListNode val1 = new ListNode(2);
        val1.next = new ListNode(4);
        val1.next.next = new ListNode(3);

        ListNode val2 = new ListNode(5);
        val2.next = new ListNode(6);
        val2.next.next = new ListNode(4);

        ListNode result = Solutions.addTwoNumber(val1, val2);

        do{
            System.out.println(result.val);
            result = result.next;
        } while (result != null);

    }

    private static void testReverseLinkList(){
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for(int i = 2; i< 6; i++){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode temp1= head;
        do{
            System.out.print(temp1.val +",");
            temp1 = temp1.next;
        } while (temp1 != null);
        System.out.println("");
        ListNode  result = Solutions.reverseLinkList(head);
        do{
            System.out.println(result.val);
            result = result.next;
        } while (result != null);
    }

    public static void testReverseOfK(){
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for(int i = 2; i< 3; i++){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode temp1= head;
        do{
            System.out.print(temp1.val +",");
            temp1 = temp1.next;
        } while (temp1 != null);
        System.out.println("");
        ListNode  result = Solutions.reverseLinkOfK(head, 2);
        do{
            System.out.println(result.val);
            result = result.next;
        } while (result != null);
    }
}
